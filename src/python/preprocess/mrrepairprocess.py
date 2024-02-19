from .contextprocess import datatypes

# denote __type__ as datatype in datatypes
# denote __num__ as a numerical value
rules = [
    { 'pattern': ['__type__', 'of', 'length', '__num__'], 'format': '__num__-length __type__'}
]

class RepairProcessor:    
    def __init__(self):
        pass
    
    def __process_rule__(self):
        pass
        
    def run(self, sent: str) -> str:
        self.sent = sent
        
        
        return self.sent