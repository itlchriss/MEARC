import sys
import os
from typing import List

def find_missing(full:List[int], sub:List[int]):
    return set(full).difference(set(sub))

def main(srcpath: str) -> None:
    _logpath = 'build/ss.log'
    _targetpath = os.path.join(srcpath, _logpath)

    if not os.path.exists(_targetpath):
        exit(-1)

    with open(_targetpath, 'r') as fp:
        _log = fp.read()
    
    with open(os.path.join(srcpath, 'rnl.txt'), 'r') as fp:
        _rawspec = fp.read()
    
    with open(os.path.join(srcpath, 'tmp/conditions.yml'), 'r') as fp:
        _conditions = fp.read()
    
    _lines_s = len(_rawspec.split('\n'))
    _tmp = _conditions.split('requires:')
    _tmp[0] = _tmp[0].replace('ensures:', '')
    _num_of_pre = len([i for i in _tmp[1].split('\n') if i.startswith('-')])
    _num_of_post = len([i for i in _tmp[0].split('\n') if i.startswith('-')])
    # _lines_c = len([i for i in _conditions.split('\n') if i.startswith('-')])

    print(_lines_s, _num_of_pre, _num_of_post)

    import glob
    _files = glob.glob(os.path.join(srcpath, "jml", "*.jml"))
    _num_of_jml = len(_files)
    
    _trans_pre = []
    _trans_post = []

    for _file in _files:
        _a = _file.split('/')[-1].split('.')
        if _a[0] == 'pre':
            _trans_pre.append(int(_a[1]))
        else:
            _trans_post.append(int(_a[1]))

    _trans_pre = sorted(_trans_pre)
    _trans_post = sorted(_trans_post)

    _missing_pre = find_missing(range(_num_of_pre), _trans_pre)
    _missing_post = find_missing(range(_num_of_post), _trans_post)
    if len(_log) == 0:
        print('ss_result:OK')
        if _missing_pre:
            print('missing preconditions: %s' % ','.join([str(i) for i in _missing_pre]))
        if _missing_post:
            print('missing postconditions: %s' % ','.join([str(i) for i in _missing_post]))
    else:
        print(_log)

if __name__ == "__main__":
    main(sys.argv[1])