# from .contextprocess import datatypes
from typing import List
from enum import Enum
import math
import pandas as pd
import re
import yaml
from word2number import w2n


sispecspath = './specs/si/typed_si.yml'
def _get_specs():
    si = {}
    with open(sispecspath) as fp:
        si = yaml.full_load(fp)
    return si

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

SI_data = None

def __check_is_numeric__(word: str) -> bool:
    return word.isnumeric() or (word.startswith('-') and word.count('-') == 1 and word.replace('-', '').isnumeric())

def __check_is_char__(word: str) -> bool:
    return len(word) == 3 and word[0] == '\'' and word[2] == '\''

def __check_is_boolean__(word: str) -> bool:
    return word == 'true' or word == 'false'

def __check_is_be__(word: str) -> bool:
    return word == 'is' or word == 'are'

def __check_is_param__(word: str) -> bool:
    return (word.startswith('`') and word[-1] == '`') or (word.startswith('param_') and word[-1] == '_')

def __check_is_param_or_result__(word: str) -> bool:
    return (word.startswith('`') and word[-1] == '`') or (word.startswith('param_') and word[-1] == '_') or word == 'result'

def __check_is_expr__(word: str) -> bool:
    pattern = r'_expr\d+_'
    return re.match(pattern, word)

def __check_is_quoted__(word: str) -> bool:
    return word.startswith('`') and word.endswith('`')

def __check_is_type__(word: str) -> bool:
    return word.startswith('type_')

def __check_is_comparative__(word: str) -> bool:
    return word.endswith('er')

def __perform_sum__(sent: list) -> str:
    a = int(sent[0])
    b = int(sent[2])
    return str(a + b)

def __perform_diff__(sent: list) -> str:
    return str(pd.eval(' '.join(sent)))

def __perform_product__(sent: list) -> str:
    sent = list(map(lambda x: '*' if x == 'times' else x, sent))
    return str(pd.eval(' '.join(sent)))

def __get_number__(sent: list) -> str:
    for i in sent:
        if __check_is_numeric__(i):
            return str(i)
    return None

def __convert_to_vocontain__(sent: list) -> str:
    p = sent[0].replace("'s", '')
    return "%s vocontains" % p

def __array_value_access__(sent: list) -> str:
    print(sent)
    exit(1)

def __html2pow__(sent: list) -> str:
    pass

def __check_is_string__(word: str) -> bool:
    return word.startswith('"') and word.endswith('"')

def __check_is_si_term__(word: str) -> bool:
    global SI_data
    if not SI_data:
        SI_data = _get_specs()
    terms = [si["term"] for si in SI_data]
    return word in terms

def __check_is_num_word__(word: str) -> bool:
    try:
        return isinstance(w2n.word_to_num(word), int)
    except:
        return False 

def __convert_num_word__(word: str) -> int:
    return w2n.word_to_num(word)


words_4_chartype = [
    'digits', 'English letters', 'alphabets', 'numbers'
]

def __check_is_chartype__(word: str) -> str:
    return word in words_4_chartype


word_4_restrictive_adverb = [
    'only'
]

def __check_is_restrictive_adverb__(word: str) -> str:
    return word in word_4_restrictive_adverb


# returning the index that the pattern starts at, or -1 indicates the pattern is not found
def __words_contain_pattern__(words: List[str], pattern: List[str]) -> int:
    for i in range(len(words) - len(pattern) + 1):   
        for j in range(len(pattern)):     
            if (pattern[j] in func_map and not func_map[pattern[j]](words[i + j])) or (not pattern[j] in func_map and words[i + j] != pattern[j]):            
                break
        else:
            return i
    return -1

def __match_any_word__(word: str) -> bool:
    return word.isalpha()

def __check_is_SI_string__(word: str) -> bool:
    return re.match(r'^str[a-z]_[a-z]$', word)

