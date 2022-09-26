import os
import sys
import yaml
from typing import List

sis = []


def __add2sis(name: str, arguments: List[str], interpretation: str, syntax: List[str]) -> None:
    if arguments and len(arguments) >= 1:
        for arg in arguments:
            if arg != '*' and ('(%s)' % arg) not in interpretation:
                print('Warning: The following interpretation has no arguments used, but arguments are specified')
                print('Interpretation: %s' % interpretation)
                print('Arguments: %s' % ','.join(arguments))
    sis.append(
        {
            'term': name,
            'syntax': syntax,
            'arity': len(arguments),
            'arguments': [('(%s)' % arg) for arg in arguments],
            'interpretation': interpretation
        }
    )


def main(filepath: str):
    __add2sis('sorted in ascending order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] <= '
                                                  r'(x)[k + 1]', ['NN'])
    __add2sis('sorted in ascending numerical order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] <= '
                                                            r'(x)[k + 1]', ['NN'])
    __add2sis('sorted in descending order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] >= '
                                                   r'(x)[k + 1]', ['NN'])
    __add2sis('greater than or equal to', ['x', 'y'], r'(x) >= (y)', ['JJ', 'JJR', 'VBG'])
    __add2sis('less than or equal to', ['x', 'y'], r'(x) <= (y)', ['JJ', 'JJR'])
    __add2sis('correspondingly equal to', ['x', 'y'], r'\forall int i; 0 <= i < (x).length; (x)[i] == (y)[i]', ['VBG'])
    __add2sis('deeply equal to', ['x', 'y'], r'\forall int i; 0 <= i < (x).length; (((x)[i] == null && (y)[i] == '
                                             r'null) || ((x)[i] == (y)[i]) || ((x)[i].equals( (y)[i] )) || (('
                                             r'x).getClass().isArray() && (x).getClass().isArray() && Arrays.equals(('
                                             r'x), (y))))', ['VBZ'])
    __add2sis('length of', ['x'], r'(x).length', ['NN'])
    __add2sis('equals to', ['x', 'y'], r'(x) == (y)', ['JJ', 'JJR', 'VBG', 'VBZ'])
    __add2sis('equal to', ['x', 'y'], r'(x) == (y)', ['JJ', 'JJR', 'VBG', 'VBZ'])
    __add2sis('true_value', ['*'], 'true', ['NN'])
    __add2sis('false_value', ['*'], 'false', ['NN'])
    __add2sis('null_value', ['*'], 'null', ['NN'])
    __add2sis('be', ['x', 'y'], r'(x) == (y)', ['VBZ'])
    __add2sis('be', ['x'], r'(x)', ['VBZ'])
    __add2sis('contain', ['x', 'y'], r'\exists int i; 0 <= i < (x).length; (x)[i] == (y)', ['VBZ'])
    __add2sis('result', ['*'], r'\result', ['NN'])
    __add2sis('prime', ['x'], r'(x) == 2 || ((x) > 2 && (\forall int k; (x) > 2 && 2 <= k && k <= (x)/2; (x)%k != 0',
              ['NN'])
    __add2sis('parameter', ['*'], r'\param', ['NN'])
    __add2sis('input', ['*'], r'\param', ['NN'])
    __add2sis('even', ['x'], r'(x) % 2 == 0', ['RB'])
    __add2sis('prime number', ['x'], r'(x) == 2 || ((x) > 2 && (\forall int k; (x) > 2 && 2 <= k && k <= x/2; (x) % k '
                                     r'!= 0', ['NN'])
    __add2sis('length', ['x'], r'(x).size()', ['NN', 'JJ'])
    __add2sis('size', ['x'], r'(x).size()', ['JJ', 'NN'])
    __add2sis('sort', ['x'], r'\forall int k; 0 <= k && k < (x).length-1; (x)[k] <= (x)[k+1]', ['VBN'])
    __add2sis('index', ['x', 'y'], r'Arrays.asList((x)).indexOf((y)))', ['JJ'])
    __add2sis('of', ['x', 'y'], r'\sub(x)2(y)', ['IN'])
    __add2sis('in', ['x', 'y'], r'\sub(y)2(x)', ['IN'])
    __add2sis('elements of array', ['x', 'y', 'z'], r'\forall int i; 0 <= i < (x).length; (x)[i](y)(z)', ['NN'])
    __add2sis('elements of', ['x'], r'\forall int i; 0 <= i < (x).length; (x)[i]', ['NN'])
    __add2sis('elements of', ['x'], r'(x)', ['JJ'])
    __add2sis('every element', ['x'], r'\forall int i; 0 <= i < (x).length; (x)[i]', ['NN'])
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
