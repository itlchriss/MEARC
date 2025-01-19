import os
import sys
import glob
import yaml
import re
from typing import Dict, List, Tuple
from preprocess.engine import runengine

modelspecspath = './specs/models'
sispecspath = './specs/si/typed_si.yml'

    

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


def __process_parameter_type_distrition(conditions):
    results = {'ensures': [], 'requires': []}
    patterns = [
        {
            'p': '(.*)\s+of\s+each\s+(integer\s+array\s+parameter)\s+(`[^`]+`(, `[^`]+`)*(, and `[^`]+`| and `[^`]+`))\s+(.*)',
            # 'forbidden': 'are'
        },
        {
            'p': '(.*)\s+in\s+each\s+(integer\s+array\s+parameter)\s+(`[^`]+`(, `[^`]+`)*(, and `[^`]+`| and `[^`]+`))\s+(.*)',
            # 'forbidden': 'is'
        }
    ]    
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.search(pattern['p'], sent):
                    # if pattern['forbidden'] in sent:
                    #     results[t].append(sent)
                    # else:
                    target = r.group(0)
                    subject= r.group(1)
                    parameter_type = r.group(2)
                    sequence = r.group(3).replace('and', '').replace(' ', '')
                    predicate = r.groups()[-1]
                    head = '%s of %s ' % (subject, parameter_type)
                    for param in sequence.split(','):
                        results[t].append(head + param + ' ' + predicate)     
                    processed = True
            if not processed:
                results[t].append(sent)                   
                                
    # [print(r) for r in results['ensures']]
    return results

def __process_either_or__(conditions):
    results = {'ensures': [], 'requires': []}
    patterns = [
        {
            'p': 'the\s+(\w+)\s+parameter\s+(`\w+`)\s+is\s+either\s+(.*)\s+or\s+(.*)',
        }
    ] 
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.search(pattern['p'], sent):                    
                    # print(r.group(0))
                    parameter_type = r.group(1)
                    param = r.group(2)
                    format = 'the %s parameter %s is %s or the %s parameter %s is %s' % (parameter_type, param, r.group(3), parameter_type, param, r.group(4))
                    format = format.replace('is has', 'has')
                    results[t].append(sent.replace(r.group(0), format))
                    processed = True
            if not processed:
                results[t].append(sent)     
    return results  

# Experimental: inferring the noun of a posessive pronoun
def __process_pronoun__(conditions):
    results = {'ensures': [], 'requires': []}
    patterns = [
        {
            'p': 'the\s+(\w+)\s+parameter\s+(`\w+`)\s+is\s+(.*)\s+and\s+all\s+its\s+(.*)\s+are\s+(.*)',
        }
    ] 
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.search(pattern['p'], sent):                    
                    # print(r.group(0))
                    parameter_type = r.group(1)
                    param = 'the %s parameter %s' % (parameter_type, r.group(2))
                    format = 'the %s is %s and the %s of the %s are %s' % (param, r.group(3), r.group(4), param, r.group(5))
                    format = format.replace('is has', 'has')
                    results[t].append(sent.replace(r.group(0), format))
                    processed = True
            if not processed:
                results[t].append(sent)     
    return results  


def __process_conditional_sentence_distribution(conditions):
    results = {'ensures': [], 'requires': []}
    patterns = [
        {
            'p': 'If\s+the\s+(\w+)\s+parameter\s+(`\w+`)\s+is\s+equal\s+to\s+(("[-+\.\w+]+",\s+)+"[-+\.0-9eE]+")',
            'type': 'string'
        }
    ] 
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.search(pattern['p'], sent):                    
                    # print(r.group(0))
                    _type = ""
                    if pattern['type'] == 'string':
                        # reserve for future use. other types can be applied
                        _type = 'type_string_'                    
                        format = 'If the %s parameter %s is equal to the %s' % (r.group(1), r.group(2), _type)
                    else:
                        format = 'If the %s parameter %s is equal to ' % (r.group(1), r.group(2))
                    for s in r.group(3).replace(' ', '').split(','):
                        if _type:
                            results[t].append(sent.replace(r.group(0), format + ' ' + s))
                        else:
                            results[t].append(sent.replace(r.group(0), format + ' ' + s))
                        # print(sent.replace(r.group(0), format + ' ' + s))
                    # results[t].append(sent.replace(r.group(0), format))
                    processed = True
            if not processed:
                results[t].append(sent)     
    return results


