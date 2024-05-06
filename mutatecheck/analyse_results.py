import os
import glob
import re

# catastrophic error thrown by openjml
exclude_probs = ['./gpt35/s0043_multiply_strings',
   './gpt35/s0048_rotate_image',
   './gpt35/s0056_merge_intervals',
   './gpt35/s0059_spiral_matrix_ii',
   './gpt35/s0063_unique_paths_ii',
   './gpt35/s0064_minimum_path_sum',
   './gpt35/s0070_climbing_stairs',
   './gpt35/s0073_set_matrix_zeroes',
   './gpt35/s0087_scramble_string',
   './gpt35/s0125_valid_palindrome',
   './gpt35/s0130_surrounded_regions']

timeout_prob = ['./gpt35/s0071_simplify_path']

# 0: the mutant is killed, i.e. KILLED
# 1: the mutant is not killed, i.e. SURVIVED
# -1: not enough evidence to decide if the mutant is killed, i.e. UNCERTAIN
#       e.g. timeout
KILLED = 0
SURVIVED = 1
UNCERTAIN = -1
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
    # condition 2: if the number of verification failure caught in the mutated program is greater than that of the original program
    pattern = r'(\d+)\s+verification failure[s]?'
    o = re.search(pattern, oresult)
    m = re.search(pattern, mresult)
    if o and m:
        _o = int(o.group(1))
        _m = int(m.group(1))
        if _o >= _m:
            return SURVIVED
        else:
            return KILLED
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
        if folder in exclude_probs or folder in timeout_prob:
            continue
        s = folder.split('/')[-1]
        with open(os.path.join(folder, 'org_result.txt'), 'r') as fp:
            odata = fp.read().strip()
        mutantdata = {}
        # the number of cases that the mutation is performed on a line that cannot be proved by the theorem prover
        type1_count = 0
        for mutantfolder in glob.glob(os.path.join(folder, 'mutants/*')):
            with open(os.path.join(mutantfolder, 'line.txt'), 'r') as fp:
                mutated_line = fp.read().strip()
            # a condition to check if the mutated line is originally wrong
            if '%s: verify:' % mutated_line in odata:
                # uncomment this line to see the cases
                type1_count += 1
                # print('Originally wrong case found in %s with mutated line %s' % (folder, mutated_line))
                # skip the case
                continue
            with open(os.path.join(mutantfolder, 'result.txt'), 'r') as fp:
                mdata = fp.read().strip()
            mutantdata[mutantfolder.split('/')[-1]] = _killing_decision(odata, mdata, mutated_line)
        results[s] = mutantdata
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
print("There are %d cases without valid mutants" % len(no_valid_mutant))



    
