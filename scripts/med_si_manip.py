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
                'type': int(_type)
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
                'type': int(_type)
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
                'type': int(_type)
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
                'type': int(_type)
            }
        )


class JavaTypes(IntEnum):
    Primitive = 0
    Array = 1
    Collection = 2
    # This is to specify the result from JML Expressions
    JML_expression_result = 3
    JML_expression_template = 4
    Others = 5


def main(filepath: str):
    # # appears in 28 papers
    # __add2sis('patients who had degenerative spondylolisthesis', ['*'], r'\\result == 2', ['NN', 'NNP'], _type = JavaTypes.JML_expression_result)
    # # appears in 54 papers
    # __add2sis('patients who had spondylolisthesis', ['*'], r'\\result == 2', ['NN', 'NNP'], _type = JavaTypes.JML_expression_result)
    # __add2sis('individuals with no spinal pathology', ['*'], r'\\result == 0', ['NN', 'NNP'], _type = JavaTypes.JML_expression_result)
    # __add2sis('individual with no spinal pathology', ['*'], r'\\result == 0', ['NN', 'NNP'], _type = JavaTypes.JML_expression_result)
    # __add2sis('patients with lumbar disk herniation at L4 to L5 level', ['*'], r'\\result == 1', ['NN', 'NNP'], _type = JavaTypes.JML_expression_result)
    # __add2sis('patient with lumbar disk herniation at L4 to L5 level', ['*'], r'\\result == 1', ['NN', 'NNP'], _type = JavaTypes.JML_expression_result)

    # __add2sis('have', ['Subj', 'Acc'], r'(Acc) ==> (Subj)', ['VBD', 'VBZ'], _type = JavaTypes.JML_expression_result, _specific_arg_types = [JavaTypes.JML_expression_result, JavaTypes.JML_expression_result])
    # __add2sis('be', ['Subj', 'Acc'], r'_sub(Subj)2(Acc)', ['VBD'], _type = JavaTypes.JML_expression_result, _specific_arg_types = [JavaTypes.Primitive, JavaTypes.JML_expression_template])
    # __add2sis('no spinal pathology', ['*'], r'normal', ['NN'])
    # __add2sis('normal range', ['X', 'Acc'], r'X')
    __add2sis('result', ['*'], r'\\result', ['NN'], _type=JavaTypes.Primitive)
    __add2sis('be', ['Subj', 'Acc'], r'_event_sub(Subj)2(Acc)', ['VBZ'], _type=JavaTypes.JML_expression_result,
              _specific_arg_types=[JavaTypes.Primitive, JavaTypes.JML_expression_template])
    __add2sis('be', ['Subj', 'Acc'], r'(Subj) == (Acc)', ['VBZ'], _type=JavaTypes.JML_expression_result,
              _specific_arg_types = [JavaTypes.Primitive, JavaTypes.JML_expression_result])
    __add2sis('be', ['Subj', 'Acc'], r'(Subj) == (Acc)', ['VBZ'], _type=JavaTypes.JML_expression_result,
              _specific_arg_types=[JavaTypes.JML_expression_result, JavaTypes.Primitive])
    __add2sis('be', ['Subj', 'Acc'], r'(Subj) == (Acc)', ['VBZ'], _type=JavaTypes.JML_expression_result,
              _specific_arg_types=[JavaTypes.Primitive, JavaTypes.Primitive])
    __add2sis('be', ['Subj', 'Acc'], r'(Subj) == (Acc)', ['VBZ'], _type=JavaTypes.JML_expression_result,
              _specific_arg_types=[JavaTypes.JML_expression_result, JavaTypes.JML_expression_result])
    __add2sis('range', ['Subj', 'Acc'], r'_sub(Subj)2(Acc)', ['VBZ'], _type = JavaTypes.JML_expression_result, _specific_arg_types = [JavaTypes.Primitive, JavaTypes.JML_expression_template])
    __add2sis('equal to', ['Subj', 'Acc'], r'(Subj) == (Acc)', ['VBZ'], _type=JavaTypes.JML_expression_result,
              _specific_arg_types=[JavaTypes.Primitive, JavaTypes.Primitive])
    __add2sis('greater than', ['Acc'], r'(Subj) > (Acc)', ['NNP'], _type=JavaTypes.JML_expression_template,
              _specific_arg_types=[JavaTypes.Primitive, JavaTypes.Primitive], _grammar_args=['Subj'])
    # __add2sis('than', ['x', 'y'], r'_sub(y)2(x)', ['IN'], _type=JavaTypes.JML_expression_result)
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
