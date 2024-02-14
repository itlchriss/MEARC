import os
import sys
import glob
import yaml
from typing import Dict, List
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


def main(filecontent: str) -> Dict[str, List[str]]:    
    models, si = _get_specs()
    conditions = _get_conditions(filecontent)
    
    results = { 'ensures': [], 'requires': []}
    for t in conditions:
        clist = conditions[t]
        for i, c in enumerate(clist):
            results[t].append(runengine(c))
    
    return results

if __name__ == "__main__":
    filepath = sys.argv[1]

    with open(filepath) as fp:
        filecontent = fp.read()
    
    if not filecontent or not filecontent.strip():
        exit(1)
    filecontent = filecontent.strip()
    results = main(filecontent)

    folder = '/'.join(filepath.split('/')[:-1])
    tmpfolder = os.path.join(folder, 'tmp')
    if not os.path.exists(tmpfolder):
        os.mkdir(tmpfolder)
    
    with open(os.path.join(tmpfolder, 'conditions.yml'), 'w') as fp:
        yaml.dump(results, fp, sort_keys=False, allow_unicode=True)