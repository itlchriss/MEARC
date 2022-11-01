import subprocess
import sys
import yaml
from yaml.loader import SafeLoader


def main(nlp_path: str, conditions_file: str, pycmd_path: str, std_si_path: str) -> None:
    prog_name = conditions_file.replace('.conditions.yml', '').split('/')[-1]

    with open(conditions_file) as fp:
        data = yaml.load(fp, Loader=SafeLoader)
    tmp_path = './tmp'
    for i, record in enumerate(data):
        t = record['type']
        nlspec = record['specification']
        # print(nlspec)
        process = subprocess.Popen(
            ("echo %s | sed -f %s/en/tokenizer.sed > %s/sentences.tok" % (nlspec, nlp_path, tmp_path)),
            shell=True, stdout=subprocess.PIPE
        )
        process.wait()
        process = subprocess.Popen(
            ("%s/candc-1.00/bin/candc  --log %s/candc.log --models %s/candc-1.00/models --candc-printer xml --input "
             "%s/sentences.tok > %s/sentences.candc.xml" % (nlp_path, tmp_path, nlp_path, tmp_path, tmp_path)),
            shell=True, stdout=subprocess.PIPE
        )
        process.wait()
        process = subprocess.Popen(
            ("%s %s/en/candc2transccg_wsc.py %s/sentences.candc.xml > %s/sentences.xml" % (pycmd_path, nlp_path, tmp_path, tmp_path)),
            shell=True, stdout=subprocess.PIPE
        )
        process.wait()
        process = subprocess.Popen(
            ("%s %s/scripts/semparse_wsc.py %s/sentences.xml %s/en/semantic_templates_en_emnlp2015.yaml > "
             "%s/%s.conditions.mr.%d" % (pycmd_path, nlp_path, tmp_path, nlp_path, tmp_path, prog_name, i)),
            shell=True, stdout=subprocess.PIPE
        )
        process.wait()
        # cmd = ("%s %s/scripts/semparse_wsc.py %s/sentences.xml %s/en/semantic_templates_en_emnlp2015.yaml" % (pycmd_path, nlp_path, tmp_path, nlp_path))
        # process = subprocess.run(
        #     cmd.split(' '),
        #     stdout=subprocess.PIPE
        # )
        # # generated mr from ccg2lambda
        # mr = process.stdout.decode().strip()
        mr_file_path = ("%s/%s.conditions.mr.%d" % (tmp_path, prog_name, i))
        si_file_path = ("%s/%s.si.yml" % (tmp_path, prog_name))
        with open(mr_file_path) as fp:
            mr = fp.read()
        # print(mr.strip())
        if not mr or not mr.strip():
            print('MR generation failed for the following specification: ')
            print(nlspec)
            continue

        cmd = "./bin/main -f%s -s%s,%s" % (mr_file_path, si_file_path, std_si_path)
        process = subprocess.run(
            cmd.split(' '),
            stdout=subprocess.PIPE
        )
        # generated jml from translator
        cond = process.stdout.decode().strip()
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


if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4])
