from .contextprocess import ContextProcessor
from .mrrepairprocess import RepairProcessor
from typing import Tuple

def runengine(sent: str) -> Tuple[str, dict]:
    cp = ContextProcessor()
    rp = RepairProcessor()
    dynamic_si = {}
    sent = rp.run(sent)
    # dynamic_si |= rp.dynamic_si
    if rp.dynamic_si:
        for key in rp.dynamic_si.keys():
            if key not in dynamic_si.keys():
                dynamic_si[key] = rp.dynamic_si[key]
    sent = cp.run(sent)    
    if cp.dynamic_si:
        for key in cp.dynamic_si.keys():
            if key not in dynamic_si.keys():
                dynamic_si[key] = cp.dynamic_si[key]
    return sent, dynamic_si