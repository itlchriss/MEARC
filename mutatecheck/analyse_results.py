import os
import glob
import re

# 0: the mutant is killed, i.e. KILLED
# 1: the mutant is not killed, i.e. SURVIVED
# -1: not enough evidence to decide if the mutant is killed, i.e. UNCERTAIN
#       e.g. timeout
KILLED = 0
SURVIVED = 1
UNCERTAIN = -1
FALSE_NEGATIVE = -2
IE_Terms = ['timeout']
def _killing_decision(oresult: str, mresult: str, mline: str) -> int:
    # UNCERTAIN conditions
    # condition 1: If any of the terms that provides the validity of result is unknown, the result is uncertain
    if any(t in mresult for t in IE_Terms):
        return UNCERTAIN
    ################################################
    # KILLED conditions:
    # condition 1: if the mutated line is precisely captured
    if '%s verify:' % mline in mresult:
        return KILLED
    # added condition: if the lines associated are different, it means that at least a new defect is found.
    pattern = r'Solution\.java:(\d+):\s+verify'
    o = re.findall(pattern, oresult)
    m = re.findall(pattern, mresult)
    if oresult and mresult:
        o_lines = set(o)
        m_lines = set(m)
        if o_lines != m_lines:
            return KILLED
    elif not oresult and mresult:
        return KILLED
    elif not mresult and not oresult:
        return SURVIVED
    # condition 2: if the number of verification failure caught in the mutated program is greater than that of the original program
    pattern = r'(\d+)\s+verification failure[s]?'
    o = re.search(pattern, oresult)
    m = re.search(pattern, mresult)
    if o and m:
        _o = int(o.group(1))
        _m = int(m.group(1))
        if _o >= _m:
            # we have to compare the lines here
            pattern = r'Solution\.java:(\d+):\s+verify'
            ol = re.findall(pattern, oresult)
            ml = re.findall(pattern, mresult)
            if (_o >= _m and sorted(ml) != sorted(ol)) or sorted(ml) != sorted(ol):
                return KILLED
            return SURVIVED
        elif _o < _m:
            return KILLED
        else:
            return SURVIVED
    elif not o and m:
        return KILLED
    elif not m and o:
        return SURVIVED
    else:
        return UNCERTAIN
    ################################################

def _get_data(t: str):
    results = {}
    for folder in glob.glob('./%s/*' % t):
        s = folder.split('/')[-1]
        # x = False
        # if s == 's0392_is_subsequence':
        #     print(t, s)
        #     x = True
        with open(os.path.join(folder, 'org_result.txt'), 'r') as fp:
            odata = fp.read().strip()
        mutantdata = {}
        # the number of cases that the mutation is performed on a line that cannot be proved by the theorem prover
        type1_count = 0
        for mutantfolder in glob.glob(os.path.join(folder, 'mutants/*')):
            # x and print(mutantfolder)
            with open(os.path.join(mutantfolder, 'line.txt'), 'r') as fp:
                mutated_line = fp.read().strip()
            # a condition to check if the mutated line is originally wrong
            # if '%s: verify:' % mutated_line in odata:
            #     # uncomment this line to see the cases
            #     type1_count += 1
            #     # print('Originally wrong case found in %s with mutated line %s' % (folder, mutated_line))
            #     # skip the case
            #     # continue
            #     mutantdata[mutantfolder.split('/')[-1]] = UNCERTAIN
            # else:
            with open(os.path.join(mutantfolder, 'result.txt'), 'r') as fp:
                mdata = fp.read().strip()
            mutantdata[mutantfolder.split('/')[-1]] = _killing_decision(odata, mdata, mutated_line)
        results[s] = mutantdata
        # print(s, len(mutantdata.keys()))
        # uncomment this to check the number of exluded mutants in type 1
        # print('Type 1 excluded %d out of %d' % (type1_count, len(glob.glob(os.path.join(folder, 'mutants/*')))))
    return results

gpt_results = _get_data('gpt35')
hart_results = _get_data('hart')

# print('GPT specs killed')
# for s in gpt_results.keys():
#     print(s, len(gpt_results[s].keys()), len([v for v in gpt_results[s].values() if v == KILLED]))

# print('HART specs killed')
# for s in gpt_results.keys():
#     print(s, len(hart_results[s].keys()), len([v for v in hart_results[s].values() if v == KILLED]))

gpt_better_cases = []
hart_better_cases = []
no_kill_cases = []
ident_cases = []
no_valid_mutant = []

with open('./opdata') as fp:
    opdata = fp.read()

with open('./optypes') as fp:
    r = fp.read()
    types = r.strip().split(',')
    optypes = {}
    for t in types:
        optypes[t] = 0

lines = opdata.strip().split('\n')
opdata = {}
for line in lines:
    data = line.split(' ')
    if data[0] not in opdata.keys():
        opdata[data[0]] = []
    opdata[data[0]].append(data[2])

opstat = {'hart': optypes.copy(), 'llm': optypes.copy()}

