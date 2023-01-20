import os
import sys
from enum import IntEnum

import yaml
from typing import List

sis = []


def __add2sis(name: str, arguments: List[str], interpretation: str, syntax: List[str], _type: int = 3, _specific_arg_types: List[int] = None, _grammar_args: List[str] = None) -> None:
    if arguments and len(arguments) >= 1:
        for arg in arguments:
            if arg != '*' and ('(%s)' % arg) not in interpretation:
                print('Warning: SI %s following interpretation has no arguments used, but arguments are specified' % name)
                print('Interpretation: %s' % interpretation)
                print('Arguments: %s' % ','.join(arguments))
    if not _specific_arg_types and not _grammar_args:
        sis.append(
            {
                'term': name,
                'syntax': syntax,
                'arity': len(arguments),
                'arguments': [('(%s)' % arg) for arg in arguments],
                'interpretation': interpretation,
            }
        )
    elif _specific_arg_types and _grammar_args:
        sis.append(
            {
                'term': name,
                'syntax': syntax,
                'arity': len(arguments),
                'arguments': [('(%s)' % arg) for arg in arguments],
                'g_arity': len(_grammar_args),
                'grammar_arguments': [('(%s)' % arg) for arg in _grammar_args],
                'specific_arg_types': [('%d' % t) for t in _specific_arg_types],
                'interpretation': interpretation,
            }
        )
    elif _specific_arg_types:
        sis.append(
            {
                'term': name,
                'syntax': syntax,
                'arity': len(arguments),
                'arguments': [('(%s)' % arg) for arg in arguments],
                'specific_arg_types': [('%d' % t) for t in _specific_arg_types],
                'interpretation': interpretation,
            }
        )
    else:
        sis.append(
            {
                'term': name,
                'syntax': syntax,
                'arity': len(arguments),
                'arguments': [('(%s)' % arg) for arg in arguments],
                'g_arity': len(_grammar_args),
                'grammar_arguments': [('(%s)' % arg) for arg in _grammar_args],
                'interpretation': interpretation,
            }
        )


class JavaTypes(IntEnum):
    Primitive = 0
    Array = 1
    Collection = 2


