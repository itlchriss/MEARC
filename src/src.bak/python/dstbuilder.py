import os
import sys
import re
from typing import Dict, List, Union, Set

import javalang
import javalang.tree
from javalang.tree import ClassDeclaration, InterfaceDeclaration, TypeDeclaration, MethodDeclaration
from bs4 import BeautifulSoup, NavigableString
from functools import lru_cache

asts = {}
javadocs = {}


def get_levenshtein_distance(a, b):
    @lru_cache(None)  # for memorization
    def min_dist(s1, s2):

        if s1 == len(a) or s2 == len(b):
            return len(a) - s1 + len(b) - s2

        # no change required
        if a[s1] == b[s2]:
            return min_dist(s1 + 1, s2 + 1)

        return 1 + min(
            min_dist(s1, s2 + 1),  # insert character
            min_dist(s1 + 1, s2),  # delete character
            min_dist(s1 + 1, s2 + 1),  # replace character
        )

    return min_dist(0, 0)


#
#  Obtaining globally visible software elements
#   Including names of classes and their public methods, protected methods in inherited class,
#       names of interfaces and their methods
#
def get_package_global_info(directory: str) -> Set:
    global_element_names = []
    for filename in os.listdir(directory):
        f = os.path.join(directory, filename)
        if os.path.isfile(f) and filename.endswith('.java'):
            with open(f, 'r') as fp:
                file = fp.read()
                tree = javalang.parse.parse(file)
                for path, node in tree.filter(javalang.tree.MethodDeclaration):
                    global_element_names.append(node.name.lower())
                for path, node in tree.filter(javalang.tree.InterfaceDeclaration):
                    global_element_names.append(node.name.lower())
                for path, node in tree.filter(javalang.tree.ClassDeclaration):
                    global_element_names.append(node.name.lower())
                for path, node in tree.filter(javalang.tree.FieldDeclaration):
                    if node.declarators:
                        global_element_names.append(node.declarators[0].name.lower())
    return set(global_element_names)


java_types_with_dimensions = ['Collection', 'Set', 'Queue', 'Stack']
java_types_primitive = ['int', 'byte', 'short', 'long', 'float', 'double', 'boolean', 'char', 'Integer']

def get_type(typedefinition) -> int:
    if typedefinition:
        if typedefinition.dimensions:
            t = 1
        elif typedefinition.name in java_types_primitive:
            t = 0
        elif typedefinition.name == 'Collection' or typedefinition.name == 'List':
            t = 2
        else:
            t = 3
    else:
        t = -1
    return t

def get_package_global_info_from_javasrc(srcfile: str) -> Set:
    global_element_names = []
    if os.path.isfile(srcfile) and srcfile.endswith('.java'):
        with open(srcfile, 'r') as fp:
            file = fp.read()
            tree = javalang.parse.parse(file)
            for path, node in tree.filter(javalang.tree.MethodDeclaration):
                global_element_names.append(node.name.lower())
                global_element_names.append(("\\result", get_type(node.return_type)))
                if node.parameters:
                    for p in node.parameters:
                        t_name = get_type(p.type)
                        global_element_names.append((p.name, t_name))
            for path, node in tree.filter(javalang.tree.InterfaceDeclaration):
                global_element_names.append(node.name.lower())
            for path, node in tree.filter(javalang.tree.ClassDeclaration):
                global_element_names.append(node.name.lower())
            for path, node in tree.filter(javalang.tree.FieldDeclaration):
                print(node)
                if node.declarators:       
                    t_name = get_type(p.type)             
                    global_element_names.append((node.declarators[0].name.lower(), t_name))
                    global_element_names.append((node.declarators[0].name.capitalize(), t_name))
    return set(global_element_names)


def get_package_info(directory: str):
    print('| File name | Number of methods in the file |')
    print('|:---:|:---:|')
    for filename in os.listdir(directory):
        f = os.path.join(directory, filename)
        if os.path.isfile(f) and filename.endswith('.java'):
            with open(f, 'r') as fp:
                file = fp.read()
                tree = javalang.parse.parse(file)
                method_count = 0
                for path, node in tree.filter(javalang.tree.MethodDeclaration):
                    method_count += 1
                print('| %s | %s |' % (filename, method_count))


