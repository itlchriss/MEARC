from .contextprocess import datatypes
from typing import List
from enum import Enum
import math
import pandas as pd
import re



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
    return word.isnumeric() or (word.startswith('-') and word.count('-') == 1 and word.replace('-', '').isnumeric())

def __check_is_param__(word: str) -> bool:
    return word.startswith('`') and word[-1] == '`'

def __check_is_type__(word: str) -> bool:
    return False 

def __perform_sum__(sent: list) -> str:
    a = int(sent[0])
    b = int(sent[2])
    return str(a + b)

def __perform_diff__(sent: list) -> str:
    return str(pd.eval(' '.join(sent)))

def __html2pow__(sent: list) -> str:
    pass

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
    '__num__': __check_is_numeric__,
    '__param__': __check_is_param__,
    '__type__': __check_is_type__,
    '__sum__': __perform_sum__,
    '__diff__': __perform_diff__,
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
    },
    { 
        'pattern': ['is', 'a', 'multiple', 'of', '__num__'], 
        'format': 'is evenly_divided by __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['less', 'than', 'or', 'equal', 'to', '__param__'], 
        'format': 'less than or equal to the __param__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['less', 'than', '__param__'], 
        'format': 'less than to the __param__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['greater', 'than', 'equal', 'to', '__param__'], 
        'format': 'greater than or equal to the __param__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['greater', 'than', '__param__'], 
        'format': 'greater than to the __param__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
        
    { 
        'pattern': ['__num__', '+', '__num__'], 
        'format': '__sum__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['__num__', '-', '__num__'], 
        'format': '__diff__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'in', 'the', 'range', 'of', '__num__', 'to', '__num__'], 
        'format': 'is greater than or equal to __num__ and is less than or equal to __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'within', 'the', 'range', 'of', '__num__', 'to', '__num__'], 
        'format': 'is greater than or equal to __num__ and is less than or equal to __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'in', 'the', 'range', 'of', 'negative', '__num__', 'to', '__num__'], 
        'format': 'is greater than or equal to negative __num__ and is less than or equal to __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'in', 'the', 'range', 'of', '__num__', 'to', 'negative', '__num__'], 
        'format': 'is greater than or equal to __num__ and is less than or equal to negative __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'within', 'the', 'range', 'of', '__num__', 'to', 'negative', '__num__'], 
        'format': 'is greater than or equal to __num__ and is less than or equal to negative __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'within', 'the', 'range', 'of', 'negative', '__num__', 'to', '__num__'], 
        'format': 'is greater_than_or_equal to negative __num__ and is less_than_or_equal to __num__', 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
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
            if w.startswith('-') and w.count('-') == 1 and w.replace('-', '').isnumeric():
                targets[w] = 'negative ' + w.replace('-', '')
        for k in targets:
            sent = sent.replace(k, targets[k])
        return sent

    def __process_power_sign__(self, sent):
        words = sent.split(' ')
        targets = {}        
        for w in words:
            if "^" in w:
                x = w.replace("^", "**")
                try:
                    targets[w] = str(pd.eval(x))
                except:
                    # targets[w] = w.replace("^", "_pow_")
                    pass
            if "<sup>" in w and "</sup>" in w:
                arr = w.replace("<sup>", ' ').replace("</sup>", '').strip().split(' ')
                if len(arr) == 2 and arr[0].isnumeric() and arr[1].isnumeric():
                    targets[w] = str(math.pow(int(arr[0]), int(arr[1]))).split('.')[0]
        for k in targets:            
            sent = sent.replace(k, targets[k])
        return sent

    def __process_range_sign__(self, sent):
        # words = sent.split(' ')
        range_p = 'range\s+of\s+\[(.*)\]'
        if r := re.search(range_p, sent):
            s = [i.strip() for i in r.group(1).split(',')]
            s = [pd.eval(i) for i in s]
            new = 'range of %s to %s' % (str(s[0]), str(s[1]))
            sent = re.sub(range_p, new, sent)
        return sent
    
    def __nth_repl(s, sub, repl, n):
        find = s.find(sub)
        # If find is not -1 we have found at least one match for the substring
        i = find != -1
        # loop util we find the nth or we find no match
        while find != -1 and i != n:
            # find + 1 means we start searching from after the last match
            find = s.find(sub, find + 1)
            i += 1
        # If i is equal to n we found nth match so replace
        if i == n:
            return s[:find] + repl + s[find+len(sub):]
        return s
    
    def __process_general_syntax_rule__(self, words, rule, index):
        f = rule['format']
        pattern = rule['pattern']
        symbol = rule['symbol']
        interpretation = rule['interpretation']
        
        pairs = []
        if len(f.split(' ')) > 1:
            for i, x in enumerate(pattern):
                if x in func_map.keys():
                    f = f.replace(x, words[index + i], 1)
                    pairs.append((x, words[index + i]))
        elif f in func_map.keys():
            subsent = words[index: index + len(pattern)]
            f = func_map[f](subsent)
                
                
        if pairs:
            for k,v in pairs:
                if not symbol:
                    continue
                symbol = symbol.replace(k, v)     
                interpretation = interpretation.replace(k, v)
        if symbol:           
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
        if ',' in sent:
            sent = sent.replace(',', ' , ')        
        sent = self.__process_power_sign__(sent)
        sent = self.__process_range_sign__(sent)
        sent = self.__process_negative__(sent)  
        words = sent.split(' ')        
        for r in general_syntax_rules:
            pattern = r['pattern']
            while (i := __words_contain_pattern__(words, pattern)) >= 0:
                sent = self.__process_general_syntax_rule__(words, r, i)
                words = sent.split(' ')        
        for k in reqtype_ignore_rules[t].keys():
            sent = sent.replace(k, reqtype_ignore_rules[t][k])        

        return sent