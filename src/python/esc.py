import javalang, javalang.tree
from yaml import SafeLoader
import sys
from util import run_cmd_with_output
from typing import List, Tuple

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

std_modifiers = ['public', 'protected', 'private', 
                 'abstract', 'default', 'static', 'final', 
                 'transient', 'volatile', 'synchronized', 
                 'native', 'strictfp'
                 ]

def sort_modifiers(modifiers: list[str]) -> List[str]:
    indices = [std_modifiers.index(i) for i in modifiers]
    _m = [x for _,x in sorted(zip(indices, modifiers))]
    return _m


def get_program_structure(prog_file_path: str) -> List[str]:
    with open(prog_file_path, 'r') as fp:
        file = fp.read()
        tree = javalang.parse.parse(file)
    data = []
    for path, node in tree:
        if isinstance(node, javalang.tree.CompilationUnit):
            if node.package:
                data.append("package %s;" % node.package)
        if isinstance(node, javalang.tree.ClassDeclaration):
            m = ''
            if node.modifiers:
                m = ' '.join(sort_modifiers(list(node.modifiers))) + ' '
            data.append("%sclass %s {\n" % (m, node.name))
            for fpath, field in node.filter(javalang.tree.FieldDeclaration):
                m = ''
                if field.modifiers:
                    m = ' '.join(sort_modifiers(list(field.modifiers))) + ' '
                if hasattr(field.declarators[0], 'initializer'):
                    data.append("%s%s %s = %s;\n" % 
                                (m, field.type.name, field.declarators[0].name, field.declarators[0].initializer.value))
                else:    
                    data.append("%s%s %s;\n" % (m, field.type.name, field.declarators[0].name))
            for mpath, method in node.filter(javalang.tree.MethodDeclaration):
                m = ''
                if method.modifiers:
                    m = ' '.join(sort_modifiers(list(method.modifiers))) + ' '
                args = []
                if method.parameters:
                    # TODO: change the script to form a yml file with method correspondence
                    for p in method.parameters:  # type: javalang.tree.FormalParameter
                        args.append("%s %s" % (type_to_str(p.type), p.name))
                data.append("*****JML*****")
                data.append("%s%s %s(%s);\n" % (m, type_to_str(method.return_type), method.name, ','.join(args)))
            data.append("}\n")
    return data


def write_JML_files(
    jmls: List[Tuple[str | None, str | None]], 
    prog_file_path: str,
    target_path: str, 
    prog_name: str):
    structure = get_program_structure(prog_file_path=prog_file_path)
    for i, jml in enumerate(jmls):        
        data = ''
        for item in jml:
            if item[0] != 'ALSO':
                data += "//@ %s\n" % item[0]
                data += "//@ %s\n" % item[1]
            else:
                data += "//@ %s\n" % item[1]
        if data:            
            tmp = list(map(lambda x: x.replace('*****JML*****', data), structure))
            with open('%s/%s.query.%d.jml' % (target_path, prog_name, i), 'w+') as fp:  
                fp.writelines(tmp)
            


def esc(java_file_path: str, raw_jml_path: str, config_path: str):
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
