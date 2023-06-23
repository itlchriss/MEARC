import os
import re
import sys
from typing import List, Dict, Tuple
from dstbuilder import get_package_global_info_from_javasrc, \
    JavaTypes, get_constants_from_javasrc, get_single_method_parameters
import yaml
from copy import deepcopy

import javalang
import nltk
import spacy
import time
from optparse import OptionParser

optparser = OptionParser()
optparser.add_option("-d", "--debug", dest="debuglevel", help="debug level. 1 = INFO, 2 = DEBUG")
optparser.add_option("-f", "--file", dest="prog_path", help="path of the program file")
optparser.add_option("-o", "--target", dest="target_path", help="[optional]path for saving the output")
optparser.add_option("-p", "--profquery", dest="prof_query_path", help="[optional]path for professional query when query file presents")
optparser.add_option("-q", "--query",
                     dest="query_path", help="[optional]path of the queries that at saved in separate file")
optparser.add_option("-s", "--silib", dest="silib_path", help="[optional]path of the SI Library")

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
        'will be': 'is',
        # 'is equal to': 'is',
        'will': ' ',
        'degrees': ' ',
        'degree': ' ',
        'is between': 'ranges between',
        'all elements in': 'every element of'
    }
    function_si = []
    phrases = [
        {
            'phrase': 'from (x) to (y)',
            'args': {'(x)': ['CD', 'PARAM'], '(y)': ['CD', 'PARAM']},
            'si_args': ['(Subj)'],
            'interpretation': '(x) < (Subj) < (y)',
            'type': int(JavaTypes.JML_expression_result)
        },
        {
            'phrase': '(z) from (x) to (y)',
            'args': {'(x)': ['CD', 'PARAM'], '(y)': ['CD', 'PARAM'], '(z)': ['PARAM']},
            'interpretation': '(x) < (z) < (y)',
            'type': int(JavaTypes.JML_expression_result)
        },
        {
            'phrase': '(x) ± (y)',
            'args': {'(x)': ['CD'], '(y)': ['CD']},
            'si_args': ['(Subj)'],
            'interpretation': '(x) - (y) < (Subj) < (x) + (y)',
            'type': int(JavaTypes.JML_expression_result)
        },
        {
            'phrase': 'between (x) and (y)',
            'args': {'(x)': ['CD'], '(y)': ['CD']},
            'si_args': ['(Subj)'],
            'interpretation': '(x) < (Subj) < (y)',
            'type': int(JavaTypes.JML_expression_result)
        },
        {
            'phrase': '(x) to (y)',
            'args': {'(x)': ['CD', 'PARAM'], '(y)': ['CD', 'PARAM']},
            'si_args': ['(Subj)'],
            'interpretation': '(x) < (Subj) < (y)',
            'type': int(JavaTypes.JML_expression_result)
        }
    ]
    operators = {
        '>=': 'greater than or equal to',
        '=': 'equal',
        '<=': 'less than or equal to',
        '<': 'less than',
        '>': 'greater than'
    }
    # word: [position, phrase]
    word_with_position = {
        'returns': [0, 'The result is']
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
        if str2 == 'index':
            print(str1, str2)
        while str1[i].lower() == str2[i].lower():
            if i == 0:
                return True
            i = i - 1
        return False

    def __search(self, needle: str, haystack: str):
        # t = self.__preprocess(needle)
        # if needle == 'index':
        #     print(t)
        # skip = 0
        # while len(haystack) - skip >= len(needle):
        #     if self.__same(haystack[skip:], needle, len(needle)):
        #         return skip
        #     skip = skip + t[ord(haystack[skip + len(needle) - 1])]
        data = haystack.split(' ')
        pattern = needle.split(' ')
        for i in range(len(data)):
            match = True
            for j, w2 in enumerate(pattern):
                if i + j >= len(data):
                    match = False
                    break
                if data[i + j].strip(',') != w2:
                    match = False
                    break
            if match:
                prefix = ' '.join(data[:i])
                if not prefix:
                    return 0
                else:
                    return len(prefix) + 1
        return -1

    def __process_function__(self, text: str) -> str:
        _text = text
        if _text[-1] == '.':
            _text = _text[:-1]
        data = _text.split(' ')
        tmp = []
        i = 0
        while i < len(data):
            token = data[i]
            if r := re.findall(r'^\((.*)\)$', token, re.ASCII):
                r = r[0]
                # TODO: we have to process everything that can be encountered in java expression. or we can restrict to accept a subset
                # after applying the replacement rules, we have to add an SI
                tmp.append(r)
                interpretation = r
                if '-' in r:
                    expr_tokens = list(filter(None, re.split(r'(\W)', r.strip())))
                    ri = []
                    for j, t in enumerate(expr_tokens):
                        if t == 'result':
                            ri.append('\\result')
                        elif t == '-':
                            ri.append(t)
                            expr_tokens[j] = 'dash'
                        elif t:
                            ri.append(t)
                        else:
                            continue
                    interpretation = ''.join(ri)
                    r = '_'.join(expr_tokens)
                self.function_si.append({
                    'term': r.replace('.', '_DOT'),
                    'syntax': ['NN', 'NNS', 'NNP'],
                    'arity': 1,
                    'arguments': '(*)',
                    'interpretation': interpretation,
                    'type': -1,
                })
            elif (token.lower() == 'parameter' or token.lower() == 'input') \
                    and i + 1 < len(data) \
                    and (data[i + 1] in self.dst_names or data[i + 1].strip('\'s').strip(',').strip() in self.dst_names):
                _t = token + '_' + data[i + 1]
                _type = -1
                for d in self.dst:
                    if isinstance(d, tuple) and d[0] == data[i + 1].strip('\'s').strip(',').strip():
                        _type = d[1]
                        break
                    elif d == data[i + 1].strip('\'s').strip(',').strip():
                        _type = -1
                        break
                tmp.append(_t)
                if not [1 for si in self.function_si if si['term'] == _t.lower()]:
                    self.function_si.append({
                        'term': _t.lower().strip(','),
                        'syntax': ['NN', 'NNS', 'NNP'],
                        'arity': 1,
                        'arguments': '(*)',
                        'interpretation': data[i + 1].strip('\'s').strip(',').strip(),
                        'type': _type,
                    })
                # skip the data[i + 1] because it has been processed
                i = i + 1
            elif token.lower() == 'if' and data[i + 1].lower() == 'and' and data[i + 2].lower() == 'only' and data[i + 3].lower() == 'if':
                _t = 'if_only'
                tmp.append(_t)
                if not [1 for si in self.function_si if si['term'] == _t.lower()]:
                    self.function_si.append({
                        'term': _t.lower(),
                        'syntax': ['RB'],
                        'arity': 2,
                        'arguments': ['(*)', '(*)'],
                        'interpretation': '<==>',
                        'type': 2,
                    })
                i = i + 3
            elif token in self.operators:
                _t = self.operators[token]
                tmp.append(_t)
            elif [1 for w in self.word_with_position if w == token.lower() and i == self.word_with_position[w][0]]:
                _t = self.word_with_position[token.lower()][1]
                tmp.append(_t)
            else:
                tmp.append(token)
            i = i + 1        
        return ' '.join(tmp)

    #TODO we have to consider after processed i, may result in i - 1 pattern. so we have to retry all patterns
    def __process_phrases__(self, text: str) -> str:
        data = text.split(' ')
        i = 0
        tmp = []
        tags = nltk.pos_tag(data)
        while i < len(data):
            # match = True
            for phrase_rule in self.phrases:
                match = True
                _p_token_list = phrase_rule['phrase'].split(' ')
                _args = phrase_rule['args']
                _interpretation = phrase_rule['interpretation']
                for j in range(len(_p_token_list)):
                    # print(tags[i + j], _interpretation)
                    # if the phrase has a parameter, check the parameter type and the current token, the type must be equal to the type of the token
                    if i + j >= len(data):
                        match = False
                        break
                    if _p_token_list[j][0] == '(':
                        _arg_type = _args[_p_token_list[j]]
                        _tag = tags[i + j]
                        if _tag[1] not in _arg_type and _tag[0] not in self.dst_names:
                            match = False
                            break
                        _interpretation = _interpretation.replace(_p_token_list[j], _tag[0])
                    elif _p_token_list[j] != data[i + j]:
                        match = False
                        break
                    
                if match:                    
                    _t = '_'.join(data[i:i+len(_p_token_list)])
                    tmp.append(_t)
                    i = i + len(_p_token_list)

                    si = {
                        'term': _t.lower().replace('.', '_DOT').replace('-', '_dash'),
                        # only direct semantics are acceptable
                        'syntax': ['NN', 'NNP', 'NNS', 'NNPS', 'CD'],
                        'arity': 1,
                        #TODO if si_args in the dict presents, the arguments must specify the si_args
                        # otherwise, asterisk is used
                        'arguments': ['(*)'],
                        'interpretation': _interpretation,
                        'type': phrase_rule['type'],
                    }
                    if 'si_args' in phrase_rule:
                        si['arguments'] = deepcopy(phrase_rule['si_args'])
                    # add the si
                    self.function_si.append(si)                                        
                    break
            if not match:
                tmp.append(data[i])
                i = i + 1
        return ' '.join(tmp)


    def process(self, text: str) -> str:
        text = self.__process_function__(text)
        # text = self.__process_phrases__(text)
        # if text[-1] != '.':
        #     text = text + '.'
        for name in self.dst_names:
            self.patterns[name.replace('_', ' ').strip()] = name
        # remove all single word pattern, because we don't have to treat them in this function        
        patterns = self.patterns        
        patterns = {k:v for k,v in patterns.items() if len(k.split(' ')) > 1 or v}
        # _patterns_list = sorted(list(patterns.items()), key= lambda key: len(key[0].split(' ')))
        # patterns = {ele[0]: ele[1] for ele in _patterns_list}
        text = text.strip()        
        # text = ' '.join([t.lower() for t in text.split(' ')])
        # data = text.split('\n')
        # text = text.split('\n')[0]
        data = []
        for t in text.split():
            if not t in self.dst_names:
                data.append(t.lower())
            else:
                data.append(t)
        result = None
        data = ' '.join(data)
        data = data.split('\n')
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
        result = self.__process_phrases__(result)
        if result and result[-1] == '.':
            result = result[:-1].strip()
            result += '.'
        else:
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

    def __init__(self, java_file, std_si_lib_file=None, cont_si_lib=None, dst=None):
        if dst:
            self.dst = dst
            self.dst_names = []
            for d in dst:
                if isinstance(d, tuple):
                    self.dst_names.append(d[0])
                else:
                    self.dst_names.append(d)
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
                elif r[1].strip().lower() not in PTBTagSet:
                    print('The syntax specified in ', s, ' is not a valid Penn Tree Bank category')
                    exit(1)
                tmp['semantics'].append(
                    {
                        'term': r[0].strip().replace('"', ''),
                        'syntax': [s.upper() for s in r[1].strip().split(',')],
                        'arity': r[2].strip(),
                        'arguments': [('(%s)' % i.strip()) for i in r[3].strip().split(',')],
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


def main(javafilepath: str, silibpath: str, targetpath: str = None, querypath: str = None, profquerypath:str = None, using_gpt=True):
    contextual_si = []
    dst = get_package_global_info_from_javasrc(javafilepath)
    for name in dst:
        interpretation = None
        _type = -1
        if not isinstance(name, tuple):
            interpretation = name
        elif len(name) == 2:
            interpretation = name[0]
            _type = int(name[1])
        else:
            interpretation = name[2]
            _type = int(name[1])
        contextual_si.append({
            'term': name[0] if isinstance(name, tuple) else name,
            'syntax': ['NN', 'NNS', 'NNP', 'NNPS'],
            'arity': 1,
            'arguments': ['(*)'],
            'interpretation': interpretation,
            'type': _type if isinstance(name, tuple) else -1
        })
    # TODO: we need error handling in accessing the class name
    preprocessor = Preprocessor(javafilepath, silibpath, dst=dst)
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
        if raw_specs['requires']:
            for r in raw_specs['requires']:
                specs['requires'].append(preprocessor.process(r))
        if raw_specs['ensures']:
            for e in raw_specs['ensures']:
                specs['ensures'].append(preprocessor.process(e))
    yaml_data = []
    #############################################################
    # 20230525 in Portugal
    # Adding the GPT optional preprocessing
    if querypath:
        from gpt_helpers import Gpt_preprocessing
        features = list(get_single_method_parameters(javafilepath))
        classes = list(get_constants_from_javasrc(javafilepath))
        with open(querypath, 'r') as fp:
            lines = fp.readlines()
        pq = None
        if profquerypath:
            with open(profquerypath, 'r') as fp:
                pq = fp.read()
        #########################################################
        # 20230623 in Macau
        # Reading the key of openai
        # the key is fixed to place in somewhere the user can read
        # but not polluting the software
        with open('../openai.key', 'r') as fp:
            key = fp.read().strip()
        #########################################################
        # 20230623 in Macau
        # Reading a relationship file to specify the relationship between terms to train GPT 
        with open('./professional_queries/relationships.txt', 'r') as fp:
            text = fp.read()
        raw = text.split('\n')
        relationships = []
        for r in raw:
            p = r.split(',')
            relationships.append(p)
        #########################################################
        gpt_turbo_count = 0
        nlp = spacy.load("en_core_web_sm")
        for i, line in enumerate(lines):
            runner = Gpt_preprocessing(features=features, classes=classes, professional_query=pq, 
                                       openai_key=key, relationships=relationships, debug=False)
            runner.count = gpt_turbo_count
            print('input line: ', line.strip())
            spec = runner.process(line.strip())            
            for post in spec:    
                for text in spec[post]:
                    # each class can have several sentences, or no sentences
                    print(text)
                    # we only retrieve those with sentences
                    doc = nlp(text)
                    for s in doc.sents:
                        if s.sent.text and len(s.sent.text.strip()) > 2:
                            print(s.sent.text)
                            #############################################################
                            # 20230623 Added in Macau
                            # write spec pairs into the yaml file
                            #############################################################
                            yaml_data.append({
                                'type': 'pair',
                                'precondition': s.sent.text,
                                'postcondition': post
                            })
            if i != len(lines) - 1:
                print('Finished a line. Pausing for 60 seconds...')
                time.sleep(60)
    #############################################################
    contextual_si += preprocessor.function_si
    
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
    # yaml.dumper.SafeDumper.ignore_aliases = lambda self, data: True

    if not targetpath:
        # yaml.dump(contextual_si, sys.stdout, sort_keys=False, allow_unicode=True, Dumper=yaml.dumper.SafeDumper)
        # yaml.dump(yaml_data, sys.stdout, sort_keys=False, allow_unicode=True, Dumper=yaml.dumper.SafeDumper)
        yaml.dump(contextual_si, sys.stdout, sort_keys=False, allow_unicode=True)
        yaml.dump(yaml_data, sys.stdout, sort_keys=False, allow_unicode=True)
    else:
        name = javafilepath.split('/')[-1].split('.')[0]
        si_path = ('%s/%s.si.yml' % (targetpath, name))
        spec_path = ('%s/%s.conditions.yml' % (targetpath, name))

        with open(si_path, 'w+') as fp:
            yaml.dump(contextual_si, fp, sort_keys=False, allow_unicode=True)
        with open(spec_path, 'w+') as fp:
            yaml.dump(yaml_data, fp, sort_keys=False, allow_unicode=True)


if __name__ == "__main__":
    (options, args) = optparser.parse_args()
    if not options.prog_path:
        print('Please provide the java source file path')
        exit(1)
    else:
        main(javafilepath=options.prog_path,
             silibpath=options.silib_path, targetpath=options.target_path, querypath=options.query_path, profquerypath=options.prof_query_path)
    # if len(sys.argv) == 5:
    #     main(javafilepath=sys.argv[1], sidbpath=sys.argv[2], targetpath=sys.argv[3], querypath=sys.argv[4])
    # elif len(sys.argv) == 4:
    #     main(javafilepath=sys.argv[1], sidbpath=sys.argv[2], targetpath=sys.argv[3])
    # elif sys.argv[1] and os.path.exists(sys.argv[1]) and \
    #         sys.argv[2] and os.path.exists(sys.argv[2]):
    #     main(sys.argv[1], sys.argv[2])
    # else:
    #     print('Please check the input file path')
