from .contextprocess import ContextProcessor
from .mrrepairprocess import RepairProcessor

def runengine(sent: str) -> str:
    cp = ContextProcessor()
    rp = RepairProcessor()
    sent = cp.run(sent)
    sent = rp.run(sent)
    return sent