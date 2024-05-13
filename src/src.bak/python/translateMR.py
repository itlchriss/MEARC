import subprocess
import sys
import yaml
import os
from typing import Dict
from yaml.loader import SafeLoader

_configs = { 'ROOT': 'ROOT', 'NLP': 'NLP', 'STD_SI': 'STD_SI', 'PYCMD': 'PYCMD', 'TMP': 'TMP'}


class dot_access_dict(dict):
    __getattr__ = dict.get
    __setattr__ = dict.__setitem__
    __delattr__ = dict.__delitem__


def warn_missing_config(key: str) -> None:
    print('Please fill the value of %s in mearc.config' % key)
    exit(-1)


def run_cmd_with_output(cmd: str) -> Dict[str, str]:
    process = subprocess.run(
        cmd.split(' '),
        shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    process.wait()
    return { 'stdout': process.stdout.decode(), 'stderr': process.stderr.decode() }


def run_cmd_without_output(cmd: str) -> None:
    process = subprocess.Popen(
            cmd,
            shell=True, stdout=subprocess.PIPE
        )
    process.wait()


def run_cmd_show_output(cmd: str) -> None:
    process = subprocess.Popen(
            cmd,
            shell=True
        )
    process.wait()


debug = True
info = False

def main(config_path: str, prog_file: str) -> None:
    with open('../../mearc.config') as fp:
        _config = yaml.load(fp, Loader=SafeLoader)
    for key in _configs:
        if not _config[key] or not _config[key].strip():
            warn_missing_config(key)
        else:
            _configs[key] = _config[key]
    configs = dot_access_dict(_configs)
    # prog_name = prog_file.replace('.conditions.yml', '').split('/')[-1]
    prog_name = prog_file.replace('.java', '').split('/')[-1]
    si_file_path = ("%s/%s.si.yml" % (configs.TMP, prog_name))

    # create tmp folder
    # run_cmd_without_output('mkdir -p %s; rm -rf %s/*' % (configs.TMP, configs.TMP))
    run_cmd_without_output('mkdir -p %s;' % (configs.TMP))
    # preprocessing 
    # cmd = "%s ./src/python/preprocessor.py %s %s %s" % (configs.PYCMD, prog_file, configs.STD_SI, configs.TMP)
    # run_cmd_show_output(cmd)
    # prog_condition_file = "%s/%s.conditions.yml" % (configs.TMP, prog_name)
    prog_si_file = "%s/%s.si.yml" % (configs.TMP, prog_name)
    if not os.path.exists(prog_condition_file) or not os.path.exists(prog_si_file):
        print('Preprocessing failed.')
        exit(-2)
    else:
        info and print('INFO:Preprocessing done')
    with open("%s/%s.conditions.yml" % (configs.TMP, prog_name)) as fp:
        data = yaml.load(fp, Loader=SafeLoader)
    for i, record in enumerate(data):
        t = record['type']
        nlspec = record['specification']
        info and print('INFO:using ccg2lambda to get MR from (%s)' % nlspec)
        debug and print('DEBUG: ccgslambda - tokenization')
        process = subprocess.Popen(
            ("echo \"%s\" | sed -f %s/en/tokenizer.sed > %s/sentences.tok" % (nlspec, configs.NLP, configs.TMP)),
            shell=True, stdout=subprocess.PIPE
        )        
        process.wait()
        debug and print('DEBUG: ccgslambda - C & C parsing')
        process = subprocess.Popen(
            ("%s/candc-1.00/bin/candc  --log %s/candc.log --models %s/candc-1.00/models --candc-printer xml --input "
             "%s/sentences.tok > %s/sentences.candc.xml" % (configs.NLP, configs.TMP, configs.NLP, configs.TMP, configs.TMP)),
            shell=True, stdout=subprocess.PIPE
        )
        process.wait()
        debug and print('DEBUG: ccgslambda - translate C & C parsing result to CCG rules')
        process = subprocess.Popen(
            ("%s %s/en/candc2transccg_wsc.py %s/sentences.candc.xml > %s/sentences.xml" % (configs.PYCMD, configs.NLP, configs.TMP, configs.TMP)),
            shell=True, stdout=subprocess.PIPE
        )
        process.wait()
        debug and print('DEBUG: ccgslambda - mapping CCG rules with the templates')
        process = subprocess.run(
            ("%s %s/scripts/semparse_wsc.py %s/sentences.xml %s/en/semantic_templates_en_emnlp2015.yaml > "
             "%s/%s.conditions.mr.%d" % (configs.PYCMD, configs.NLP, configs.TMP, configs.NLP, configs.TMP, prog_name, i)),
            shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
        )
        mr_file_path = ("%s/%s.conditions.mr.%d" % (configs.TMP, prog_name, i))        
        mr_error = process.stderr.decode()
        mr_stdout = process.stdout.decode()    
        
        if mr_error:
            print('Failed', file=sys.stderr)
            with open('%s/%s.error' % (configs.TMP, prog_name), 'a') as fp:
                fp.write(mr_error)
            break
        else:
            info and print('INFO:MR generation done')
            
        with open(mr_file_path) as fp:
            mr = fp.read()
        if not mr or not mr.strip():
            print('MR generation failed for the following specification: ')
            print(nlspec)            
            continue
        else:
            debug and print('DEBUG: going to translate the following mr: ')
            debug and print('DEBUG: ', mr)
            pass

        cmd = "./bin/main -f%s -s%s,%s" % (mr_file_path, si_file_path, configs.STD_SI)
        process = subprocess.run(
            cmd.split(' '),
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE
        )
        # generated jml from translator
        cond = process.stdout.decode().strip()        
        error = process.stderr.decode()
        if cond:
            keyword = None
            if t == 'precondition':
                keyword = 'requires'
            elif t == 'postcondition':
                keyword = 'ensures'
            elif t == 'invariant':
                keyword = 'invariant'

            if keyword:
                print('%s(%s);' % (keyword, cond))
            else:
                print('Compiler output: %s ' % cond)
                print('Unknown condition type: %s.' % t)
        else:
            print('Failed', file=sys.stderr)


if __name__ == "__main__":
    # main(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4])
    if len(sys.argv) == 3:
        debug = False
        main(sys.argv[1], sys.argv[2])
    elif len(sys.argv) == 4:
        debug = True
        main(sys.argv[1], sys.argv[2])
    else:
        print('unknown number of arguments provided')
