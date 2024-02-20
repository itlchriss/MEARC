from .contextprocess import datatypes
from typing import List

# denote __type__ as datatype in datatypes
# denote __num__ as a numerical value

def __check_is_numeric__(word: str) -> bool:
    return word.isnumeric()

# returning the index that the pattern starts at, or -1 indicates the pattern is not found
def __words_contain_pattern__(words: List[str], pattern: List[str]) -> int:
    for i in range(len(words) - len(pattern) + 1):
        for j in range(len(pattern)):
            if (pattern[j] in func_map and not func_map[pattern[j]](words[i + j])) or (not pattern[j] in func_map and words[i + j] != pattern[j]):                          
                break
        else:
            return i
    return -1

func_map = {
    '__num__': __check_is_numeric__
}

rules = [
    { 'pattern': ['an', 'array', 'of', 'length', '__num__'], 'format': 'a __num__-length array'}
]

class RepairProcessor:    
    def __init__(self):
        pass
    
    def __process_rule__(self, words, rule, index):
        print(words[index])
        pass
        
    def run(self, sent: str) -> str:
        if sent[-1] == '.':
            sent = sent[:-1]
        words = sent.split(' ')
        for r in rules:
            pattern = r['pattern']
            if (i := __words_contain_pattern__(words, pattern)) >= 0:
                self.__process_rule__(words, r, i)
                    
        
        return sent