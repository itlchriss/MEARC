import re
import os

# processing the text related to parameter, including the symbols, types that explicitly mentioned in the sentence
# processing the text related to method return, including the return type, text refer to the result, etc.


# these are java primitive and reference types
# we should extend this list to support more data types
    # Boolean = 0,
    # Byte = 1,
    # Char = 2,
    # Short = 3,
    # Integer = 4,
    # Long = 5,
    # Float = 6,
    # Double = 7
primitive_datatypes = ['boolean', 'byte', 'character', 'short', 'integer', 'long', 'float', 'double', ]
    # Array = 0,
    # String = 1,
    # Object = 2
reference_datatypes = ['array', 'string', 'object', 'list']
# datatypes = [
#     'integer', 'Integer', 'float', 'Float', 'double', 'Double', 'short', 'Short', 'long', 'Long', 'array', 'list', 'collection', 'arrays',
#     'string', 'strings', 'matrix', 'boolean'
#     ]


rulespath = './rules'
ALT_RULE_FILENAME = 'alt'

alt_rules = []

def _get_alt_rules():
    with open(os.path.join(rulespath, ALT_RULE_FILENAME)) as fp:
        f = fp.read()
    f = f.strip().split('\n')
    for record in f:
        target = record.split(':')[0]
        t = record.split(':')[1].strip()
        if "\," in t:
            t = t.replace("\,", "ESC_COMMA")
        alt = t.split(',')
        for a in alt:
            x = a.replace("ESC_COMMA", ",")
            alt_rules.append((x, target))