def __process_false_otherwise(conditions):
    results = {'ensures': [], 'requires': []}
    patterns = [
        {
            'p': ',\s+or\s+false\s+otherwise\.',
        }
    ] 
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.search(pattern['p'], sent):                    
                    # print(r.group(0))
                    _sent = sent.replace(r.group(0), '')
                    results[t].append(_sent)
                    if 'is' in sent:
                        results[t].append(_sent.replace('is', 'is not'))
                    elif 'are' in sent:
                        results[t].append(_sent.replace('are', 'are not'))
                    processed = True
            if not processed:
                results[t].append(sent)     
    return results  


def __process_and_false_clause(conditions):
    results = {'ensures': [], 'requires': []}
    patterns = [
        {
            'p': ',\s+and\s+false\s+(.*)\.',
        }
    ] 
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.search(pattern['p'], sent):   
                    target = 'The boolean result is false %s' % r.group(1)                
                    _sent = sent.replace(r.group(0), '.')
                    results[t].append(_sent)
                    results[t].append(target)
                    processed = True
            if not processed:
                results[t].append(sent)     
    return results

def __process_redundant_type_clause(conditions):
    patterns = [
        {
            'p': "the\s+('[\w\W]')\s+character",
        }
    ] 
    results = {'ensures': [], 'requires': []}
    for t in conditions:
        for sent in conditions[t]:                   
            processed = False
            for pattern in patterns:
                if r := re.findall(pattern['p'], sent):   
                    for _r in r:
                        target = 'the type_character_ %s' % _r              
                        _sent = sent.replace('the %s character' % _r, target)
                        results[t].append(_sent)
                        results[t].append(target)
                    processed = True
            if not processed:
                results[t].append(sent)     
    return results
    
def main(filecontent: str) -> Tuple[Dict[str, List[str]], List[Dict]]:    
    models, si = _get_specs()
    conditions = _get_conditions(filecontent)    
    
    results = { 'ensures': [], 'requires': []}
    sis = {}
    # do a preliminary checking on the sentences. if the sentence is too complicated with compound subject, we split it into multiple sentences
    conditions = __process_parameter_type_distrition(conditions)
    conditions = __process_either_or__(conditions)
    # Experimental
    conditions = __process_pronoun__(conditions)
    conditions = __process_conditional_sentence_distribution(conditions)
    conditions = __process_false_otherwise(conditions)
    conditions = __process_and_false_clause(conditions)
    conditions = __process_redundant_type_clause(conditions)
    #######
    for t in conditions:
        clist = conditions[t]
        for i, c in enumerate(clist):
            s, si = runengine(c, t)
            results[t].append(s)            
            # if si:
            #     for v in list(si.values()):
            #         if v not in sis:
            #             sis.append(v)    
            # sis.append(list(si.values()))
            if t == 'ensures':
                ditype = 'post'
            else:
                ditype = 'pre'
            sis['%s.%s' % (ditype, str(i))] = list(si.values())
    return results, sis

class NoAliasDumper(yaml.SafeDumper):
    def ignore_aliases(self, data):
        return True

if __name__ == "__main__":
    filepath = sys.argv[1]

    with open(filepath) as fp:
        filecontent = fp.read()
    
    if not filecontent or not filecontent.strip():
        exit(1)
    filecontent = filecontent.strip()
    conditions, sis = main(filecontent)


    folder = '/'.join(filepath.split('/')[:-1])
    tmpfolder = os.path.join(folder, 'tmp')
    if not os.path.exists(tmpfolder):
        os.mkdir(tmpfolder)
    
    with open(os.path.join(tmpfolder, 'conditions.yml'), 'w') as fp:
        yaml.dump(conditions, fp, sort_keys=False, allow_unicode=True, width=float("inf"))
    
    # for i, v in enumerate(sis):        
    for k in sis.keys():
        with open(os.path.join(tmpfolder, 'dynamic_si.%s.yml' % k), 'w') as fp:
            if not sis[k]:
                os.remove(os.path.join(tmpfolder, 'dynamic_si.%s.yml' % k))
            else:
                yaml.dump(sis[k], fp, sort_keys=False, allow_unicode=True, Dumper=NoAliasDumper)
    
    