import subprocess
import sys
import yaml
from yaml.loader import SafeLoader


def main(nlp_path: str, conditions_file: str, pycmd_path: str) -> None:
    with open(conditions_file) as fp:
        data = yaml.load(fp, Loader=SafeLoader)
    tmp_path = './tmp'
    for record in data:
        t = record['type']
        c = record['specification']
        print(c)
        process = subprocess.Popen(
            ("echo %s | sed -f %s/en/tokenizer.sed > %s/sentences.tok" % (c, nlp_path, tmp_path)),
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
        cmd = ("%s %s/scripts/semparse_wsc.py %s/sentences.xml %s/en/semantic_templates_en_emnlp2015.yaml" % (pycmd_path, nlp_path, tmp_path, nlp_path))
        process = subprocess.run(
            cmd.split(' '),
            stdout=subprocess.PIPE
        )
        # generated mr from ccg2lambda
        out = process.stdout.decode().strip()

        if not out:
            continue
        

if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2], sys.argv[3])