# consider a vienne diagram
#   A: set of killed mutants from GPT
#   B: set of killed mutants from HART
#   If A% > B% of (A U B), then A provides more effective spec in a problem
#   vice versa
for s in gpt_results.keys():
    print('problem: %s' % s)  
    gl = len(gpt_results[s].keys())
    hl = len(hart_results[s].keys())
    gk = len([v for v in gpt_results[s].values() if v == KILLED])
    hk = len([v for v in hart_results[s].values() if v == KILLED])

    gv = [v for v in gpt_results[s].values() if v == KILLED]
    hv = [v for v in hart_results[s].values() if v == KILLED]
    if s in opdata:
        ops = opdata[s]
        for v in gv:
            opstat['llm'][ops[int(v)]] += 1
        for v in hv:
            opstat['hart'][ops[int(v)]] += 1 

    union_mutants = list(set(list(gpt_results[s].keys()) + list(hart_results[s].keys())))
    if len(union_mutants) == 0:
        # the case that no mutants are valid
        no_valid_mutant.append(s)
        continue
    gpt_killed_mutants = [k for k in gpt_results[s].keys() if gpt_results[s][k] == KILLED]
    hart_killed_mutants = [k for k in hart_results[s].keys() if hart_results[s][k] == KILLED]
    killed_union = set(gpt_killed_mutants + hart_killed_mutants)
    killed_union_percentage = len(killed_union)/len(union_mutants)
    gpt_killed_percentage = len(gpt_killed_mutants) / len(union_mutants)
    hart_killed_percentage = len(hart_killed_mutants) / len(union_mutants)
    print("union killed %%: %f%%, GPT killed %%: %f%%, HART killed %%: %f%%" % (killed_union_percentage * 100, gpt_killed_percentage * 100, hart_killed_percentage * 100))
    # find mutual kills
    
    if len(killed_union) == 0:
        # no mutants are killed, no comparison can be made
        no_kill_cases.append(s)
        continue
    gpt_contribute = len(set(gpt_killed_mutants).intersection(killed_union)) / len(killed_union)
    hart_contribute = len(set(hart_killed_mutants).intersection(killed_union)) / len(killed_union)
    print("GPT contributed kill %%: %f%%, HART contributed kill %%: %f%%" % (gpt_contribute * 100, hart_contribute * 100))
    # comparison
    # condition 1: if A has more kill than B, then A dominates
    if len(gpt_killed_mutants) > len(hart_killed_mutants):
        gpt_better_cases.append(s)
    elif len(gpt_killed_mutants) < len(hart_killed_mutants):
        hart_better_cases.append(s)
    else:
        # condition 2: if A and B kill the same number, then we check the contribution, if A contributes more than B, then A dominates
        if gpt_contribute > hart_contribute:
            gpt_better_cases.append(s)
        elif gpt_contribute < hart_contribute:
            hart_better_cases.append(s)
        else:
            # if even the contribution is the same, then there are no differences
            if gpt_contribute == 1 and hart_contribute == 1:
                ident_cases.append(s)
            pass

print("Comparison: ")
print("Total cases: %d" % len(gpt_results.keys()))
print("GPT is better in %d cases and HART is better in %d cases" % (len(gpt_better_cases), len(hart_better_cases)))

print("Identical contributions in killing mutants: %d" % len(ident_cases))
print("There are %d cases without mutants killed" % len(no_kill_cases))
print(no_kill_cases)
print("There are %d cases without valid mutants" % len(no_valid_mutant), no_valid_mutant)

missing_case = [i for i in gpt_results.keys() if i not in gpt_better_cases and i not in hart_better_cases and i not in ident_cases and i not in no_kill_cases and i not in no_valid_mutant]

if missing_case:
    print("The following case is missing in the above results: ", missing_case)

print(gpt_results['s0455_assign_cookies'])
print(hart_results['s0455_assign_cookies'])

print('Number of mutants produced by these operators')
print('HART')
print(opstat['hart'])
print('GPT')
print(opstat['llm'])

import matplotlib.pyplot as plt
import numpy as np

#### start of boxplot 

programs = [str(i) for i in gpt_results.keys()]
total = {}
for i in programs:
    total[i] = len(glob.glob('./hart/%s/mutants/*' % i))
g = []
p = []
u = []
d = []
import math
for i in programs:
    hk = [k for k in hart_results[i] if hart_results[i][k] == KILLED]
    gk = [k for k in gpt_results[i] if gpt_results[i][k] == KILLED]
    inters = set(hk).intersection(gk)
    union = list(set(gk + hk))
    nh = len(hk)
    ng = len(gk)
    t = total[i]
    if t == 0:
        continue
    g.append((ng/t))
    p.append((nh/t) )
    u.append((len(union)/t) )
    d.append(len(inters)/t)

data = [np.array(g), np.array(p), np.array(u), np.array(d)]

avg_h = sum(p)/len(p)
avg_g = sum(g)/len(g)
avg_u = sum(u)/len(u)
print("HART: ", avg_h, ", LLM: ",  avg_g,". Union: ",  avg_u)




fig, ax = plt.subplots()
bp = ax.boxplot(data, labels=['LLM', 'Hybrid', 'Union', 'Intersection'])  
plt.title("Mutation score of the contracts generated by the two approaches")  
plt.show()  

#### end of boxplot


programs = [str(i) for i in gpt_results.keys()]
width = 0.5
g = []
p = []
for i in programs:
    hk = [k for k in hart_results[i] if hart_results[i][k] == KILLED]
    gk = [k for k in gpt_results[i] if gpt_results[i][k] == KILLED]
    nh = len(hk)
    ng = len(gk)
    g.append(ng)
    p.append(nh)

labels = [i.split('_')[0] for i in programs]
plt.xlabel('Name of the programs')
plt.ylabel('Number of mutants killed')
plt.title("Number of mutants killed by the two approaches")

# 0020 - 0044
start = 0
end = int(len(labels)/2)
# start = 42
# end = 84

labels = labels[start:end]
xaxis = np.arange(42)
plt.xticks(xaxis, labels, rotation=90)
plt.bar(xaxis - 0.2, g[start:end], 0.4, color='r', label='pure LLM approach')
plt.bar(xaxis + 0.2, p[start:end], 0.4, color='black', label='HAFIS')
plt.legend()
plt.show()