def main(filepath: str):    
    __add2sis('sorted in ascending order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] <= '
                                                  r'(x)[k + 1]', ['NN'])
    __add2sis('sorted in ascending order', ['x'], r'\forall int k; (x); ((y))[k] <= '
                                                  r'((y))[k + 1]', ['JJ'], _grammar_args=['y'])
    __add2sis('sorted in ascending numerical order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] <= '
                                                            r'(x)[k + 1]', ['NN'])
    __add2sis('sorted in descending order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] >= '
                                                   r'(x)[k + 1]', ['NN'])
    __add2sis('greater than or equal to', ['x', 'y'], r'(x) >= (y)', ['JJ', 'JJR', 'VBG'])
    __add2sis('less than or equal to', ['x', 'y'], r'(x) <= (y)', ['JJ', 'JJR', 'VBG'])
    # __add2sis('correspondingly equal to', ['x', 'y'], r'\forall int i; 0 <= i < (x).length; (x)[i] == (y)[i]', ['VBG'])
    # __add2sis('correspondingly equal to', ['x', 'y'], r'\forall int i; 0 <= i < (x).length && (x).length == ('
    #                                                   r'y).length; (x) == (y)', ['VBG'], int(JavaTypes.Array))
    __add2sis('correspondingly equal to', ['x', 'y'], r'(x) != null && (y) != null && (x).length == (y).length && (('
                                                      r'\forall int i; 0 <= i < (x).length;  ((x)[i] == null && (y)['
                                                      r'i] == null) || ((x)[i].equals((y)[i]))))', ['VBG'], int(JavaTypes.Array))
    # __add2sis('deeply equal to', ['x', 'y'], r'\forall int i; 0 <= i < (x).length; (((x)[i] == null && (y)[i] == '
    #                                          r'null) || ((x)[i] == (y)[i]) || ((x)[i].equals( (y)[i] )) && (x).length '
    #                                          r'== (y).length && (Arrays.equals((x), (y)))', ['VBZ'])
    __add2sis('deeply equals to', ['x', 'y'], r'((((x) == null && (y) == null) && (((x) == (y)) || ((x).equals((y))) || Arrays.equals((x), (y)))) || (((x) != null && (y) != null && (x).length == (y).length) && (\forall int i; 0 <= i < (x).length; (((x)[i] == null && (y)[i] == null) || ((x)[i] != null && (y)[i] != null && (!(x)[i].getClass().isArray() && !(y)[i].getClass().isArray() && (x)[i].equals((y)[i])))))))', ['VBZ'])                                        
    __add2sis('length of', ['x'], r'(x).length', ['NN', 'JJ'])
    __add2sis('equals to', ['x', 'y'], r'(x) == (y)', ['JJ', 'JJR', 'VBG', 'VBZ'])    
    __add2sis('equal to', ['x', 'y'], r'((x).size() == (y).size()) && (\forall int j; 0 <= j < (y).size(); ((x).contains((y).get(j))))', ['VBG'], _specific_arg_types=[JavaTypes.Collection, JavaTypes.Collection])
    __add2sis('equal to', ['x', 'y'], r'(x) == (y)', ['JJ', 'JJR', 'VBG', 'VBZ'], _specific_arg_types=[JavaTypes.Primitive, JavaTypes.Primitive])
    __add2sis('true_value', ['*'], 'true', ['NN'])
    __add2sis('false_value', ['*'], 'false', ['NN'])
    __add2sis('null_value', ['*'], 'null', ['NN'])
    __add2sis('be', ['x', 'y'], r'(x) == (y)', ['VBZ'])
    __add2sis('return value', ['*'], r'\result', ['NN'])
    # __add2sis('be', ['x'], r'(x)', ['VBZ'])
    # __add2sis('contain', ['x', 'y'], r'\exists int i; 0 <= i < (x).length; (x)[i] == (y)', ['VBZ'], int(JavaTypes.Array))
    # __add2sis('contain', ['x', 'y'], r'\exists int i; 0 <= i < (x).size(); (x)[i] == (y)', ['VBZ'], int(JavaTypes.Collection))
    # __add2sis('contain', ['x', 'y'], r'\exists int i; 0 <= i < (x).length; (x)[i] == (y)', ['VBZ'],
    #           int(JavaTypes.Array))
    #__add2sis('contain', ['x', 'y'], r'\exists int i; 0 <= i < (x).size(); (x)[i] == (y)', ['VBZ'],
       #       int(JavaTypes.Collection))
    __add2sis('contain', ['x', 'y'], r'((y) == null && (\exists int i; 0 <= i < (x).size(); (x).get(i) == null)) '
                                     r'|| (\exists int i; 0 <= i < (x).size();(x).get(i) == (y))',
              ['VBZ'],
              int(JavaTypes.Collection))
    __add2sis('contain', ['x', 'y'], r'\exists int i; 0 <= i < \old((x)).size(); \old((x)).get(i) == (y)', ['VBD'], _specific_arg_types=[JavaTypes.Collection, JavaTypes.Primitive])
    __add2sis('contain', ['x', 'y'], r'(\exists int i; 0 <= i < (x).length; (x)[i] == (y)) ',
              ['VBZ', 'VB'],
              int(JavaTypes.Array), _specific_arg_types = [JavaTypes.Array, JavaTypes.Primitive])
    __add2sis('removed from', ['x', 'y'], r'!(\exists int i; 0 <= i < (y).size(); (y).get(i) == (x))', ['VBG'], _specific_arg_types=[JavaTypes.Collection, JavaTypes.Primitive])
    __add2sis('result', ['*'], r'\result', ['NN'])
    __add2sis('prime', ['x'], r'(x) == 2 || ((x) > 2 && (\forall int k; (x) > 2 && 2 <= k && k <= (x)/2; (x)%k != 0',
              ['NN'])
    __add2sis('parameter', ['*'], r'\param', ['NN'])
    __add2sis('input', ['*'], r'\param', ['NN'])
    __add2sis('even', ['x'], r'(x) % 2 == 0', ['RB'])
    __add2sis('prime number', ['x'], r'(x) == 2 || ((x) > 2 && (\forall int k; (x) > 2 && 2 <= k && k <= (x)/2; (x) % k '
                                     r'!= 0', ['NN'])
    __add2sis('length of', ['x'], r'(x).size()', ['NN', 'JJ'])
    __add2sis('size of', ['x'], r'(x).size()', ['JJ', 'NN'])
    __add2sis('sort', ['x'], r'\forall int k; 0 <= k && k < (x).length-1; (x)[k] <= (x)[k+1]', ['VBN'])
    __add2sis('index', ['y'], r'\old(Arrays.asList(((x))).indexOf((y))))', ['NN'], _grammar_args = ['x'])
    __add2sis('of', ['x', 'y'], r'\sub(x)2(y)', ['IN'])
    __add2sis('in', ['x', 'y'], r'\sub(y)2(x)', ['IN'])
    # __add2sis('if', ['x', 'y'], r'((y)) ==> ((x))', ['IN'])
    __add2sis('if', ['*', '*'], r'==>', ['IN'], _type=3)
    # __add2sis('elements of array', ['x', 'y', 'z'], r'\forall int i; 0 <= i < (x).length; (x)[i](y)(z)', ['NN'])
    __add2sis('element', ['x'], r'(x)[i]', ['NNS'])
    __add2sis('every element', ['x'], r'\forall int i; 0 <= i < (x).length; (x)[i]', ['NN'])
    __add2sis('reference of', ['x'], r'(x)', ['NN'])
    # __add2sis('change', ['x'], r'((x).size() != \old((x)).size()) || (\exists int i; 0 <= i < (x).size(); (x)[i] != '
    #                            r'\old((x))[i])', ['VBN'], int(JavaTypes.Array))
    __add2sis('change', ['x'], r'((x).size() != \old((x)).size()) || (\exists int i; 0 <= i < (x).size(); (x).get(i) '
                               r'!= \old((x)).get(i))', ['VBN', 'VBD'], int(JavaTypes.Collection))
    __add2sis('empty', ['x'], r'(x).size() == 0', ['JJ'])
    __add2sis('number of elements', ['x'], r'(x).size()', ['NNS'])
    __add2sis('for', ['x', 'y'], r'\sub(x)2(y)', ['NNS'])
    __add2sis('all of the elements', ['x'], r'\forall int i; 0 <= i < (x).length; ', ['NNS'])
    __add2sis('all valid indices', ['x'], r'\forall int i; 0 <= i < (x).length; ', ['NNS'])
    __add2sis('elements of', ['x'], r'\forall int i; 0 <= i < (x).length; (x)[i]', ['NN'])
    __add2sis('elements of', ['x'], r'(x)', ['JJ'])
    __add2sis('insertion point', ['x'], r'(\forall int j; 0 <= j < ((z)); (x)[j] < (y)) && (\forall int j; ((z)) <= j < (x).length; (y) < (x)[j])', ['NN'], _specific_arg_types=[JavaTypes.Array], _grammar_args=['y', 'z'])
    __add2sis('minus', ['x', 'y'], r'(x) - (y)', ['IN'])
    __add2sis('negative', ['x'], r'-(x)', ['JJ'])
    fp = open(filepath, 'w')
    yaml.dump(sis, fp, sort_keys=False, default_style=None, default_flow_style=False)


