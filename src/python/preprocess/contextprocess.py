import re

# processing the text related to parameter, including the symbols, types that explicitly mentioned in the sentence
# processing the text related to method return, including the return type, text refer to the result, etc.

alt_rules = [
    ('The method returns', 'The result is'),
    ('The method generates', 'The result is'),
    ('The method yields', 'The result is'),
    ('The method produces', 'The result is'),
    ('where', ''),
    ('any', ''),
    ('represents', 'is'),
    ('distinct', 'unique')
]

# these are java primitive and reference types
# we should extend this list to support more data types
datatypes = ['integer', 'Integer', 'float', 'Float', 'double', 'Double', 'short', 'Short', 'long', 'Long', 'array', 'list', 'collection']

class ContextProcessor:

    def __init__(self) -> None:
        self.contextual_si = {}

    def _parameter_syntax_processor(self):
        contextual_si = self.contextual_si
        sent = self.sent

        if r := re.findall('input\s+(%s)\s+`(.*)`' % '|'.join(datatypes), sent, re.ASCII):
            for type, param in r:
                sent = re.sub('input\s+%s\s+`%s`' % (type, param), 'PARAM_type_%s_sym_%s' % (type, param), sent, re.ASCII)
                contextual_si['PARAM_type_%s_sym_%s' % (type, param)] = param
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