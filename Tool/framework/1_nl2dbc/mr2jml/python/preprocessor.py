import os
import re
import sys
from typing import List, Dict
from dstbuilder import get_package_global_info_from_javasrc
import yaml

import javalang
import nltk

Pronouns = ['I', 'me', 'he', 'she', 'herself', 'you', 'it', 'that', 'they', 'each', 'few', 'many', 'who', 'whoever',
            'whose', 'someone', 'everybody']

PTBDescriptions = [
    "coordinating conjunction",
    "cardinal digit",
    "determiner",
    "existential there",
    "foreign word",
    "preposition/subordinating conjunction",
    "adjective",
    "adjective, comparative",
    "adjective, superlative",
    "list marker",
    "modal",
    "noun, singular",
    "noun plural",
    "proper noun, singular",
    "proper noun, plural",
    "predeterminer",
    "possessive ending",
    "personal pronoun",
    "possessive pronoun",
    "adverb",
    "adverb, comparative",
    "adverb, superlative",
    "particle",
    "to",
    "interjection",
    "base form",
    "past tense",
    "gerund/present participle",
    "past participle",
    "singular present, non-3d",
    "3rd person sing. present",
    "wh-determiner",
    "wh-pronoun",
    "possessive wh-pronoun",
    "wh-abverb"
]

PTBTagSet = [
    "cc",
    "cd",
    "dt",
    "ex",
    "fw",
    "in",
    "jj",
    "jjr",
    "jjs",
    "ls",
    "md",
    "nn",
    "nns",
    "nnp",
    "nnps",
    "pdt",
    "pos",
    "prp",
    "prp$",
    "rb",
    "rbr",
    "rbs",
    "rp",
    "to",
    "uh",
    "vb",
    "vbd",
    "vbg",
    "vbn",
    "vbp",
    "vbz",
    "wdt",
    "wp",
    "wp$",
    "wrb"
]