# TODO: this normalisation has banned the close curly bracket '}'
def __normalise(text: str) -> str:
    # soup = BeautifulSoup(text)
    # text = soup.getText()
    text = text.replace('{@link', '').replace('}', '').replace('{@code', '').replace('<code>', '').replace('</code>', '')
    if rs := re.findall(r'<\w+>', text):
        for r in set(rs):
            text = text.replace(r, r.replace('<', '').replace('>', '') + '_TYPE')
    # if text[-1] != '.':
    #     text = text + '.'
    return text


def __get_element_info(element: Union[BeautifulSoup, NavigableString]) -> Dict[str, Dict[str, str]]:
    data = {}
    for method in element.find_all('method', recursive=False):  # type: Union[BeautifulSoup, NavigableString]
        param_name = param_desc = None
        comments = {}
        for tag in method.find_all('tag', recursive=False):  # type: Union[BeautifulSoup, NavigableString]
            if tag['name'] == '@return':
                comments['r'] = 'The method returns ' + __normalise(tag['text']).strip()
            elif tag['name'] == '@throws':
                comments['t'] = 'The method throws ' + __normalise(tag['text']).strip()
            elif tag['name'] == '@param':
                ts = tag['text'].split(' ')
                param_name = ts[0].strip()
                param_desc = (' '.join(ts[1:])).strip()
                # for word in ts[1:]:
                #     if param_name.lower() in word.lower():
                #         print(param_name, word, param_desc)
                comments['p'] = 'The ' + param_name + '_PARAM is ' + __normalise(param_desc)
        data[method['name']] = comments
        # TODO: We can aim for an issue to check if return and throw comments mention the parameter
        #        If we can do this, we MAY be able to increase the rate of generation, but not exactly the accuracy
        # if param_name and param_desc and comments:
        #     for comment in comments:
        #         if param_desc in comment:
        #             print(param_name, param_desc, comment)
    return data


def get_javadoc_info(filepath: str) -> Dict[str, Dict[str, Dict[str, str]]]:
    with open(filepath, 'r') as file:
        fs = file.read()
    soup = BeautifulSoup(fs, 'xml')
    package = soup.find('package')
    if not package:
        print('Cannot find package in javadoc file: %s' % filepath)
        exit(-1)
    data = {}
    for element in package.find_all('class', recursive=False):  # type: Union[BeautifulSoup, NavigableString]
        data['class_' + element['name']] = __get_element_info(element)
    for element in package.find_all('interface', recursive=False):
        data['interface_' + element['name']] = __get_element_info(element)
    return data


def get_asts_under_dir(directory: str):
    for filename in os.listdir(directory):
        f = os.path.join(directory, filename)
        if os.path.isfile(f) and filename.endswith('.java'):
            with open(f, 'r') as fp:
                file = fp.read()
                tree = javalang.parse.parse(file)
                if tree.types:  # type: ClassDeclaration
                    extends_type = tree.types[0].extends
                    if extends_type:
                        if isinstance(extends_type, List):
                            [print(i.attrs['name']) for i in extends_type]
                            # extends_type.__class__ = javalang.tree.Type
                    asts[tree.types[0].name] = {
                        'fields': tree.types[0].fields,  # type: javalang.tree.FieldDeclaration
                        'methods': tree.types[0].methods,  # type: javalang.tree.MethodDeclaration
                        'constructors': tree.types[0].constructors,  # type: javalang.tree.ConstructorDeclaration
                        # 'inherit': [print(i[1]) for i in tree.types[0].extends]
                    }
                # asts.append(javalang.parse.parse(file))
                # # asts.append((filename, javalang.parse.parse(file)))
        elif os.path.isdir(f):
            get_asts_under_dir(f)
        else:
            pass


# Get all visible element names from the parsed asts
#  element_name: a name of a software element. It is either a class or interface
#  What is the definition of visible?
#  - the internal members and the elements which it has extended/implemented,
#       as well as their internal members
def get_visible_ele_names_from_asts(element_name: str) -> List[str]:
    # class/interface names should be in simple name, which has no package name in the string
    # field/method names should has the parent class/interface name
    names = []
    return names