def __check_is_posessive_preposition__(word: str) -> bool:
    return word == 'of' or word == 'in'

def __check_is_values_with_param_or_result__(word: str) -> bool:
    return word.endswith("'s") and (word.replace("'s", '') == 'result' or word.startswith("param_"))

def __check_is_contain__(word: str) -> bool:
    return word == 'contain' or word == 'contains'

func_map = {
    '__param_or_result__': __check_is_param_or_result__,
    '__num__': __check_is_numeric__,
    '__be__': __check_is_be__,
    '__quoted__': __check_is_quoted__,
    '__string__': __check_is_string__,
    '__char__': __check_is_char__,
    '__bool__': __check_is_boolean__,
    '__param__': __check_is_param__,
    '__type__': __check_is_type__,
    '__sum__': __perform_sum__,
    '__diff__': __perform_diff__,
    '__product__': __perform_product__,
    '__expr__': __check_is_expr__,
    '__comparative__': __check_is_comparative__,
    '__chartype__': __check_is_chartype__,
    '__restrictive_adverb__': __check_is_restrictive_adverb__,
    '__si_term__': __check_is_si_term__,
    '__num_word__': __check_is_num_word__,
    '__filter_num__': __get_number__,
    '__array_value_access__': __array_value_access__,
    '__word__': __match_any_word__,
    '__expr_string__': __check_is_SI_string__,
    '__pos_prep__': __check_is_posessive_preposition__,
    '__param_or_result_values__': __check_is_values_with_param_or_result__,
    '__vocontain__': __convert_to_vocontain__,  
    '__contain__': __check_is_contain__
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
label_interpretation_type = 'interpretation_type'
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
        'pattern': ['__num__', '*', '__num__'], 
        'format': '__product__', 
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
        'pattern': ['are', 'in', 'the', 'range', 'of', '__num__', 'to', '__num__'], 
        'format': 'are greater than or equal to __num__ and are less than or equal to __num__', 
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
    },
    { 
        'pattern': ['has', 'a', 'length', 'of', '__num__'], 
        'format': "'s length is equal to __num__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['has', 'a', 'length', 'of', '__expr__'], 
        'format': "'s length is equal to __expr__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['is', 'of', 'length', '__num__'], 
        'format': "'s length is equal to __num__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['length', 'of', 'the', 'string_representation', 'of', 'the', '__type__', '__param_or_result__'], 
        'format': "__type__ __param_or_result__'s string_representation's length", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['__be__', 'either', '__num__', 'or', '__num__'], 
        'format': "__be__ equal to __num__ or __be__ equal to __num__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['__be__', 'either', '__char__', 'or', '__char__'], 
        'format': "__be__ equal to __char__ or __be__ equal to __char__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['__be__', 'either', '__char__', ',', '__char__', ',', 'or', '__char__'], 
        'format': "__be__ equal to __char__ or __be__ equal to __char__ or __be__ equal to __char__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['__be__', 'either', '__num__', ',', '__num__', ',', 'or', '__num__'], 
        'format': "__be__ equal to __num__ or __be__ equal to __num__ or __be__ equal to __num__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['__be__', 'either', '__bool__', 'or', '__bool__'], 
        'format': "__be__ equal to the __bool__ literal or __be__ equal to the __bool__ literal", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['must', 'have', 'a', 'length', 'of', '__num__'], 
        'format': "'s length is equal to __num__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['must', 'have', 'a', 'length', '__param__'], 
        'format': "'s length is equal to __param__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    { 
        'pattern': ['must', 'have', 'a', 'length', '__comparative__'], 
        'format': "'s length is __comparative__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['consist', 'of', '__chartype__', '__restrictive_adverb__'], 
        'format': "only contain __chartype__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['must', 'consist', 'of', '__restrictive_adverb__'], 
        'format': "only contain", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['consists', 'of', '__chartype__'], 
        'format': "contains __chartype__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['consists', 'of', '__restrictive_adverb__'], 
        'format': "only contains", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['consists', '__restrictive_adverb__', 'of'], 
        'format': "only contains", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['consisting', 'of', '__restrictive_adverb__'], 
        'format': "only contains", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'length'], 
        'format': "__param__'s length", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'has', 'a', 'length'], 
        'format': "__param__'s length", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__num__', 'times', 'the', 'integer', '__quoted__'], 
        'format': "the type_integer_ `__num__ * __quoted__`", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'has', 'an', 'even', 'length'], 
        'format': "__param__'s length is even", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['length', 'of', 'the', '__type__', '__param__', 'and', 'the', '__type__', '__param__'], 
        'format': "length of the __type__ __param__ and the length of the __type__ __param__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ["__si_term__", ",", "__si_term__", "or", "__si_term__"], 
        'format': "__si_term__ or __si_term__ or __si_term__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ["is", "equal", "to", "__string__", "or", "__string__"], 
        'format': "is equal to __string__ or is equal to __string__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'has', 'only', 'one', 'element'], 
        'format': "__param__'s length is equal to 1", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'has', '__num_word__', 'elements'], 
        'format': "__param__'s length is equal to __num_word__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'has', 'more', 'than', '__num_word__', 'elements'], 
        'format': "__param__'s length is greater than to __num_word__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param__', 'has', 'more', 'than', '__num__', 'elements'], 
        'format': "__param__'s length is greater than to __num__", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['is', 'in', 'the', 'range', 'of', 'a', 'signedint'], 
        'format': "is greater than to negative 2147483648 and is less than to 2147483647", 
        'symbol': '', 
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['the', 'value', 'of', 'the', '__type__', '__param__', 'at', 'the', 'index', 'of', 'the', 'result'],
        'format': "the __param__array_access_with_result_",
        'symbol': '__param__array_access_with_result_',
        'interpretation': '__param__[\\result]',
        'syntax': 'NN',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['the', 'length', 'of', 'the', '__type__', '__param__'],
        'format': "the __type__ __param__'s length",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['The', 'length', 'of', 'the', '__type__', '__param__'],
        'format': "The __type__ __param__'s length",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['the', 'length', 'of', 'the', '__type__', 'result'],
        'format': "the __type__ result's length",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['The', 'length', 'of', 'the', '__type__', 'result'],
        'format': "The __type__ result's length",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['any', 'value', 'appears', 'at', 'least', 'twice', 'in', 'the', '__word__'],
        'format': "the __word__ is not unique",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['contain', 'the', 'substring', '__expr_string__'],
        'format': "contain the __expr_string__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['All', 'values', '__pos_prep__', 'the', '__type__', '__param__'],
        'format': "The __type__ __param__'s values",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['If', 'all', 'values', '__pos_prep__', 'the', '__type__', '__param__'],
        'format': "If the __type__ __param__'s values",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['All', 'the' 'values', '__pos_prep__', 'the', '__type__', '__param__'],
        'format': "The __type__ __param__'s values",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['All', 'the', 'values', '__pos_prep__', '__type__', '__param__'],
        'format': "The __type__ __param__'s values",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['All', 'values', '__pos_prep__', '__type__', '__param__'],
        'format': "The __type__ __param__'s values",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['All', 'values', '__pos_prep__', 'the', '__type__', '__param_or_result__'],
        'format': "The __type__ __param_or_result__'s values",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['not', 'equal', 'to', 'the', 'summation', 'in', 'the', '__type__', '__param__'],
        'format': "not_equal to the __type__ __param__'s summation",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['The', 'summation', 'in', 'the', '__type__', '__param_or_result__'],
        'format': "The __type__ __param_or_result__'s summation",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['the', 'summation', 'in', 'the', '__type__', '__param_or_result__'],
        'format': "the __type__ __param_or_result__'s summation",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['the', 'minimum_value', 'in', 'the', '__type__', '__param__'],
        'format': "the __type__ __param__'s minimum_value",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['first_element', '__pos_prep__', 'the', '__type__', '__param_or_result__'],
        'format': "__type__ __param_or_result__'s first_element",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['second_element', '__pos_prep__', 'the', '__type__', '__param_or_result__'],
        'format': "__type__ __param_or_result__'s second_element",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['number_of_unique_elements', '__pos_prep__', 'the', '__type__', '__param_or_result__'],
        'format': "__type__ __param_or_result__'s number_of_unique_elements",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['length', 'plus', '__num__'],
        'format': "length + __num__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['at', 'least', '1', 'value', 'in', 'the', '__type__', '__param_or_result__', 'appears', 'more', 'than', 'once'],
        'format': "the __type__ __param_or_result__ is not unique",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__num__', 'times', '__num__'],
        'format': "__product__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['contains', 'less', 'than', '__num__', 'elements'],
        'format': "'s length is less than __num__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__be__', 'unique', 'sorted', 'to', 'the', 'ascending_order'],
        'format': "__be__ unique and __be__ sorted to the ascending_order",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__be__', 'between', '__num__', 'and', '__num__'],
        'format': "__be__ greater than __num__ and __be__ less than __num__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__param_or_result_values__', 'values', 'only', '__contain__'],
        'format': "__vocontain__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['there', 'exists', 'no', 'non-repeating', 'character', 'in', 'the', '__type__', '__param_or_result__'],
        'format': "the __type__ __param_or_result__ does not contain a nonrepeating_character",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['there', 'exists', 'a', 'non-repeating', 'character', 'in', 'the', '__type__', '__param_or_result__'],
        'format': "the __type__ __param_or_result__ contains a nonrepeating_character",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['the', 'sum', 'of', 'the', '__type__', '__param_or_result__', 'and', 'the', '__type__', '__param_or_result__'],
        'format': "the __type__ __param_or_result__ + the __type__ __param_or_result__",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    },
    {
        'pattern': ['__contain__', 'integers', 'and', 'arithmetic_operators', 'separated', 'by', 'number', 'of', 'spaces'],
        'format': "only __contain__ arithexprspace",
        'symbol': '',
        'interpretation': '',
        'syntax': '',
        'arguments': [],
        'synthesised_datatype': { }
    }
]

