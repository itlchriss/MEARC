import sys
import os
import re
import shutil
from glob import glob
from typing import List


# root_path = './datasets/selected-leetcode-set'
root_path = './test'


def _getfile(path:str) -> List[str]:
    tmp = None
    if os.path.exists(path):
        with open(path, 'r') as fp:
            tmp = fp.read()
        if tmp and tmp.strip():
            tmp = tmp.strip()
    return tmp

def _appendfile(path:str, lines:List[str], program: str) -> None:
    if not os.path.exists(path):
        os.mkdir(path)
   
    tmp = []
    if lines:
        tmp += ['//@ requires(*%s*);' % re.sub(r'^-\s+', '', i) for i in lines.split('\n') if 'result' not in i and 'parameter' in i]
        tmp += ['//@ ensures(*%s*);' % re.sub(r'^-\s+', '', i) for i in lines.split('\n') if 'result' in i]        
    if not tmp:
        print('No specifications found under %s' % path)
        exit(-2)
    tmp = '\n'.join(tmp)
    r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    _program = program[:r.start()] + '\n' + tmp + program[r.start():]
    with open(os.path.join(path, "Solution.java"), 'w+') as fp:
        fp.write(_program)

def main(srcpath:str):
    program = _getfile(os.path.join(srcpath, "Solution.java.no_annotation"))
    if not program:
        print('Program is not found under %s' % srcpath)
        return
    
    gpt4lines = _getfile(os.path.join(srcpath, "rnl-gpt-4o.txt"))   
    starchatlines = _getfile(os.path.join(srcpath, "rnl-starchat.txt"))  

    _appendfile(os.path.join(srcpath, 'gpt4'), gpt4lines, program)
    _appendfile(os.path.join(srcpath, 'starchat'), starchatlines, program)

    # lines = _getfile(os.path.join(srcpath, "rnl.txt"))        
    # tmp = []
    # if lines:
    #     tmp += ['//@ requires(*%s*);' % re.sub(r'^-\s+', '', i) for i in lines.split('\n') if 'result' not in i]
    #     tmp += ['//@ ensures(*%s*);' % re.sub(r'^-\s+', '', i) for i in lines.split('\n') if 'result' in i]        
    # if not tmp:
    #     print('No specifications found under %s' % srcpath)
    #     exit(-2)
    # tmp = '\n'.join(tmp)
    # r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    # program = program[:r.start()] + '\n' + tmp + program[r.start():]
    # if not os.path.exists(os.path.join(srcpath, "Solution.java.no_annotation")):
    #     shutil.copy2(os.path.join(srcpath, "Solution.java"), os.path.join(srcpath, "Solution.java.no_annotation"))
    # with open(os.path.join(srcpath, "Solution.java"), 'w+') as fp:
    #     fp.write(program)
        
        


if __name__ == "__main__":
    main(sys.argv[1])
    