def __process_section(tag: Union[BeautifulSoup, NavigableString]):
    # print('@name:%s %s %s' % (tag.name, tag['qualified'], tag['name']))
    element_name = tag['name']
    docs = {'param': [], 'inherit': [], 'method': {}}
    visible_element_names = get_visible_ele_names_from_asts(element_name)
    for _t in tag.find_all(recursive=False):  # type: Union[BeautifulSoup, NavigableString]
        if _t.name == 'tag' and _t['name'] in ['@param', '@return']:
            pass
        elif _t.name == 'interface' and 'name' not in _t:
            # interface extends interface (or) class implements interface
            docs['inherit'].append(_t['qualified'])
        elif _t.name == 'method':
            tmp = {'parameter': {}}
            for parameter in _t.find_all(name='parameter', recursive=False): \
                    # type: Union[BeautifulSoup, NavigableString]
                tmp['parameter'][parameter['name']] = []

                # tmp['parameter'][parameter['name']].append()
            # for _mt in _t.find_all(recursive=False):
            #     if _mt.name == 'tag' and _mt['name'] == '@param':
            #         text = _mt['text']
            #         param_name = text.split(' ')[0]
            #         param_type = _t.find(name='parameter', attrs={'name': param_name}).find(name='type')['qualified']
            #         tmp['param'].append([param_name, param_type, ' '.join(text.split(' ')[1:])])
            docs['method'][_t['name']] = tmp

    print(docs)


# What do we need from documentation?
#  - We want the field, method documentations
def process_documentation(filepath: str):
    with open(filepath, 'r') as file:
        fs = file.read()
    soup = BeautifulSoup(fs, 'xml')
    package = soup.find('package')
    if not package:
        print('Cannot find package in javadoc file: %s' % filepath)
        exit(-1)
    param_docs = []
    return_docs = []
    throw_docs = []
    types = []
    # TODO: to be fine-tuned. use this in the current stage
    code_tag_regex = r'(<code>[a-zA-Z0-9 ()]+</code>)'
    for element in package.find_all('class', recursive=False):
        types.append(element['name'])
    for element in package.find_all('interface', recursive=False):
        types.append(element['name'])
    for element in package.find_all('tag', recursive=True):
        text = element['text'].replace('\n', '').replace('  ', ' ').strip()
        tokens = [i.strip() for i in text.split(' ')]
        if element['name'] == '@param':
            param_docs.append(text)
        elif element['name'] == '@return':
            return_docs.append(text)
        elif element['name'] == '@throws':
            exception_type = tokens[0]
            desc = ' '.join(tokens[1:])
            if m := re.findall(code_tag_regex, desc, re.ASCII):
                print(m)
                print(exception_type, desc)
            # fix <code>term</code>
            #   if the term is a method name and the term is a noun, then ?
            #   if the term is a method signature and the term is a noun, then?
            #       - how does it be recognised as a noun?
            #   null is a value type
            # fix @link
            throw_docs.append((exception_type,))

    words = []
    # for doc in throw_docs:
    #     print(doc)

    # for element in package.find_all(True, recursive=False):
    #     if element.name == 'comment':
    #         # interface and class comments are excluded in the current stage
    #         continue
    #     elif element.name == 'interface' or element.name == 'class':
    #         __process_section(element)
    #     else:
    #         print('Unknown javadoc element: %s' % element.name)
    #         exit(-2)


def main(source_code_dir: str, javadoc_xml_filepath: str):
    # TODO: the argument should be a directory
    # TODO: we need to parse all java files under the directory first
    # TODO: we need to save all the asts in a data structure
    #
    # Loop all classes, interfaces
    #   Loop all fields and methods to get their documentation
    #       Loop all words (using tokenizer) in the documentation
    #           For each word, loop all visible scope in the software to find exact name of software element
    # get_asts_under_dir(source_code_dir)
    # process_documentation(javadoc_xml_filepath)
    global_names = get_package_global_info(source_code_dir)
    get_javadoc_info(javadoc_xml_filepath)



if __name__ == "__main__":
    if sys.argv[1] and os.path.exists(sys.argv[1]) and \
            sys.argv[2] and os.path.exists(sys.argv[2]):
        main(sys.argv[1], sys.argv[2])
    else:
        print('use -h to view the helping messages')
