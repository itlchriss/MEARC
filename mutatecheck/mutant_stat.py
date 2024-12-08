import glob
import re

stat = {}

for entry in glob.glob('./hart/*/mutants.log'):
    with open(entry, 'r') as fp:
        data = fp.read()
        if data:
            lines = data.split('\n')
            for i, line in enumerate(lines):
                if not line:
                    continue
                op = line.split(':')[1]
                # print(entry.split('/')[2], i, op)
                if op not in stat.keys():
                    stat[op] = 1
                else:
                    stat[op] += 1

print(stat)

for entry in glob.glob('./hart/*'):
    # print(entry)
    d = entry.replace('./hart', '')
    gm = glob.glob('./gpt35' + d + '/mutants/*')
    hm = glob.glob('./hart' + d + '/mutants/*')
    if len(hm) != len(gm):
        print(d, len(hm), len(gm))