class ContextProcessor:

    def __init__(self) -> None:
        self.dynamic_si = {}
        _get_alt_rules()

    def _parameter_syntax_processor(self):
        # contextual_si = self.contextual_si
        sent = self.sent
        combined_datatypes = ['%s %s' % (p, r) for p in primitive_datatypes for r in reference_datatypes]
        for c in combined_datatypes:
            sent = re.sub(r'\s+' + c + r'\s+parameters\s+', ' type_' + c.replace(' ', '_') + '_ parameters ', sent)
            sent = re.sub(r'\s+' + c + r"\s+parameters's\s+", ' type_' + c.replace(' ', '_') + "_ parameters's ", sent)
            
            sent = re.sub(r'\s+' + c + r'\s+parameter\s+', ' type_' + c.replace(' ', '_') + '_ parameter ', sent)
            sent = re.sub(r'\s+' + c + r"\s+parameter's\s+", ' type_' + c.replace(' ', '_') + "_ parameter's ", sent)
            sent = re.sub(r'\s+' + c + r'\s+result\s+', ' type_' + c.replace(' ', '_') + '_ result ', sent)
            sent = re.sub(r'\s+' + c + r"\s+result's\s+", ' type_' + c.replace(' ', '_') + "_ result's ", sent) 
        
        for c in primitive_datatypes:
            sent = re.sub(r'\s+' + c + r'\s+parameters\s+', ' type_' + c.replace(' ', '_') + '_ parameters ', sent)
            sent = re.sub(r'\s+' + c + r'\s+parameter\s+', ' type_' + c.replace(' ', '_') + '_ parameter ', sent)
            sent = re.sub(r'\s+' + c + r'\s+result\s+', ' type_' + c.replace(' ', '_') + '_ result ', sent)

        for c in reference_datatypes:
            sent = re.sub(r'\s+' + c + r"\s+parameters\s+", ' type_' + c.replace(' ', '_') + "_ parameters ", sent)
            sent = re.sub(r'\s+' + c + r"\s+parameters's\s+", ' type_' + c.replace(' ', '_') + "_ parameters's ", sent)
            sent = re.sub(r'\s+' + c + r"\s+parameter\s+", ' type_' + c.replace(' ', '_') + "_ parameter ", sent)
            sent = re.sub(r'\s+' + c + r"\s+parameter's\s+", ' type_' + c.replace(' ', '_') + "_ parameter's ", sent)
            sent = re.sub(r'\s+' + c + r'\s+result\s+', ' type_' + c.replace(' ', '_') + '_ result ', sent)
            sent = re.sub(r'\s+' + c + r"\s+result's\s+", ' type_' + c.replace(' ', '_') + "_ result's ", sent)        
        
        # print('before cp: ', sent)
        # if r := re.findall('input\s+(%s)\s+`([a-zA-Z]+)`' % '|'.join(datatypes), sent, re.ASCII):
        #     for type, param in r:
        #         sent = re.sub('input\s+%s\s+`%s`' % (type, param), 'type_%s_ param_%s_' % (type, param), sent, re.ASCII)
        # elif r := re.findall(r'parameter (`[0-9a-zA-Z_]+`) and (`[0-9a-zA-Z_]+`)', sent, re.ASCII):
        # if r := re.findall('input\s+(%s)\s+`([a-zA-Z]+)`' % '|'.join(datatypes), sent, re.ASCII):
            # for type, param in r:
                # sent = re.sub('input\s+%s\s+`%s`' % (type, param), 'type_%s_ param_%s_' % (type, param), sent, re.ASCII)
        # elif r := re.findall(r'parameter (`[0-9a-zA-Z_]+`) and (`[0-9a-zA-Z_]+`)', sent, re.ASCII):
        if r := re.findall(r'parameter (`[0-9a-zA-Z_]+`) and (`[0-9a-zA-Z_]+`)', sent, re.ASCII):
            # the case of composite subject/object with two parameters
            # we should convert both of them
            for param in r[0]:
                pattern = 'param_%s_' % param.replace('`', '')
                sent = sent.replace(param, pattern)
        elif r := re.findall(r'parameters (`[0-9a-zA-Z_]+`) and (`[0-9a-zA-Z_]+`)', sent, re.ASCII):
            # the case of composite subject/object with two parameters
            # we should convert both of them
            for param in r[0]:
                pattern = 'param_%s_' % param.replace('`', '')
                sent = sent.replace(param, pattern)
        elif r := re.findall(r'parameter (`[0-9a-zA-Z_]+`)', sent, re.ASCII):
            for param in r:
                pattern = 'param_%s_' % param.replace('`', '')
                sent = sent.replace(param, pattern)
        elif r := re.findall(r'(parameter [0-9a-zA-Z_]+)', sent, re.ASCII):
            for param in r:
                pattern = 'param_%s_' % param.replace('`', '').replace('parameter ', '')
                sent = sent.replace(param, pattern)
        self.sent = sent
        # print('cp: ', sent)
        
        # NOTE: there can be words representing types, but there are no keywords such as 'input', 'parameter'
        #       after doing the above operations, if the words listed in the datatypes have no conflicts with the parameter symbols, 
        #       we also treat them as types
        # words = sent.split(' ')
        # for d in datatypes:
        #     indices = [i for i, w in enumerate(words) if w == d]
        #     if indices:
        #         for index in indices:
        #             words[index] = 'type_' + words[index] + '_'
        
        
        # self.sent = ' '.join(words)
        
        # NOTE: because LLM has already recognised the parameter. If 'param_' exists, it means that LLM has provided the parameter information and we have tackled it.
        #       therefore, in this case, the word 'parameter' can be skipped.
        if 'param_' in self.sent:
            self.sent = self.sent.replace('parameters', '')
            self.sent = self.sent.replace('parameter', '')            

    def _synonym_syntax_preprocessor(self):
        if self.sent[-1] == '.':
            self.sent = self.sent[:-1]        
        for rule in alt_rules:            
            self.sent = self.sent.replace(rule[0], ' ' + rule[1] + ' ')
            
    def _symbol_syntax_preprocessor(self):
        if self.sent[-1] == '.':
            self.sent = self.sent[:-1]  
        self.sent = re.sub(r"','", ' comma ', self.sent)
        self.sent = re.sub(r"'\?'", ' questionmark ', self.sent)
        self.sent = re.sub(r"'\*'", ' asterisk ', self.sent)
        self.sent = re.sub(r"'\.'", ' period ', self.sent)
        self.sent = re.sub(r"`*'\s*\(\s*'`*", 'leftp', self.sent)
        self.sent = re.sub(r"`*'\s*\)\s*'`*", 'rightp', self.sent)
        self.sent = re.sub(r"`*'\s*\[\s*'`*", 'leftbp', self.sent)
        self.sent = re.sub(r"`*'\s*\]\s*'`*", 'rightbp', self.sent)
        self.sent = re.sub(r"`*'\s*\{\s*'`*", 'leftb', self.sent)
        self.sent = re.sub(r"`*'\s*\}\s*'`*", 'rightb', self.sent)

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
        self._symbol_syntax_preprocessor()
        self._synonym_syntax_preprocessor()
        self._parameter_syntax_processor()        
        return self.sent