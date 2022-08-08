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

asts = []
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


def get_asts_under_dir(directory: str):
    for filename in os.listdir(directory):
        f = os.path.join(directory, filename)
        if os.path.isfile(f) and filename.endswith('.java'):
            with open(f, 'r') as fp:
                file = fp.read()
                asts.append(javalang.parse.parse(file))
                # asts.append((filename, javalang.parse.parse(file)))
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

                tmp['parameter'][parameter['name']].append()
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
    for element in package.find_all(True, recursive=False):
        if element.name == 'comment':
            # interface and class comments are excluded in the current stage
            continue
        elif element.name == 'interface' or element.name == 'class':
            __process_section(element)
        else:
            print('Unknown javadoc element: %s' % element.name)
            exit(-2)


def main(source_code_dir: str, javadoc_xml_filepath: str):
    # TODO: the argument should be a directory
    # TODO: we need to parse all java files under the directory first
    # TODO: we need to save all the asts in a data structure
    #
    # Loop all classes, interfaces
    #   Loop all fields and methods to get their documentation
    #       Loop all words (using tokenizer) in the documentation
    #           For each word, loop all visible scope in the software to find exact name of software element
    get_asts_under_dir(source_code_dir)
    process_documentation(javadoc_xml_filepath)


if __name__ == "__main__":
    if sys.argv[1] and os.path.exists(sys.argv[1]) and \
            sys.argv[2] and os.path.exists(sys.argv[2]):
        main(sys.argv[1], sys.argv[2])
    else:
        print('use -h to view the helping messages')
