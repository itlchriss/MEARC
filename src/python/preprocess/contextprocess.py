import re
import os

# processing the text related to parameter, including the symbols, types that explicitly mentioned in the sentence
# processing the text related to method return, including the return type, text refer to the result, etc.


# these are java primitive and reference types
# we should extend this list to support more data types
datatypes = ['integer', 'Integer', 'float', 'Float', 'double', 'Double', 'short', 'Short', 'long', 'Long', 'array', 'list', 'collection']


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
        self.contextual_si = {}
        _get_alt_rules()

    def _parameter_syntax_processor(self):
        contextual_si = self.contextual_si
        sent = self.sent

        if r := re.findall('input\s+(%s)\s+`(.*)`' % '|'.join(datatypes), sent, re.ASCII):
            for type, param in r:
                sent = re.sub('input\s+%s\s+`%s`' % (type, param), 'TYPE_%s PARAM_%s' % (type, param), sent, re.ASCII)
                # contextual_si['PARAM_type_%s_sym_%s' % (type, param)] = param
                contextual_si['PARAM_%s' % (param)] = param
        elif r := re.findall(r'`.*`', sent, re.ASCII):
            for param in r:
                pattern = 'PARAM_%s' % param.replace('`', '')
                sent = sent.replace(param, pattern)
                contextual_si['PARAM_%s' % param] = param
        self.sent = sent

    def _synonym_syntax_preprocessor(self):
        for rule in alt_rules:
            self.sent = self.sent.replace(rule[0], rule[1])

    def run(self, sent: str) -> str:
        self.sent = sent
        self._parameter_syntax_processor()
        self._synonym_syntax_preprocessor()
        return self.sent