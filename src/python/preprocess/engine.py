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
    exprs = {}
    # for all remaining strings in quotes (``), we treat them as expressions and separatedly stored
    # print(sent)
    if r := re.findall(r'(`[0-9 <>\-\+\*!,a-zA-Z\[\]=\.\^\(\)\%\|\/_\'{}]+`)', sent):        
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['expr_' + index] = e
            sent = sent.replace(e, ' expr_' + index, 1)
    if r := re.findall(r'(\'[^ ]+\')', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['strx_' + index] = e
            sent = sent.replace(e, ' strx_' + index, 1)
    if r := re.findall(r'(\"[^ ]+\")', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['stry_' + index] = e
            sent = sent.replace(e, ' stry_' + index, 1)
    if r := re.findall(r'(\"[a-zA-Z ]+\")', sent):
        for i, e in enumerate(r):
            index = chr(i + 97)
            exprs['strz_' + index] = e
            sent = sent.replace(e, ' strz_' + index, 1)
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
    if sent[-1] != '.':
        sent += '.'    
    for k in dynamic_si.keys():
        v = dynamic_si[k]
        p = 'any'
        r = 'any'
        if 'arr_' not in k:
            sp = 'undefined'
            sr = 'string'
            interpretation = v.replace('`', '')
        else:
            sp = 'integer'
            sr = 'array'
            interpretation = 'new int[] {%s}' % v
        
        #TODO: change the interpretation to new int[] {} when key is arr_[a-z]+
        d = {
            'term': k,
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