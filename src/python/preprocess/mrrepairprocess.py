from .contextprocess import datatypes
from typing import List
from enum import Enum



# the enumeration and constant UNDEFINED values must be consistent with the declarations in cst.h
UNDEFINED = -1
ANY = '*'

class IntEnum(Enum):
    def __int__(self):
        return self.value
    
    def __str__(self):
        return str(self.value)

class Primitive_datatype(IntEnum):
    Boolean = 0
    Byte = 1
    Char = 2
    Short = 3
    Integer = 4
    Long = 5
    Float = 6
    Double = 7
    


class Reference_datatype(IntEnum):
    Array = 0
    String = 1
    Object = 2


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

# general_syntax_rules = [
#     { 
#         'pattern': ['an', 'array', 'of', 'length', '__num__'], 
#         'format': 'a __num__-length array', 
#         'symbol': '__num__-length', 
#         'interpretation': '(Subj).length == __num__',
#         'syntax': 'JJ',
#         'arguments': ['Subj'],
#         'specific_arg_types': '5',
#         'synthesised_datatype': '9'
#     }
# ]

label_primitive_type = 'primitive_type'
label_reference_type = 'reference_type'
label_symbol = 'symbol'

general_syntax_rules = [
    { 
        'pattern': ['an', 'array', 'of', 'length', '__num__'], 
        'format': 'a __num__-length array', 
        'symbol': '__num__-length', 
        'interpretation': '(Subj).length == __num__',
        'syntax': 'JJ',
        'arguments': [
            {
                label_symbol: 'Subj',
                label_primitive_type: ANY,
                label_reference_type: str(Reference_datatype.Array)
            }
            ],
        'synthesised_datatype': { label_primitive_type: str(Primitive_datatype.Boolean), label_reference_type: str(UNDEFINED) }
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
    
    def __process_negative__(self, sent):
        words = sent.split(' ')
        targets = {}
        for w in words:
            if w.startswith('-') and w.replace('-', '').isnumeric():
                targets[w] = 'negative ' + w.replace('-', '')
        for k in targets:
            sent = sent.replace(k, targets[k])
        return sent
    
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
                                   'arguments': rule['arguments'],  
                                   'synthesised_datatype': rule['synthesised_datatype'],                                   
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
        sent = self.__process_negative__(sent)
        return sent