class Preprocessor:
    patterns = {
        'null': 'a null_value',
        'true': 'a true_value',
        'false': 'a false_value',
        'should be': 'is',
        'should not be': 'is not',
        'must be': 'is',
        'must not be': 'is not',
        'is equal to': 'is',
        'will contain': 'contains'
    }

    def __preprocess(self, pattern: str) -> list:
        t = []
        for i in range(256):
            t.append(len(pattern))
        for i in range(len(pattern) - 1):
            t[ord(pattern[i])] = len(pattern) - 1 - i
        return t

    def __same(self, str1: str, str2: str, length: int) -> bool:
        i = length - 1
        while str1[i].lower() == str2[i].lower():
            if i == 0:
                return True
            i = i - 1
        return False

    def __search(self, needle: str, haystack: str):
        t = self.__preprocess(needle)
        skip = 0
        while len(haystack) - skip >= len(needle):
            if self.__same(haystack[skip:], needle, len(needle)):
                return skip
            skip = skip + t[ord(haystack[skip + len(needle) - 1])]
        return -1

    # def process(self, text: str, custom_idioms: Dict[str, str]) -> str:
    def process(self, text: str) -> str:
        patterns = self.patterns
        # patterns.update(custom_idioms)
        # patterns = custom_idioms
        # patterns.update(self.patterns)
        text = text.strip()
        text = ' '.join([t.lower() for t in text.split(' ')])
        data = text.split('\n')
        result = None
        for d in data:
            _d = d
            for pattern in patterns:
                pc = len(pattern)
                tmp = _d
                start = 0

                if not patterns[pattern]:
                    target = '_'.join(pattern.split(' '))
                elif patterns[pattern].isspace():
                    target = ''
                else:
                    target = patterns[pattern]

                while len(tmp) >= pc:
                    found = self.__search(pattern, tmp)
                    if found >= 0:
                        dis = len(_d) - len(tmp)
                        _d = _d[:dis + found] + target + _d[dis + found + pc:]
                        start = found + pc
                        tmp = tmp[start:]
                    else:
                        break
            # providing warnings to the use of pronouns
            if not _d.strip():
                continue
            # if _d[-1] == '.':
            #     arr = _d[:-1].split(' ')
            # else:
            #     arr = _d.split(' ')
            # indices = []
            # for pronoun in Pronouns:
            #     s = [i for i, x in enumerate(arr) if x.lower() == pronoun.lower()]
            #     if s:
            #         print('Warning: The use of pronoun (', pronoun, ') may not be '
            #                                                         'correctly '
            #                                                         'processed by the '
            #                                                         'NLP. Please '
            #                                                         'consider to '
            #                                                         'replace it with '
            #                                                         'proper noun.')
            #         indices += s
            # if indices:
            #     # for i, x in enumerate(arr):
            #     #     if i in indices:
            #     #         arr[i] = '**' + x + '**'
            #     s = ' '.join(arr) + '.'
            #     print(s)
            #     c = 0
            #     for i in range(len(s)):
            #         if s[i] == ' ':
            #             c += 1
            #             print('_', end='')
            #             continue
            #         if c in indices:
            #             print('^', end='')
            #         else:
            #             print('_', end='')
            result = _d
        if result and result[-1] == '.':
            result = result[:-1].strip()
            result += '.'
        return result

    def get_custom_idioms(self, method_name) -> Dict[str, str]:
        r = {}
        a = [i['term'] for i in self.data[method_name]['semantics']]
        c = [i['term'] for i in self.data[method_name]['defines']]
        b = [i for i in self.data[method_name]['eliminates']]
        for i in a:
            r[i] = ''
        for i in b:
            r[i] = ' '
        for i in c:
            r[i] = ''
        return r

    def get_specs(self, method_name) -> Dict[str, List[str]]:
        r = {'requires': self.data[method_name]['requires'], 'ensures': self.data[method_name]['ensures']}
        return r

    def get_semantics(self, method_name) -> List[str]:
        d = self.data[method_name]['semantics']
        for i in d:
            i['term'] = '_'.join(i['term'].split(' '))
        return [';'.join(i.values()) for i in d]

    def get_defined_terms(self, method_name) -> List[Dict[str, str]]:
        return self.data[method_name]['defines']

    def __add_si_to_patterns(self, si_lib: List[Dict]):
        for si in si_lib:  # type: Dict
            self.patterns[si['term']] = ''

    def __init__(self, java_file, std_si_lib_file=None, cont_si_lib=None):
        with open(java_file, 'r') as f:
            file = f.read()
        if cont_si_lib:
            with open(cont_si_lib, 'r') as f:
                cont_sis = yaml.full_load(f)
                self.__add_si_to_patterns(cont_sis)
        if std_si_lib_file:
            with open(std_si_lib_file, 'r') as f:
                std_sis = yaml.full_load(f)
                self.__add_si_to_patterns(std_sis)
                # for p in tmp.split('\n'):
                #     if p and len(p) > 1:
                #         if ';' in p:
                #             k = p.split(';')[0]
                #             v = p.split(';')[1]
                #         else:
                #             k = p
                #             v = ''
                #         self.patterns[k] = v
        # formatting method specifications (requires, ensures) in the data
        self.data = {}
        specs = file.split('\n')
        tmp = {'semantics': [], 'requires': [], 'ensures': [], 'eliminates': [], 'defines': []}
        for i, s in enumerate(specs):
            if r := re.findall(
                    r'^[\s]*(public|protected|private|static|\s)+(class)\s+(\w+) *(\{?|[^;])[\s]*$',
                    s, re.ASCII):
                r = r[0]
                continue
            elif r := re.findall(r'^[\s]*//\+[\s]*eliminates[\s]*"(.*)"[\s]*$', s, re.ASCII):
                r = r[0].strip()
                tmp['eliminates'].append(r)
            elif r := re.findall(r'^[\s]*//\+[\s]*defines[\s]*"(.*)"[\s]*,[\s]*"(.*)"[\s]*[\s]*$', s, re.ASCII):
                r = list(r[0])
                # if len(r) == 2:
                # TODO: extract defines to a single file
            elif r := re.findall(
                    r'^\s*//\+\s*semantics\s*\"(.*)\"\s*,\s*(.*)\s*,\s*(\d+)\s*,\s*(.*)\s*$',
                    s, re.ASCII):
                r = r[0]
                if len(r) != 4:
                    print('Syntax error in declaring semantics: ', s)
                    print('The declaration of semantics follow term, syntax, arity, semantic')
                    exit(1)
                elif not r[2].strip().isnumeric():
                    print('Syntax error in declaring semantics: ', s)
                    print('Expected number in the arity, but get ', r[2])
                    exit(1)
                elif r[1].strip().lower() not in PTBTagSet:
                    print('The syntax specified in ', s, ' is not a valid Penn Tree Bank category')
                    exit(1)
                tmp['semantics'].append(
                    {
                        'term': r[0].strip().replace('"', ''),
                        'syntax': r[1].strip(),
                        'arity': r[2].strip(),
                        'semantic': r[3].strip()
                    }
                )
            elif r := re.findall(r'^[\s]*//\+[\s]*define[\s]*"(.*)"[\s]*,[\s]*"(.*)"[\s]*$', s, re.ASCII):
                r = r[0]
                if len(r) != 2:
                    print('Syntax Error in declaring definition: ', s)
                    exit(1)
                elif self.__search(r[0].strip(), r[1].strip()) < 0:
                    print('Please mention the term in the definition, or we cannot refer the definition of the term.')
                    exit(2)
                else:
                    tmp['defines'].append(
                        {
                            'term': r[0].strip(),
                            'definition': r[1].strip()
                        }
                    )
            elif r := re.findall(r'^[\s]*//@[\s]*requires[\s]*\(\*(.*)\*\);[\s]*$', s, re.ASCII):
                tmp['requires'] += nltk.sent_tokenize(r[0])
            elif r := re.findall(r'^[\s]*//@[\s]*ensures[\s]*\(\*(.*)\*\);[\s]*$', s, re.ASCII):
                tmp['ensures'] += nltk.sent_tokenize(r[0])
            elif r := re.findall(
                    r'^[\s]*(public|protected|private|static|\s)+[\w\<\>\[\]]+\s+(\w+) *\([^\)]*\) *(\{?|[^;])[\s]*$',
                    s, re.ASCII):
                if r[0][1] in self.data:
                    print('Method with the same name is not supported in the current stage.')
                else:
                    self.data[r[0][1]] = tmp
                tmp = {'semantics': [], 'requires': [], 'ensures': [], 'eliminates': [], 'defines': []}
            else:
                continue


