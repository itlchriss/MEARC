import os
import sys
from enum import IntEnum

import yaml
from typing import List

sis = []


def __add2sis(name: str, arguments: List[str], interpretation: str, syntax: List[str], _type: int = 3) -> None:
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
            'interpretation': interpretation,
        }
    )


class JavaTypes(IntEnum):
    Primitive = 0
    Array = 1
    Collection = 2


def main(filepath: str):
    # __add2sis('sorted in ascending order', ['x'], r'\forall int k; 0 <= k < (x).length - 1; (x)[k] <= '
    #                                               r'(x)[k + 1]', ['NN'])
    __add2sis('no spinal pathology', ['*'], r'normal', ['NN'])
    fp = open(filepath, 'w')
    yaml.dump(sis, fp, sort_keys=False, default_style=None, default_flow_style=False)


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
