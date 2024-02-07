import os

root='./initial'

files = []
with open('./success', 'r') as fp:
    tmp = fp.read()
    files = tmp.split('\n')    
        

for f in files:
    p = os.path.join(root, f)
    