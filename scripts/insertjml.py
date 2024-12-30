import sys
import os

def main(srcpath: str, app: str) -> None:
    _apppath = os.path.join(srcpath, app)
    _progpath = os.path.join(srcpath, "Solution.java.no_annotation")
    if not os.path.exists(_apppath) or not os.path.exists(_progpath):
        exit(-1)
    
    with open(_progpath, 'r') as fp:
        _program = fp.read()
        if _program:
            _program = _program.strip()
    
    import glob
    _files = glob.glob(os.path.join(_apppath, "jml", "*.jml"))
    if not _files:
        exit(-2)
    
    _tmp = []
    for file in _files:
        with open(file, 'r') as fp:
            data = fp.read()
        _tmp.append(r'//@ ' + data.replace('\n', '').replace('\ forall', r'\forall'))
    _tmp = '\n'.join(_tmp)

    import re
    _r = re.search(r'(\s+)?public.*\)(\s+)?[{]?', _program, re.ASCII)
    _program = _program[:_r.start()] + '\n' + _tmp + _program[_r.start():]

    _target = 'public class'
    if '@SuppressWarning' in _program:
        _target = '@SuppressWarning' 
    if 'import java.util.Arrays' not in _program:
        _program = _program.replace(_target, 'import java.util.Arrays;\n\n%s' % _target)
    if 'import java.util.Collections' not in _program:
        _program = _program.replace(_target, 'import java.util.Collections;\n\n%s' % _target)
    
    print(_program)



if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2])