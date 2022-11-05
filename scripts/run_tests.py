import os
from util import run_cmd_without_output, run_cmd_with_output, run_cmd_directly

# _cmd = "./mearc ./datasets/patterns/%s/%s"
_cmd = './mearc ./mearc.config ./datasets/patterns/%s/%s'
benchmark_dir = './datasets/patterns'


def main():
    print('Comparing JML generated from MR to expected results')
    run_cmd_without_output("rm -rf ./tmp/*; mkdir -p ./tmp/test; rm -rf ./datasets/output; mkdir -p ./datasets/output")
    succeeded_cases = {}
    for dataset in os.listdir(benchmark_dir):
        for filename in os.listdir(os.path.join(benchmark_dir, dataset)):
            test_name = filename.replace('.java', '')
            cmd = _cmd % (dataset, filename)
            # process = subprocess.run(
            #     cmd.split(' '),
            #     stdout=subprocess.PIPE,
            #     stderr=subprocess.PIPE
            # )
            output, error = run_cmd_with_output(cmd)
            output = output.strip()
            # # generated jml from translator
            # output = process.stdout.decode().strip()
            # error = process.stderr.decode()
            if 'parse error' in error:
                r = 'NLP error (I)'
                with open("./datasets/output/%s.error" % test_name, "w") as ofp: 
                    ofp.write(error.strip())
                print('Error information is written to ./datasets/output/%s.error' % test_name)
            else:
                if not output:
                    r = 'Failed'
                else:
                    with open("./datasets/output/%s.conditions.jml" % test_name, "w") as ofp: 
                        ofp.write(output)
                    print('Output is written to ./datasets/output/%s.conditions.jml' % test_name)
                    with open("./datasets/results/%s.conditions.jml" % test_name) as fp:
                        e = fp.read().strip()
                    if e == 'Failed' and output == e:
                        r = 'Expected Failed. And the result is as expected.'
                    elif output != e:
                        r = 'Failed'
                    else:
                        r = 'Success'
                        succeeded_cases[test_name] = os.path.join(os.path.join(benchmark_dir, dataset), filename)
            print('Dataset %s : %s' % (test_name, r))
    print('Successful translated datasets are subjected to static testing')
    for ds in succeeded_cases:
        cmd = 'python ./scripts/esc.py %s %s ./mearc.config' % (succeeded_cases[ds], './datasets/output/%s.conditions.jml' % ds)
        print(cmd)
        run_cmd_directly(
            cmd
        )




if __name__ == "__main__":
    main()

