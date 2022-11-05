import javalang, javalang.tree
from yaml import SafeLoader
import sys
from util import run_cmd_with_output

import yaml


# parameters=[
#     FormalParameter(
#         annotations=[],
#         modifiers=set(),
#         name=original,
#         type=ReferenceType(arguments=None, dimensions=[None], name=Integer, sub_type=None), varargs=False)]

def type_to_str(_type: javalang.tree.BasicType) -> str:
    if not _type:
        return 'void'
    elif isinstance(_type, javalang.tree.ReferenceType):
        r = _type.name
        if _type.arguments:
            args = []
            for arg in _type.arguments:
                args.append(arg.type.name)
            r += '<%s>' % ','.join(args)
        else:
            for i in _type.dimensions:
                r += '[]'
    else:
        r = _type.name
        for i in _type.dimensions:
            r += '[]'
    return r


def main(java_file_path: str, raw_jml_path: str, config_path: str):
    prog_name = java_file_path.split('/')[-1].replace('.java', '')
    with open(java_file_path, 'r') as fp:
        file = fp.read()
        tree = javalang.parse.parse(file)
    with open(raw_jml_path, 'r') as fp:
        raw_jmls = fp.read()

    data = []
    for path, node in tree:
        if isinstance(node, javalang.tree.CompilationUnit):
            if node.package:
                data.append("package %s;" % node.package)
        if isinstance(node, javalang.tree.ClassDeclaration):
            m = ''
            if node.modifiers:
                m = ' '.join(node.modifiers) + ' '
            data.append("%sclass %s {" % (m, node.name))
            for fpath, field in node.filter(javalang.tree.FieldDeclaration):
                m = ''
                if field.modifiers:
                    m = ' '.join(field.modifiers) + ' '
                data.append("%s%s %s;" % (m, field.type, field.name))
            for mpath, method in node.filter(javalang.tree.MethodDeclaration):
                m = ''
                if method.modifiers:
                    m = ' '.join(method.modifiers) + ' '
                args = []
                if method.parameters:
                    # TODO: change the script to form a yml file with method correspondence
                    for p in method.parameters:  # type: javalang.tree.FormalParameter
                        args.append("%s %s" % (type_to_str(p.type), p.name))
                for jml in raw_jmls.split('\n'):
                    data.append('//@ %s' % jml)
                data.append("%s%s %s(%s);" % (m, type_to_str(method.return_type), method.name, ','.join(args)))
            data.append("}")
    with open(config_path, 'r') as fp:
        configs = yaml.load(fp, Loader=SafeLoader)
    tmp_path = configs['TMP']
    openjml = configs['OPENJML']
    target_jml_path = '%s/%s.jml' % (tmp_path, prog_name)
    print('JML specification file is formatted in %s' % target_jml_path)
    with open(target_jml_path, 'w') as fp:
        for s in data:
            fp.write(s + '\n')
    cmd = "%s/openjml -esc %s -specs-path %s --check-specs-path --prover=z3_4_7" % (openjml, java_file_path, tmp_path)
    print('Verifying %s with command: %s' % (prog_name, cmd))
    output, error = run_cmd_with_output(cmd)
    if not output and not error:
        print('Verification accepted.')
    else:
        print('Verification rejected.')
        if output:
            print(output)
        if error:
            print(error)


if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2], sys.argv[3])
