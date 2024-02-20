from .contextprocess import ContextProcessor
from .mrrepairprocess import RepairProcessor

def runengine(sent: str) -> str:
    cp = ContextProcessor()
    rp = RepairProcessor()
    sent = rp.run(sent)
    sent = cp.run(sent)    
    return sent