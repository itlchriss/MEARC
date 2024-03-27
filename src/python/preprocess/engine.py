from .contextprocess import ContextProcessor
from .mrrepairprocess import RepairProcessor
from typing import Tuple
import re


def __fix_to_cases__(sent: str) -> str:       
    # repeated 'to' is replaced as a single 'to'
    sent = re.sub(r'(to\s+)+', 'to ', sent)
    exprs = {}
    # for all remaining strings in quotes (``), we treat them as expressions and separatedly stored
    if r := re.findall(r'(`[0-9 <>\-\+\*!,a-zA-Z\[\]=\.\^\(\)\%]+`)', sent):        
        for i, e in enumerate(r):
            exprs['expr' + str(i)] = e
            sent = sent.replace(e, 'expr' + str(i), 1)
    if r := re.findall(r'(\'.*\')', sent):
        for i, e in enumerate(r):
            exprs['str' + str(i)] = e
            sent = sent.replace(e, 'str' + str(i), 1)
    words = sent.split(' ')
    targets = {}
    for w in words:
        if "^" in w:
            targets[w] = w.replace("^", "_pow_")
    for k in targets.keys():
        sent = sent.replace(k, targets[k])
    return sent

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
    sent = __fix_to_cases__(sent)
    if sent[-1] != '.':
        sent += '.'
    return sent, dynamic_si