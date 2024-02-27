from .contextprocess import ContextProcessor
from .mrrepairprocess import RepairProcessor
from typing import Tuple

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
    if sent[-1] != '.':
        sent += '.'
    return sent, dynamic_si