import sys
import os
import re
import shutil
from glob import glob
from typing import List


root_path = './datasets/leetcode-behavioural'


def _getfile(path:str) -> List[str]:
    tmp = None
    if os.path.exists(path):
        with open(path, 'r') as fp:
            tmp = fp.read()
        if tmp and tmp.strip():
            tmp = tmp.strip()
    return tmp

def main(srcpath:str):
    program = _getfile(os.path.join(srcpath, "Solution.java.no_annotation"))
    if not program:
        program = _getfile(os.path.join(srcpath, "Solution.java"))
    if not program:
        print('Program is not found under %s' % srcpath)
        return
    post = _getfile(os.path.join(srcpath, "readme.p1"))    
    tmp = []
    if post:
        tmp += ['//@ ensures(*%s*);' % i for i in post.split('\n')]
    if not tmp:
        print('No specifications found under %s' % srcpath)
        exit(-2)
    tmp = '\n'.join(tmp)
    r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    program = program[:r.start()] + '\n' + tmp + program[r.start():]
    if not os.path.exists(os.path.join(srcpath, "Solution.java.no_annotation")):
        shutil.copy2(os.path.join(srcpath, "Solution.java"), os.path.join(srcpath, "Solution.java.no_annotation"))
    with open(os.path.join(srcpath, "Solution.java"), 'w+') as fp:
        fp.write(program)
        
        


if __name__ == "__main__":
    main(sys.argv[1])
    
