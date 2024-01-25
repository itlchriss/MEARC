import sys
import os
import re
import shutil
from glob import glob


root_path = './datasets/leetcode-dataset/initial'


def _getfile(path:str) -> str:
    tmp = None
    print(path)
    if os.path.exists(path):
        with open(path, 'r') as fp:
            tmp = fp.read()
        if tmp:
            tmp = tmp.strip()
    return tmp

def main(srcpath:str):
    program = _getfile(os.path.join(srcpath, "Solution.java.no_annotation"))
    if not program:
        program = _getfile(os.path.join(srcpath, "Solution.java"))
    if not program:
        print('Program is not found under %s' % srcpath)
        return
    pre = _getfile(os.path.join(srcpath, "pre"))
    post = _getfile(os.path.join(srcpath, "post"))    
    tmp = ''
    # if pre:
    #     tmp += '//@ requires(*%s*);\n' % pre 
    if post:
        tmp += '//@ ensures(*%s*);\n' % post 
    if not tmp:
        print('No specifications found under %s' % srcpath)
        exit(-2)
    r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', program, re.ASCII)
    program = program[:r.start()] + '\n' + tmp + program[r.start():]
    if not os.path.exists(os.path.join(srcpath, "Solution.java.no_annotation")):
        shutil.copy2(os.path.join(srcpath, "Solution.java"), os.path.join(srcpath, "Solution.java.no_annotation"))
    with open(os.path.join(srcpath, "Solution.java"), 'w+') as fp:
        fp.write(program)
        
        


if __name__ == "__main__":
    # main(sys.argv[1])
    for d in glob(os.path.join(root_path, '*'), recursive = False):
        with open(os.path.join(d, "formula_constraints"), "w+") as fp:
            fp.write("")