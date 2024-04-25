from typing import Dict, List

def createmutant(line: int, org: str, new: str, prog: List[str]) -> str:
    tmp = prog
    if new != "<NO-OP>":
        tmp[line - 1] = tmp[line - 1].replace(org, new)
    else:
        tmp[line - 1] = ""
    return '\n'.join(tmp)


import sys
import os
folder = sys.argv[1]
with open(os.path.join(folder, "mutants.log"), 'r') as fp:
    mutantslog = fp.read()
with open(os.path.join(folder, "Solution.java"), 'r') as fp:
    program = fp.read()
program = program.split('\n')
mutantslog = mutantslog.split('\n')
mutants = []
import re
for log in mutantslog:
    if r := re.match(r'(\d+):[A-Z]+:.*\(.*,.*\):(\d+):(.*)\s+\|==>\s+(.*)', log, re.ASCII):
        mutants.append((r.group(2), createmutant(int(r.group(2)), r.group(3), r.group(4), program.copy())))

for i, t in enumerate(mutants):
    p = t[1]
    line = t[0]
    os.makedirs(os.path.join(folder, "mutants", str(i)), exist_ok=True)
    with open(os.path.join(folder, "mutants", str(i), "Solution.java"), 'w') as fp:
        fp.write(p)
    with open(os.path.join(folder, "mutants", str(i), "line.txt"), 'w') as fp:
        fp.write(line)