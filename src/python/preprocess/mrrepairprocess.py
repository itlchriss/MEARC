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

general_syntax_rules = [
    { 
        'pattern': ['an', 'array', 'of', 'length', '__num__'], 
        'format': 'a __num__-length array', 
        'symbol': '__num__-length', 
        'interpretation': '(Subj).length == __num__',
        'syntax': 'JJ',
        'arguments': ['Subj'],
        'specific_arg_types': '5',
        'synthesised_datatype': '9'
    }
]

reqtype_ignore_rules = {
    'requires': {
        },
    'ensures': {
        "`answer`": 'keyword_result'
    }
}

class RepairProcessor:    
    
    dynamic_si = {}
    _t = None
    
    def __init__(self):
        pass
    
    def __process_general_syntax_rule__(self, words, rule, index):
        f = rule['format']
        pattern = rule['pattern']
        symbol = rule['symbol']
        interpretation = rule['interpretation']
        
        pairs = []
        for i, x in enumerate(pattern):
            if x in func_map.keys():
                f = f.replace(x, words[index + i])
                pairs.append((x, words[index + i]))
                
        if pairs:
            for k,v in pairs:
                symbol = symbol.replace(k, v)     
                interpretation = interpretation.replace(k, v)           
        self.dynamic_si[symbol] = { 
                                   'term': symbol.replace('-', '_dash_'),
                                   'syntax': [rule['syntax']],
                                   'arity': len(rule['arguments']),
                                   'arguments': rule['arguments'], 
                                   'specific_arg_types': [rule['specific_arg_types']], 
                                   'synthesised_datatype': [rule['synthesised_datatype']],                                   
                                   'interpretation': interpretation,
                                   }        
        words = words[:index] + [f] + words[index + len(pattern):]
        return ' '.join(words)
    
        
    def run(self, sent: str, t: str) -> str:
        self._t = t
        if sent[-1] == '.':
            sent = sent[:-1]
        words = sent.split(' ')
        for r in general_syntax_rules:
            pattern = r['pattern']
            if (i := __words_contain_pattern__(words, pattern)) >= 0:
                sent = self.__process_general_syntax_rule__(words, r, i)
        for k in reqtype_ignore_rules[t].keys():
            sent = sent.replace(k, reqtype_ignore_rules[t][k])
        
        return sent