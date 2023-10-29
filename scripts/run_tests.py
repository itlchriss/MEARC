import os
from util import run_cmd_without_output, run_cmd_with_output, run_cmd_directly

# _cmd = "./mearc ./datasets/patterns/%s/%s"
_cmd = 'python mearc ./mearc.config ./datasets/patterns/%s/%s'
benchmark_dir = './datasets/patterns'


def main():
    # run_cmd_without_output("make clean; make")
    print('Comparing JML generated from MR to expected results')
    run_cmd_without_output("rm -rf ./tmp/*; mkdir -p ./tmp/test; rm -rf ./datasets/output; mkdir -p ./datasets/output")
    succeeded_cases = {}
    failed_cases = []
    number_of_tests = 0
    for dataset in os.listdir(benchmark_dir):
        for filename in os.listdir(os.path.join(benchmark_dir, dataset)):
            test_name = filename.replace('.java', '')
            cmd = _cmd % (dataset, filename)
            number_of_tests += 1
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
                    failed_cases.append((dataset, test_name))
                else:
                    with open("./datasets/output/%s.conditions.jml" % test_name, "w") as ofp: 
                        ofp.write(output)
                    print('Output is written to ./datasets/output/%s.conditions.jml' % test_name)
                    with open("./datasets/results/%s.conditions.jml" % test_name) as fp:
                        e = fp.read().strip()
                    if e == 'Failed' and output == e:
                        r = 'Expected Failed. And the result is as expected.'
                        failed_cases.append((dataset, test_name))
                    elif output != e:
                        r = 'Failed'
                        failed_cases.append((dataset, test_name))
                    else:
                        r = 'Success'
                        succeeded_cases[test_name] = os.path.join(os.path.join(benchmark_dir, dataset), filename)
            print('Dataset(%s) benchmark(%s) : %s' % (dataset, test_name, r))
    print('%d out of %d benchmarks are translated successfully' % (len(succeeded_cases), number_of_tests))
    if failed_cases:
        print('Translation failed cases are: ')
        [print('Dataset(%s) benchmark(%s)' % (t[0], t[1])) for t in failed_cases]
    print('Successful translated datasets are subjected to static testing')
    for ds in succeeded_cases:
        cmd = 'python ./scripts/esc.py %s %s ./mearc.config' % (succeeded_cases[ds], './datasets/output/%s.conditions.jml' % ds)
        print(cmd)
        run_cmd_directly(
            cmd
        )




if __name__ == "__main__":
    main()

