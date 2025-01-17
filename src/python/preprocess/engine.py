from .contextprocess import ContextProcessor
from .mrrepairprocess import RepairProcessor
from typing import Tuple, Dict
import re


def __fix_to_cases__(sent: str) -> Tuple[str, Dict[str, str]]:           
    # repeated 'to' is replaced as a single 'to'
    sent = re.sub(r'(to\s+)+', 'to ', sent)
    sent = re.sub(r'(is\s+)+', 'is ', sent)
    sent = re.sub(r'is\s+is', 'is ', sent)
    sent = re.sub(r'are\s+is', 'are ', sent)
    sent = re.sub(r'to\s+to', 'to ', sent)
    sent = re.sub(r'\s+or\s+or\s+', ' or ', sent)
    sent = re.sub(r'is\snot\sis', 'is not', sent)
    exprs = {}
    # for all remaining strings in quotes (``), we treat them as expressions and separatedly stored
    # print(sent)
    if r := re.findall(r'(`[0-9 <>\-\+\*!,a-zA-Z\[\]=\.\^\(\)\%\|\/_\'{}]+`)', sent):        
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['expr_' + index] = e
            sent = sent.replace(e, ' expr_' + index, 1)
    if r := re.findall(r'((\'[^ ]+\')\s+or\s+(\'[^ ]+\')\s+characters)', sent):
        r = r[0]
        s = r[0]
        # TODO: need to fix here. to introduce character type here
        _s = s.replace('characters', '')
        # sent = sent.replace(s, _s)
        t = r[1:]
        for i, e in enumerate(t):
            index = chr(i + 97)
            exprs['chrx_' + index] = e
            _s = _s.replace(e, ' chrx_' + index, 1)
        sent = sent.replace(s, _s)
    if r := re.findall(r'(\'[^, ]+\')', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['chry_' + index] = e
            sent = sent.replace(e, 'the chry_' + index, 1)
    if r := re.findall(r'(\"[^, ]+\")', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['stry_' + index] = e
            sent = sent.replace(e, ' stry_' + index, 1)
    if r := re.findall(r'(\"[a-zA-Z, ]+\")', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['strz_' + index] = e
            sent = sent.replace(e, ' strz_' + index, 1)
    if r := re.findall(r'(\"[^,]+\")', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['strl_' + index] = e
            sent = sent.replace(e, ' strl_' + index, 1)
    if r := re.findall(r'(\".*\")', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            v = e
            if ',' in v:
                v = v.replace(' ', '')
            exprs['strkk_' + index] = v
            sent = sent.replace(e, ' strkk_' + index, 1)
    words = sent.split(' ')
    targets = {}
    for w in words:
        if "^" in w:
            targets[w] = w.replace("^", "_pow_")
    for k in targets.keys():
        sent = sent.replace(k, targets[k])
    
    # fixing the case that the NLP does not distribute the concept of composite nouns to the connected composite objects where the conjunctive is 'or'
    # for instance, A or B noun, such noun should be a concept for both A and B, however, the output MR only provides it to B.
    types = ['characters', 'strings']
    if r := re.findall(r'(str_[^ ]+)\s+(or)\s+(str_[^ ]+)\s+(\b(?:{})\b)'.format('|'.join(types)), sent):
        t = r[0]        
        # reducing the plural form to singular form
        # TODO: we can use lemmatizer, but it will be very slow
        from nltk.stem import WordNetLemmatizer
        lemmatizer = WordNetLemmatizer()
        a = t[0]
        conj = t[1]
        b = t[2]
        _type = 'type_' + lemmatizer.lemmatize(t[3]) + '_'
        sent = re.sub(r'%s\s+%s\s+%s\s+%s' % (a, conj, b, t[3]), r'the %s %s %s the %s %s' % (_type, a, conj, _type, b), sent)

    
    # fixing the case that the NLP is not correct for the 'or'. The MR incorrectly provides the two predicates accept the same entity.
    sent = re.sub(r'or str_', 'or the str_', sent)
    sent = re.sub(r'or expr_', 'or the expr_', sent)

    if r := re.findall(r'(\[[,\-0-9 ]+\])', sent, re.ASCII):
        for i, e in enumerate(r):
            index = chr(i + 97)
            _int_ = e.replace('[', '').replace(']', '')
            exprs['arr_' + index] = _int_
            sent = sent.replace(e, ' arr_' + index, 1)
    
    # fixing the case the regular expression for parameter is double treated
    sent = re.sub(r'param_param', 'param', sent)
    sent = re.sub(r'__', '_', sent)
    # if r := re.findall(r'([0-9]*\.? [0-9]*)', sent, re.ASCII):
    #     for i, e in enumerate(r):
    #         _int_ = e.replace('.', '_DOT')
    #         sent = sent.replace(e, _int_, 1)
    return sent, exprs

# sent: requirement statement in natural language
# t: the type of requirement. Currently it is either 'requires' or 'ensures'
def runengine(sent: str, t: str) -> Tuple[str, dict]:
    cp = ContextProcessor()
    rp = RepairProcessor()
    dynamic_si = {}
    sent = rp.run(sent, t)
    if rp.dynamic_si:
        for key in rp.dynamic_si.keys():
            if key not in dynamic_si.keys():
                dynamic_si[key] = rp.dynamic_si[key]
    sent = cp.run(sent)    
    if cp.dynamic_si:
        for key in cp.dynamic_si.keys():
            if key not in dynamic_si.keys():
                dynamic_si[key] = cp.dynamic_si[key]
    
    sent, exprs = __fix_to_cases__(sent)
    dynamic_si.update(exprs)
    sent = rp.run(sent, t)
    sent = cp.run(sent)
    sent, exprs = __fix_to_cases__(sent)
    dynamic_si.update(exprs)
    if rp.dynamic_si:
        for key in rp.dynamic_si.keys():
            if key not in dynamic_si.keys():
                dynamic_si[key] = rp.dynamic_si[key]['interpretation']
    words = sent.split(' ')    
    if sent[-1] != '.':
        sent += '.'
    for k in dynamic_si.keys():
        v = dynamic_si[k]
        p = 'any'
        r = 'any'
        index = words.index(k)
        if index != 0 and words[index - 1] == 'type_integer_':
            sp = 'integer'
            sr = 'undefined'
            interpretation = v.replace('`', '')
        else:
            if 'chr' in k:
                sp = 'character'
                sr = 'undefined'
                interpretation = v
            elif 'arr_' not in k:
                sp = 'undefined'
                sr = 'string'
                interpretation = v.replace('`', '')
            else:
                sp = 'integer'
                sr = 'array'
                interpretation = '%s' % v.replace(' ', '')
        
        # Experimental: This one has conflict with the lex rules about the parameter name parsing
        #               remove this line if any other conflicts rise
        term = k
        if term.startswith('param_'):
            term = term.replace('param_', '')
            interpretation = interpretation.replace('param_', '').replace('_', '')
            if term[-1] == '_':
                term = term[:-1]

        #TODO: change the interpretation to new int[] {} when key is arr_[a-z]+
        d = {
            # 'term': k,
            'term': term,
            'syntax': ['NN'],
            'arguments': [{
                'symbol': '*',
                'primitive_type': p,
                'reference_type': r
            }],
            'synthesised_datatype': [{
               'primitive_type': sp,
               'reference_type': sr
            }],
            'interpretation': interpretation
        }
        dynamic_si[k] = d
    return sent, dynamic_si