# def _main(filepath: str):
#     sis = []
#     fp = open(filepath, 'a+')
#     data = yaml.full_load(fp)
#     print('===========Start manipulation===========')
#     for line in sys.stdin:  # type: str
#         print('1. Input a semantic interpretation')
#         print('2. Delete a semantic interpretation')
#         print('3. Save changes and close')
#         print('4. Discard changes and close')
#         print('Please choose one of the options[1|2|3|4]: ', end='')
#         choice = line.strip()
#         if choice == '4':
#             while True:
#                 print('Are you sure to discard all changes?[Y|n]', end='')
#                 for ans in sys.stdin:  # type: str
#                     if ans not in ['Y', 'n']:
#                         continue
#                     elif ans == 'Y':
#                         exit(1)
#                     else:
#                         break
#         elif choice == '3':
#             if sis:
#
#                 yaml.dump(sis, fp, sort_keys=False)
#                 print('%d terms are written to the target file')
#         else:
#             if r := __analyse(line.strip()):
#                 sis.append(r)
#             else:
#                 print('Something is wrong with the previous input. Please check with the input format')


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print('===========Semantic interpretation manipulation tool===========')
        print('This tool is a helper tool to manipulate semantic interpretations in yaml format')
        print('Please provide a filepath in the first argument')
        print('The file at the path should be a yaml format file. '
              'If the file at the path does not exist, a file is created at the path.')
    elif not os.path.exists(sys.argv[1]):
        main(sys.argv[1])
    else:
        main(sys.argv[1])
