from .contextprocess import contextprocessor

def runengine(sent: str) -> str:
    sent = contextprocessor(sent)
    print(sent)
    pass