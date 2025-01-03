import sys
import os
import re
import shutil
from glob import glob
from typing import List




def _getfile(path:str) -> str:
    tmp = None
    if os.path.exists(path):
        with open(path, 'r') as fp:
            tmp = fp.read()
        if tmp and tmp.strip():
            tmp = tmp.strip()
    return tmp


def _dogpt35(srcpath: str, program: List[str]):
    srcpath = srcpath.replace('Solution.java', '')
    raw = _getfile(os.path.join(srcpath, "gpt-results", "gpt-3.5-turbo.result"))    
    tmp = []

    raw = re.sub(r'\n\s+', ' ', raw)
    for _t in raw.split('\n'):
        t = _t.replace('- requires', '//@ requires').replace('- ensures', '//@ ensures')
        if t[0] == '-':
            t = '//@ requires' + t[1:]
            # already a syntax error
        if t[-1] != ';':
            t = t.replace('//@', '//')
        tmp.append(t)

    tmp = list(set(tmp))

    tmp = '\n'.join(tmp)

    # r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    program = program[:r.start()] + '\n' + tmp + program[r.start():]
    print(program)

def _dosym(srcpath: str, program: List[str]):
    import glob
    files = glob.glob(os.path.join(srcpath, "jml", "*.jml"))
    # print(files, file=sys.stderr)
    tmp = []
    for file in files:
        with open(file, 'r') as fp:
            data = fp.read()
        if 'Lexer:' in data:
            continue
        tmp.append(r'//@ ' + data.replace('\n', '').replace('\ forall', r'\forall'))
    tmp = '\n'.join(tmp)
    r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    program = program[:r.start()] + '\n' + tmp + program[r.start():]

    target = 'public class'
    if '@SuppressWarning' in program:
        target = '@SuppressWarning' 
    if 'import java.util.Arrays' not in program:
        program = program.replace(target, 'import java.util.Arrays;\n\n%s' % target)
    if 'import java.util.Collections' not in program:
        program = program.replace(target, 'import java.util.Collections;\n\n%s' % target)
    print(program)
    # with open(os.path.join(srcpath, "hart-results", "build", "Solution.java"), 'w+') as fp:
        # fp.write(program)

def main(srcpath:str, mode: str):
    # print(os.path.join(srcpath.replace('Solution.java', ''), "Solution.java.no_annotation"))
    program = _getfile(os.path.join(srcpath.replace('Solution.java', ''), "Solution.java.no_annotation"))
    print(srcpath, file=sys.stderr)
    if mode == 'llm':
        _dogpt35(srcpath, program)
    else:
        _dosym(srcpath, program)
        


if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2])
    
