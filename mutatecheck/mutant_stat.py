import glob
import re

stat = {}

for entry in glob.glob('./hart/*/mutants.log'):
    print(entry)
    with open(entry, 'r') as fp:
        data = fp.read()
        if data:
            lines = data.split('\n')
            for line in lines:
                if not line:
                    continue
                op = line.split(':')[1]
                if op not in stat.keys():
                    stat[op] = 1
                else:
                    stat[op] += 1

print(stat)