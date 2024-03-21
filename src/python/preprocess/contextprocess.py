import re
import os

# processing the text related to parameter, including the symbols, types that explicitly mentioned in the sentence
# processing the text related to method return, including the return type, text refer to the result, etc.


# these are java primitive and reference types
# we should extend this list to support more data types
datatypes = [
    'integer', 'Integer', 'float', 'Float', 'double', 'Double', 'short', 'Short', 'long', 'Long', 'array', 'list', 'collection', 'arrays',
    'string', 'strings', 'matrix', 'boolean'
    ]


rulespath = './rules'
ALT_RULE_FILENAME = 'alt'

alt_rules = []

def _get_alt_rules():
    with open(os.path.join(rulespath, ALT_RULE_FILENAME)) as fp:
        f = fp.read()
    f = f.strip().split('\n')
    for record in f:
        target = record.split(':')[0]
        alt = (record.split(':')[1].strip()).split(',')
        for a in alt:
            alt_rules.append((a, target))

class ContextProcessor:

    def __init__(self) -> None:
        self.dynamic_si = {}
        _get_alt_rules()

    def _parameter_syntax_processor(self):
        # contextual_si = self.contextual_si
        sent = self.sent

        if r := re.findall('input\s+(%s)\s+`(.*)`' % '|'.join(datatypes), sent, re.ASCII):
            for type, param in r:
                sent = re.sub('input\s+%s\s+`%s`' % (type, param), 'type_%s_ param_%s_' % (type, param), sent, re.ASCII)
                # contextual_si['PARAM_type_%s_sym_%s' % (type, param)] = param
                # contextual_si['param_%s' % (param)] = param
        elif r := re.findall(r'`[0-9a-zA-Z_]+`', sent, re.ASCII):
            for param in r:
                pattern = 'param_%s_' % param.replace('`', '')
                sent = sent.replace(param, pattern)
                # contextual_si['param_%s' % param] = param
        self.sent = sent
        
        # NOTE: there can be words representing types, but there are no keywords such as 'input', 'parameter'
        #       after doing the above operations, if the words listed in the datatypes have no conflicts with the parameter symbols, 
        #       we also treat them as types
        words = sent.split(' ')
        for d in datatypes:
            indices = [i for i, w in enumerate(words) if w == d]
            if indices:
                for index in indices:
                    words[index] = 'type_' + words[index] + '_'
        self.sent = ' '.join(words)
        
        # NOTE: because LLM has already recognised the parameter. If 'param_' exists, it means that LLM has provided the parameter information and we have tackled it.
        #       therefore, in this case, the word 'parameter' can be skipped.
        if 'param_' in self.sent:
            self.sent = self.sent.replace('parameter', '');

    def _synonym_syntax_preprocessor(self):
        for rule in alt_rules:
            self.sent = self.sent.replace(rule[0], rule[1])

    possessable_terms = ['length']

    # converting 'length of x' to 'x's length'
    def _of_2_possesive(self):
        arr = self.sent.split(' ')
        while 'of' in arr:
            index = arr.index('of')
            if arr[index - 2].lower() != 'the' or not arr[index - 1] in self.possessable_terms:
                break
            t = arr[index + 1] + "'s " + arr[index - 1]
            arr = arr[:index - 1] + [t] + arr[index + 2:]
        self.sent = ' '.join(arr)
            

    def run(self, sent: str) -> str:
        self.sent = sent        
        self._synonym_syntax_preprocessor()
        self._parameter_syntax_processor()
        return self.sent