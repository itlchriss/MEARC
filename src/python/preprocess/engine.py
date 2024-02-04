from .contextprocess import ContextProcessor

def runengine(sent: str) -> str:
    cp = ContextProcessor()
    sent = cp.run(sent)
    print('engine:', sent)
    pass