# there exists no non-repeating character in the type_string_ param_s_



reqtype_ignore_rules = {
    'requires': {
        },
    'ensures': {
        "`answer`": 'keyword_result'
    }
}

def __process_to_later_clause__(sent, r) -> str:
    type_str = r.group(1)
    param_str = r.group(2)
    other_str = r.group(3)
    ans = None
    if type_str == 'type_integer_array_':
        ans = '0'
    elif type_str == 'type_string_':
        ans = "'0'"
    return "The %s %s %s and the %s %s's first_element is equal to %s." % (type_str, param_str, other_str, type_str, param_str, ans)


class RepairProcessor:    
    
    # dynamic_si = {}
    # _t = None
    
    def __init__(self):
        self._t = None
        self.dynamic_si = {}
        pass
    
    def __process_negative__(self, sent):
        words = sent.split(' ')
        targets = {}
        arr_flag = False
        for w in words:
            if '[' in w:
                arr_flag = True
            if '^' in w and arr_flag:
                arr_flag = False
            if w.startswith('-') and w.count('-') == 1 and w.replace('-', '').isnumeric() and not arr_flag:
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
    
    def __process_partial_equal(self, sent) -> str:
        patterns = [
            {
                'p': r'the\s+first\s+(\d+)\s+elements\s+of\s+the\s+(\w+)\s+(\w+)\s+are\s+\d+( , \d+)*( , and \d+| and \d+)?',
                'sp': 'integer',
                'sr': 'array',
            },
            {
                'p': r'the\s+first\s+(\d+)\s+elements\s+of\s+the\s+(\w+)\s+(\w+)\s+are\s+\d+( , \d+)*( , and \d+| and \d+)?',
                'sp': 'integer',
                'sr': 'array',
            },
            {
                'p': r'the\s+first\s+(\d+)\s+elements\s+of\s+the\s+(\w+)\s+(\w+)\s+are\s+equal\s+to\s+\d+( , \d+)*( , and \d+| and \d+)?',
                'sp': 'integer',
                'sr': 'array',
            },
            {
                'p': r'the\s+first\s+(\w+)\s+elements\s+of\s+the\s+(\w+\s+array\s+parameter)\s+(`\w+`)\s+are\s+equal\s+to\s+\d+( , \d+)*( , and \d+| and \d+)?',
                'sp': 'integer',
                'sr': 'array',
            },
        ]
        result = ''
        target = ''
        for pattern in patterns:
            if r := re.search(pattern['p'], sent):
                target = r.group(0)
                length = r.group(1)
                data = re.findall(r'\d+', r.group(0))         
                if not length.isdigit():                   
                    length = str(__convert_num_word__(length))
                else:                    
                    data = data[1:]
                type_str = r.group(2)
                param_str = r.group(3)                        
                symbol = 'from_%s_%s_integer_sequence_' % (str(0), str(int(length) - 1))
                result = 'the %s %s is partially_equal to the type_integer_array_ %s' % (type_str, param_str, symbol)
                sent = sent.replace(target, result)                
                interpretation = '_'.join([str(0), str(int(length) - 1)]) + '_' + ','.join([str(i) for i in data])
                # self.dynamic_si[symbol] = { 
                #                     'term': symbol,
                #                     'syntax': ['NN'],
                #                     'arguments': '*',  
                #                     'synthesised_datatype': {
                #                         'primitive_type': pattern['sp'],
                #                         'reference_type': pattern['sr']
                #                         },                                   
                #                     'interpretation': interpretation,
                #                     }     
                self.dynamic_si[symbol] = interpretation
        return sent
    
    def __process_limited_equal(self, sent) -> str:
        patterns = [
            {
                'p': r'are\s+limited\s+to\s+\d+( , \d+)*( , and \d+| and \d+)?',
                'sp': 'integer',
                'sr': 'array',
            }
        ]
        for pattern in patterns:
            if r := re.search(pattern['p'], sent):
                target = r.group(0)
                data = re.findall(r'\d+', r.group(0))         
                symbol = '_fixed_integer_sequence_'                
                # result = 'the %s %s is partially_equal to the type_integer_array_ %s' % (type_str, param_str, symbol)
                sent = sent.replace(target, 'are equal to the type_integer_array_ '+ symbol)                
                # interpretation = '_'.join([str(0), str(int(length) - 1)]) + '_' + ','.join([str(i) for i in data])
                self.dynamic_si[symbol] = ','.join([str(i) for i in data])
        return sent
    
    
    # TODO: to be combined the two functions
    def __process_complex_clause(self, sent) -> str:
        # print(sent)
        # consists of only digits and the dot character
        # only contains alphabets , digits , + , - , or dot characters
        patterns = [
            # {
            #     'p': r"(only\s+contains)\s+(the\s+characters\s+)?(('\w+')(\s+,\s+('\w+'))*(\s+,\s+or\s+'\w+'))(\s+)?",
            #     'connective': 'or',
            #     'target': 3
            # },
            {
                'p': r'(only\s+contains\s+)((([\w_]+)(\s+,\s+the\s+(\w+)(\s+character)?)*(\s+,\s+or\s+the\s+(\w+(\s+character)?)|\s+or\s+the\s+(\w+(\s+character)?))?))',
            },
            {
                'p': r'(only\s+contains)\s+(([\w_]+)(\s+,\s+(\w+))*(\s+,\s+or\s+\w+\s+characters))'
            },
            {
                'p': r"(consist\s+only\s+of)\s+('\w+'\s+or\s+'\w+'\s+characters)"
            },
            {
                'p': r"(only\s+contains)\s+(\w+\s+and\s+\w+)\s+characters(\s+)?$",
                'connective': 'or'
            },
            {
                'p': r"(consists\s+of\s+only)\s+(\w+\s+and\s+the\s+\w+)\s+character",
                'connective': 'or'
            },
            {
                'p': r"(only\s+contains)\s+((\w+)(\s+,\s+[\w\W]+)+\s,\sor\s+\w+\s+)characters",
                'connective': 'or'
            },
            {
                'p': r'(only\s+contains)\s+(([\w_]+)(\s+,\s+(the\s+)?(\w+))*(\s+,\s+or\s+(the\s+)?\w+))(\s+)?'
            },
            {
                'p': r'(contains)\s+(([\w_]+)(\s+,\s+(the\s+)?(\w+))*(\s+,\s+and\s+(the\s+)?\w+))',
                'connective': 'or'
            },
            {
                'p': r'(only\s+contains)\s+(([\w_]+)(\s+,\s+(\w+))*((\s+,)?\s+and\s+\w+))(\s+)?$',
                'connective': 'or'
            },
            {
                'p': r"are\s+either\s+(('\w')\s+or\s+('\w'))(\s+)?$",
                'require': 'All values',
                'template': 'are equal to'
            }
        ]
        # print(sent)
        tmp = sent.replace(', - ,', ', minus ,')
        for pattern in patterns:
            if r := re.search(pattern['p'], tmp):
                if not r.group(0).endswith('and the') and not r.group(0).endswith('and does'):
                    symbol = 'checking_character_sequence_'
                    if 'template' not in pattern.keys():
                        verb = r.group(1)
                        if 'target' in pattern.keys():
                            index = pattern['target']
                        else:
                            index = 2
                        connective = ''
                        if 'connective' in pattern.keys():
                            connective = pattern['connective']
                        else:
                            if 'or' in r.group(index):
                                connective = 'or'
                            else:
                                connective = 'and'
                        target = r.group(index).replace('the', '').replace(' ', '').replace('or', ',').replace('and', ',').replace(',,', ',')
                        if 'characters' in target:
                            target = target.replace('characters', '')
                        else:
                            target = target.replace('character', '')
                        target = target.replace("the", '')
                        if ',' in target:
                            self.dynamic_si[symbol] = '%s,%s' % (connective, target)
                            sent = tmp.replace(r.group(0), verb + ' the ' + symbol)
                    else:
                        values = r.group(1)
                        connective = ''
                        if 'or' in values:
                            connective = 'or'
                        else:
                            connective = 'and'
                        target = values.replace(connective, ',').replace("\'", '').replace(' ', '')
                        self.dynamic_si[symbol] = '%s,%s' % (connective, target)
                        sent = tmp.replace(r.group(0), pattern['template'] + ' ' + symbol)
        return sent
    
    def __process_complex_clause2(self, sent) -> str:
        patterns = [
            {
                'p': r'are\s+either\s+(\w+)\s+((\w+)((\s+,\s+\w+)*\s+,\s+or\s+(\w+)))',
                'connective': 'or',
                'template': 'are equal to %s',
                'symbol': 'checking_string_sequence_'
            },
            {
                'p': r'(only\s+contains)\s+((the\s+)?([\w_]+)(\s+,\s+(the\s+)?([\w_]+))*(\s+,\s+or\s+(the\s+)?[\w_]+))',
                'connective': 'or',
                'template': 'only contains %s',
                'symbol': 'checking_character_sequence_'
            }
        ]
        for pattern in patterns:
            if r := re.search(pattern['p'], sent):
                if not r.group(0).endswith('and the') and not r.group(0).endswith('and'):
                    target = r.group(0)
                    type_str = r.group(1)
                    # if type_str == 'strings':
                    #     type_str = 'string'                
                    result = r.group(2)
                    result = re.sub('the\s+', '', result)
                    # print(result)
                    result = result.replace(' ', '').replace(pattern['connective'], '')
                    for s in result.split(','):
                        if sr := re.match(r'^str\w+_[a-z]$', s):
                            if s in self.current_dynamic_si.keys():
                                result = result.replace(s, self.current_dynamic_si[s])  
                        elif sr := re.match(r'^chr\w+_[a-z]$', s):
                            if s in self.current_dynamic_si.keys():
                                result = result.replace(s, self.current_dynamic_si[s])  
                    symbol = pattern['symbol']           
                    self.dynamic_si[symbol] = '%s,%s' % (pattern['connective'], result)
                    sent = sent.replace(target, pattern['template'] % symbol)
                    break
        return sent
    
    
    def __process_except_clause__(self, sent) -> str:
        patterns = [
            {
                'p': r'The\s+(type_\w+)\s+(param_\w+)\s+does\s+not\s+(contain|have)\s+(any\s+)?leading\s+zeros\s+except\s+for\s+the\s+0\s+itself(\s+)?$',
                'template': "The %s %s's first_element is not_equal to %s unless the %s %s only contains single_zero",
                'repeat': 1
            },
            {
                'p': r'The\s+(type_\w+)\s+(result)\s+does\s+not\s+(contain|have)\s+(any\s+)?leading\s+zeros\s+except\s+for\s+the\s+0\s+itself(\s+)?$',
                'template': "The %s %s's first_element is not_equal to %s unless the %s %s only contains single_zero",
                'repeat': 1
            },
            {
                'p': r'The\s+(type_\w+)\s+(result)\s+does\s+not\s+(contain|have)\s+leading\s+zeros\s+unless',
                'template': "The %s %s's first_element is not_equal to %s unless",
                'repeat': 0
            },
            {
                'p': r'^The\s+(type_\w+)\s+(param\w+)\s+does\s+not\s+(contain|have)\s+any\s+leading\s+zeros(\s+)?$',
                'template': "The %s %s's first_element is not_equal to %s.",
                'repeat': 0
            },
            {
                'p': r'^The\s+(type_\w+)\s+(param\w+)\s+(.*)\s+and\s+contains\s+leading\s+zeros(\s+)?$',
                'func': __process_to_later_clause__
            }
        ]
        for pattern in patterns:
            if r := re.search(pattern['p'], sent):
                if 'func' in pattern.keys():
                    sent = pattern['func'](sent, r)
                    break
                else:
                    target = r.group(0)
                    type_str = r.group(1)
                    param_str = r.group(2)
                    # symbol = pattern['symbol']
                    # self.dynamic_si[symbol] = 'except the %s %s' % (type_str, param_str)
                    zero = None
                    if type_str == 'type_integer_array_':
                        zero = str(0)
                    elif type_str == 'type_string_':
                        zero = "'0'"
                    if pattern['repeat'] == 1:
                        sent = sent.replace(target, pattern['template'] % (type_str, param_str, zero, type_str, param_str))
                    else:
                        sent = sent.replace(target, pattern['template'] % (type_str, param_str, zero))
        return sent
    
    def __process_power_clause__(self, sent) -> str:
        # 10 raised to the power of the type_integer_ param_n_
        patterns = [
            {
                'p': r'\s+(\d+)\s+raised\s+to\s+the\s+power\s+of\s+the\s+(\w+)\s+param_(\w+)',
                'template': ' the %s int_expr',
                'symbol': 'int_expr'
            }
        ]
        for pattern in patterns:
            if r := re.search(pattern['p'], sent):
                target = r.group(0)
                base = r.group(1)
                type_str = r.group(2)
                power = r.group(3).replace('_', '')
                symbol = pattern['symbol']
                self.dynamic_si[symbol] = 'Math.pow(%s,%s)' % (base, power)
                sent = sent.replace(target, pattern['template'] % type_str)
        return sent

    def __process_comma_separated_clause__(self, sent) -> str:
        # comma-separated values that are either integers in the range arr_a or the chry_a 
        patterns = [
            {
                'p': r'comma-separated\s+values\s+that\s+are\s+either\s+(\w+)\s+in\s+the\s+range\s+(\w+)\s+or\s+the\s+(\w+)(\s+)?$', 
            }
        ]
        for pattern in patterns:
            if r := re.search(pattern['p'], sent):
                # this is the SI symbol that the construct type SI will find
                symbol = 'csvdata'
                target = 'or'
                if r.group(1) == 'integers':
                    _range_str = r.group(2)
                    if _range_str in self.current_dynamic_si.keys():
                        _range_str = self.current_dynamic_si[_range_str]
                    if ',' in _range_str:
                        target += ',range'
                        for i in _range_str.split(','):
                            target += ',' + i.strip()

                target += ',equal'
                if r.group(3).startswith('chr'):
                    target += ',' + self.current_dynamic_si[r.group(3)]
                
                self.dynamic_si[symbol] = target
                sent = sent.replace(r.group(0), 'checking_csv')
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
                    if x == '__be__' and 'either' in self._org_sent:
                        f = f.replace(x, words[index + i])
                    elif x == '__quoted__':
                        # replacing the info with unquoted version
                        f = f.replace(x, words[index + i].replace('`', ''), 1)
                    else:
                        f = f.replace(x, words[index + i], 1)
                    
                    if x == '__be__' and '__be__' in f:
                        f = f.replace(x, words[index + i])
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

        
    def run(self, sent: str, t: str, current_dynamic_si = None) -> str:
        self._t = t
        self._org_sent = sent
        self.current_dynamic_si = current_dynamic_si
        if sent[-1] == '.':
            sent = sent[:-1]   
        if ',' in sent:
            sent = sent.replace(',', ' , ')   
        sent = re.sub(r'\s+', ' ', sent)     
        sent = self.__process_power_sign__(sent)
        sent = self.__process_range_sign__(sent)
        sent = self.__process_negative__(sent)  
        sent = self.__process_partial_equal(sent)
        sent = self.__process_limited_equal(sent)
        sent = self.__process_complex_clause(sent)
        sent = self.__process_complex_clause2(sent)
        sent = self.__process_except_clause__(sent)
        sent = self.__process_power_clause__(sent)
        sent = self.__process_comma_separated_clause__(sent)
        words = sent.split(' ')        
        for r in general_syntax_rules:
            pattern = r['pattern']
            while (i := __words_contain_pattern__(words, pattern)) >= 0:
                sent = self.__process_general_syntax_rule__(words, r, i)
                words = sent.split(' ')        
        for k in reqtype_ignore_rules[t].keys():
            sent = sent.replace(k, reqtype_ignore_rules[t][k])        
        sent = re.sub(r'\s+\'s', '\'s', sent)
        

        # TODO: experimental statement to replace all the commas to 'and' or 'or'
        if r := re.search(r"only\s+contains((\s*\w+\s*,)+)+,*\s*(and|or)\s*\w+\s*\.?", sent):
            g = list(r.groups())
            conj = g[-1]
            sent = sent.replace(g[0], g[0].replace(',', conj))
        # print(sent)
        sent = re.sub(r'\s+\'\s,\s\'\s+', '\',\'', sent)
        # sent = sent.replace('\' , \'', '\',\'')
        # print(sent)

        # TODO: experimental replacing all text number to integer
        index = []
        words = sent.split(' ')
        for i,w in enumerate(words):
            if __check_is_num_word__(w):
                index.append(i)
        if index:
            for i in index:
                words[i] = str(__convert_num_word__(words[i]))
        sent = ' '.join(words)
        return sent