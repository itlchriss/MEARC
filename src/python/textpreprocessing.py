import os
import sys
import glob
import yaml
from typing import Dict
from preprocess.engine import runengine

modelspecspath = './specs/models'
sispecspath = './specs/si/std_si_2023.yml'

def _get_specs():
    models = {}
    for f in glob.glob(modelspecspath + '/*'):
        name = f.split('/')[-1]
        with open(f) as fp:
            models[name] = fp.read().strip()
    si = {}
    for f in glob.glob(sispecspath + '/*'):
        with open(f) as fp:
            si = yaml.full_load(fp)
    return {'models': models, 'si': si}

def _get_conditions(text: str) -> Dict[str, str]:
    conditions = { 'requires': [], 'ensures': [] }
    import re
    if r := re.findall(r'\s+\/\/@\s+(requires|ensures)\(\*(.*)\*\);', text, re.ASCII):
        for t, c in r:
            conditions[t].append(c)    
    return conditions


def main(filecontent: str) -> None:    
    models, si = _get_specs()
    conditions = _get_conditions(filecontent)
    
    for t in conditions:
        clist = conditions[t]
        for i, c in enumerate(clist):
            clist[i] = runengine(c)

if __name__ == "__main__":
    filepath = sys.argv[1]

    with open(filepath) as fp:
        filecontent = fp.read()
    
    if not filecontent or not filecontent.strip():
        exit(1)
    filecontent = filecontent.strip()
    main(filecontent)