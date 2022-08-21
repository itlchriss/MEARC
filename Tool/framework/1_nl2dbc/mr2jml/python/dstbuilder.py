import enum
import os
import sys
import re
from enum import Enum
from typing import Dict, List, Union

import javalang
import javalang.tree
from javalang.tree import ClassDeclaration, InterfaceDeclaration, TypeDeclaration
from bs4 import BeautifulSoup, NavigableString

asts = {}
javadocs = {}

# class ElementType(Enum):
#     CLASS = enum.auto()
#     INTERFACE = enum.auto()

#
# class Content:
#     # The name of the software element
#     __name: str
#     # The type of the software element. It is either ElementType.CLASS or ElementType.INTERFACE
#     __type: ElementType
#     # Includes the name of elements in the following scenario:
#     #   1. The class that a class has extended
#     #   2. The interface that a class has implemented
#     #   3. The interface that an interface has extended
#     # __inherit_elements: List[str]
#     # __fields: List[str]
#     # def __init__(self):


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


def __get_element_info(element: Union [BeautifulSoup, NavigableString]):
    print('#### %s' % element['name'])
    print('| Method signature | parameter doc count | return doc count | exception doc count |')
    print('|:---:|:---:|:---:|:---:|')
    for method in element.find_all('method', recursive=False):  # type: Union[BeautifulSoup, NavigableString]
        # print(method['name'])
        param_doc_count = return_doc_count = throw_doc_count = 0
        for tag in method.find_all('tag', recursive=False):  # type: Union[BeautifulSoup, NavigableString]
            if tag['name'] == '@param':
                param_doc_count += 1
            if tag['name'] == '@return':
                return_doc_count += 1
            if tag['name'] == '@throw':
                throw_doc_count += 1
        print('|%s|%s|%s|%s|' % (method['name'], param_doc_count, return_doc_count, throw_doc_count))


def get_javadoc_info(filepath: str):
    with open(filepath, 'r') as file:
        fs = file.read()
    soup = BeautifulSoup(fs, 'xml')
    package = soup.find('package')
    if not package:
        print('Cannot find package in javadoc file: %s' % filepath)
        exit(-1)
    for element in package.find_all('class', recursive=False): # type: Union[BeautifulSoup, NavigableString]
        __get_element_info(element)
    for element in package.find_all('interface', recursive=False):
        __get_element_info(element)


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
                        'fields': tree.types[0].fields,    # type: javalang.tree.FieldDeclaration
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
            throw_docs.append((exception_type, ))

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
    get_package_info(source_code_dir)
    get_javadoc_info(javadoc_xml_filepath)


if __name__ == "__main__":
    if sys.argv[1] and os.path.exists(sys.argv[1]) and \
            sys.argv[2] and os.path.exists(sys.argv[2]):
        main(sys.argv[1], sys.argv[2])
    else:
        print('use -h to view the helping messages')
