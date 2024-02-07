import sys
import re
import os
import glob

faults = []
pre = []
post = []

def anl():
    global pre
    global post
    for d in glob.glob('./initial/*'):
        if not os.path.exists(os.path.join(d, 'post.p1')):
            continue
        with open(os.path.join(d, 'post.p1')) as fp:
            f = fp.read()
        if 'Preconditions:' in f and 'Postconditions:' in f:
            tmp = f.split('Postconditions:')
            # print('splited', tmp)    
            # pre = tmp[0].replace('Preconditions:', '').strip().split('\n')
            # pre = [re.sub(r"^[^a-zA-Z]\s", '', p, re.ASCII) for p in pre]
            # post = tmp[1].split('\n')                   
            # post = [re.sub(r"^[^a-zA-Z]\s", '', p, re.ASCII) for p in post]            
            _pre = tmp[0].replace('Preconditions:', '').strip().split('\n')
            _pre = [re.sub(r"^[^a-zA-Z]\s", '', p, re.ASCII) for p in _pre]
            _post = tmp[1].split('\n')                   
            _post = [re.sub(r"^[^a-zA-Z]\s", '', p, re.ASCII) for p in _post]            
            # with open(os.path.join(d, 'p1_post'), 'w') as fp:
            #     [fp.write(p + '\n') for p in post if p.strip()]
            # with open(os.path.join(d, 'p1_pre'), 'w') as fp:
            #     [fp.write(p + '\n') for p in pre if p.strip()]
            pre += _pre
            post += _post
        else:
            faults.append(d)

    # print(len(faults))
    with open('./format_skip', 'w') as fp:
        [fp.write(p + '\n') for p in faults]
        

def long_substr(data):
    substr = ''
    if len(data) > 1 and len(data[0]) > 0:
        for i in range(len(data[0])):
            for j in range(len(data[0])-i+1):
                if j > len(substr) and is_substr(data[0][i:i+j], data):
                    substr = data[0][i:i+j]
    return substr

def is_substr(find, data):
    if len(data) < 1 and len(find) < 1:
        return False
    for i in range(len(data)):
        if find not in data[i]:
            return False
    return True

anl()
print('for preconditions')
print(long_substr(pre))
print('for postconditions')
print(long_substr(post))
    

# store = {}

# with open('./success') as fp:
#     files = fp.read()
    
# for f in files.split('\n'):
#     if not os.path.exists(os.path.join('./initial', f, 'post.mr')):
#         continue
    
#     with open(os.path.join('./initial', f, 'post.mr')) as fp:
#         data = fp.read()
#     r = re.findall(r'_[0-9a-zA-Z]+\{[A-Z]+\}\([a-z,0-9]+\)', data)
#     for predicate in r:
#         m = re.search(r'\{[A-Z]+\}', predicate)
#         pos = m.group().replace('{', '').replace('}', '')
#         m = re.search(r'_[0-9a-zA-Z]+', predicate)
#         p = m.group().replace('_', '')
#         m = re.search(r'\(.*\)', predicate)     
#         ac = len(m.group().split(','))
#         # print(p, pos, len(m.group().split(',')))
#         key = '%s_%s_%s' % (p, pos, ac)
#         if key in store:
#             store[key] += 1
#         else:
#             store[key] = 1

# store = sorted(store.items(), key=lambda x:x[1], reverse=True)
# print(store)
