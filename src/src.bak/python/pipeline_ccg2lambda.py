import os
import sys
import yaml
from typing import List

from dstbuilder import get_package_global_info, get_javadoc_info



def call_ccg2lambda_pipeline(sents: List[str]):
    pass


def main(source_code_dir: str, javadoc_xml_filepath: str = None) -> None:
    global_names = get_package_global_info(source_code_dir)
    if javadoc_xml_filepath:
        javadoc_data = get_javadoc_info(javadoc_xml_filepath)
    # TODO: we can fine tune the loop later.
    # [print(name) for name in global_names]
    dst = []
    for name in global_names:
        dst.append({
            'term': name,
            'syntax': ['NN'],
            'arity': 1,
            'arguments': ['x'],
            'interpretation': name
        })
    # with open(r'./dst.yml', 'w') as file:
    dynamic_symbol_file = yaml.dump(dst, sys.stdout, sort_keys=False)
    # for element_data in javadoc_data:
    #     # logger.critical('processing element %s' % element_data)
    #     method_data = javadoc_data[element_data]
    #     for method_name in method_data:
    #         # logger.critical('processing method %s' % method_name)
    #         comments = [method_data[method_name][ct] for ct in method_data[method_name] if ct == 't']
    #         # print(method_data)
    #         # logger.critical('%d comments available' % len(comments))
    #         # call_depccg_pipeline(comments)
    #         import re
    #         [print(re.sub(' +', ' ', comment.strip().replace('\n', ''))) for comment in comments]
    #         # REMOVE THIS AFTER DEBUGGING
    #         # break
    #     # REMOVE THIS AFTER DEBUGGING
    #     # break


if __name__ == "__main__":
    if len(sys.argv) == 2 and sys.argv[1] and os.path.exists(sys.argv[1]):
        main(sys.argv[1])
    elif sys.argv[1] and os.path.exists(sys.argv[1]) and \
            sys.argv[2] and os.path.exists(sys.argv[2]):
        main(sys.argv[1], sys.argv[2])
    else:
        print('use -h to view the helping messages')
