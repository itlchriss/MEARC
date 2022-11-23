import os
import re
import sys
from typing import List, Dict, Tuple
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
        'will': ' ',
        'all elements in': 'every element of'
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

    def process(self, text: str) -> str:
        patterns = self.patterns
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
                        if len(_d) >= (dis + found) > 0 and _d[:dis + found][-1] == '_':
                            break
                        _d = _d[:dis + found] + target + _d[dis + found + pc:]
                        start = found + pc
                        tmp = tmp[start:]
                    else:
                        break
            # providing warnings to the use of pronouns
            if not _d.strip():
                continue            
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

    def get_semantics(self, method_name=None) -> List[Dict]:
        if not method_name:
            d = self.data[next(iter(self.data))]['semantics']
        else:
            d = self.data[method_name]['semantics']
        return d

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
                    r'^\s*//@\s*semantics\s*\"(.*)\"\s*,\s*\[(.*)\]\s*,\s*(\d+)\s*,\s*\[(.*)\]\s*,\s*(.*)\s*$',
                    s, re.ASCII):
                r = r[0]
                if len(r) != 5:
                    print('Syntax error in declaring semantics: ', s)
                    print('The declaration of semantics follow term, syntax, arity, semantic')
                    exit(1)
                elif not r[2].strip().isnumeric():
                    print('Syntax error in declaring semantics: ', s)
                    print('Expected number in the arity, but get ', r[2])
                    exit(1)
                elif [ptb for ptb in r[1].split(',') if ptb.strip().lower() not in PTBTagSet]:                                    
                    print('The syntax specified in ', s, ' is not a valid Penn Tree Bank category')
                    exit(1)
                term = r[0].strip().replace('"', '')
                term = re.sub(r'\s+', '_', term)    
                tmp['semantics'].append(
                    {
                        'term': term,
                        'syntax': [s.upper().strip() for s in r[1].strip().split(',')],
                        'arity': r[2].strip(),
                        'arguments': ['%s' % i.strip() for i in r[3].strip().split(',')],
                        'interpretation': r[4].strip()
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
        self.__add_si_to_patterns(self.get_semantics())
        # data = self.data
        # for method in self.data:
        #     specs = self.get_specs(method)
        #     for spec_type in specs:
        #         conds = specs[spec_type]
        #         [self.__generate_si_from_grammar_rules__(cond) for cond in conds]

    grammar_rule_data = [
        [[('from', 'IN'), ('', 'CD'), ('to', 'TO'), ('', 'CD')], ['x'], '(_) <= (x) <= (_)']
    ]

    def __tag_list_comparator__(self, a: List[Tuple[str, str]], b: List[Tuple[str, str]]) -> bool:
        for i in range(len(a)):
            word_a, syntax_a = a[i]
            word_b, syntax_b = b[i]
            if not(syntax_a == syntax_b and (word_a == word_b or not word_b and word_a)):
                return False
        return True

    def generate_si_from_grammar_rules(self, sent: str):
        tokens = nltk.word_tokenize(sent)
        tags = nltk.pos_tag(tokens)
        si_list = []
        for data in self.grammar_rule_data:
            rule = data[0]
            args = data[1]            
            si = data[2]
            for i in range(len(tags) - len(rule) + 1):
                if self.__tag_list_comparator__(tags[i: i + len(rule)], rule):
                    index = [i for i in range(len(rule)) if not rule[i][0]]
                    _t = [rule[i][0] for i in range(len(rule))]
                    _si = si
                    for indice in index:
                        _si = _si.replace('(_)', tags[i: i + len(rule)][indice][0], 1)
                        _t[indice] = tags[i: i + len(rule)][indice][0]
                    _t = ('_'.join(_t)).replace('.', '_DOT')
                    sent = sent.replace(' '.join(tokens[i: i + len(rule)]), _t)                                   
                    si_list.append({
                        'term': _t,
                        'syntax': ['NN', 'NNS', 'NNP'],
                        'arity': 1,
                        'arguments': ['(%s)' % arg for arg in args],
                        'interpretation': _si,
                        'type': -1
                    })
        return si_list, sent


def main(javafile, sidb, targetpath = None):
    contextual_si = []
    dst = get_package_global_info_from_javasrc(javafile)
    for name in dst:
        contextual_si.append({
            'term': name[0] if isinstance(name, tuple) else name,
            'syntax': ['NN', 'NNS'],
            'arity': 1,
            'arguments': ['(*)'],
            'interpretation': name[0] if isinstance(name, tuple) else name,
            'type': name[1] if isinstance(name, tuple) else -1
        })
    # TODO: we need error handling in accessing the class name
    preprocessor = Preprocessor(javafile, sidb)
    annotations = preprocessor.get_semantics()
    if annotations:
        for d in annotations:
            contextual_si.append({
                'term': d['term'],
                'syntax': d['syntax'],
                'arity': d['arity'],
                'arguments': d['arguments'],
                'interpretation': d['interpretation']
            })
    specs = {'requires': [], 'ensures': []}
    for method in preprocessor.data:
        raw_specs = preprocessor.get_specs(method)
        for key in raw_specs:
            for r in raw_specs[key]:
                tmp = preprocessor.process(r)
                specs[key].append(tmp)
                g_si, tmp = preprocessor.generate_si_from_grammar_rules(tmp)
                contextual_si += g_si
        # if raw_specs['requires']:
        #     for r in raw_specs['requires']:
        #         tmp = preprocessor.process(r)
        #         specs['requires'].append(tmp)
        #         g_si, tmp = preprocessor.generate_si_from_grammar_rules(tmp)
        #         contextual_si += g_si
        # if raw_specs['ensures']:
        #     for e in raw_specs['ensures']:
        #         tmp = preprocessor.process(e)
        #         g_si, tmp = preprocessor.generate_si_from_grammar_rules(tmp)
        #         contextual_si += g_si
        #         specs['ensures'].append(tmp)                
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
    if not targetpath:
        yaml.dump(contextual_si, sys.stdout, sort_keys=False)
        yaml.dump(yaml_data, sys.stdout, sort_keys=False)
    else:
        name = javafile.split('/')[-1].split('.')[0]
        si_path = ('%s/%s.si.yml' % (targetpath, name))
        spec_path = ('%s/%s.conditions.yml' % (targetpath, name))
        with open(si_path, 'w+') as fp:
            yaml.dump(contextual_si, fp, sort_keys=False)
        with open(spec_path, 'w+') as fp:
            yaml.dump(yaml_data, fp, sort_keys=False)

if __name__ == "__main__":
    # argv[1]: java file
    # argv[2]: idioms file
    # argv[3]: temporary folder located in the working directory
    # if sys.argv[1] and os.path.exists(sys.argv[1]) and \
    #         sys.argv[2] and os.path.exists(sys.argv[2]) and \
    #         sys.argv[3] and os.path.exists(sys.argv[3]):
    if len(sys.argv) == 2:
        main(sys.argv[1], None)
    elif len(sys.argv) == 4:
        main(sys.argv[1], sys.argv[2], sys.argv[3])
    elif sys.argv[1] and os.path.exists(sys.argv[1]) and \
            sys.argv[2] and os.path.exists(sys.argv[2]):
        main(sys.argv[1], sys.argv[2])
    else:
        print('Please check the input file path')