def main(javafile, sidb):
    contextual_si = []
    dst = get_package_global_info_from_javasrc(javafile)
    for name in dst:
        contextual_si.append({
            'term': name,
            'syntax': ['NN', 'NNS'],
            'arity': 1,
            'arguments': ['(*)'],
            'interpretation': name
        })
    yaml.dump(contextual_si, sys.stdout, sort_keys=False)
    # TODO: we need error handling in accessing the class name
    preprocessor = Preprocessor(javafile, sidb)
    specs = {'requires': [], 'ensures': []}
    for method in preprocessor.data:
        raw_specs = preprocessor.get_specs(method)
        if raw_specs['requires']:
            for r in raw_specs['requires']:
                specs['requires'].append(preprocessor.process(r))
        if raw_specs['ensures']:
            for e in raw_specs['ensures']:
                specs['ensures'].append(preprocessor.process(e))
    yaml_data = []
    if specs['requires']:
        for pre in specs['requires']:
            yaml_data.append({
                'type': 'precondition',
                'specification': pre
            })
    if specs['ensures']:
        for post in [i for i in specs['ensures'] if i]:
            yaml_data.append({
                'type': 'postcondition',
                'specification': post
            })
    yaml.dump(yaml_data, sys.stdout, sort_keys=False)


if __name__ == "__main__":
    # argv[1]: java file
    # argv[2]: idioms file
    # argv[3]: temporary folder located in the working directory
    # if sys.argv[1] and os.path.exists(sys.argv[1]) and \
    #         sys.argv[2] and os.path.exists(sys.argv[2]) and \
    #         sys.argv[3] and os.path.exists(sys.argv[3]):
    if len(sys.argv) == 2:
        main(sys.argv[1], None)
    elif sys.argv[1] and os.path.exists(sys.argv[1]) and \
            sys.argv[2] and os.path.exists(sys.argv[2]):
        main(sys.argv[1], sys.argv[2])
    else:
        print('Please check the input file path')
