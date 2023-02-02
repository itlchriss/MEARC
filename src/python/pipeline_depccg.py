import os
import sys
import logging
import yaml
from typing import List


import depccg.parsing
from depccg.cat import Category
from printer import print_
from depccg.instance_models import load_model
# from depccg.lang import set_logging_level as set_lang_logging_level
from depccg.annotator import (
    english_annotator, annotate_XX
)
from depccg.allennlp.utils import read_params
# from depccg.chainer.supertagger import set_logging_level as set_chainer_logging_level
# from depccg.semantics.ccg2lambda.parse import set_logging_level as set_ccg2lamda_logging_level
# from dstbuilder import get_package_global_info, get_javadoc_info

logger = logging.getLogger(__name__)

# Taking the default values from argparse.py
unary_penalty = 0.1
nbest = 1
pruning_size = 50
beta = 0.00001
use_beta = True
max_length = 250
max_step = 10000000
num_processes = 4


# --format ccg2lambda -a spacy -I raw --silent --tokenize
def call_depccg_pipeline(text: str):
    # args = argparse("--format ccg2lambda -a spacy -I raw --silent --tokenize")
    sents = [text]
    logging_level = logging.ERROR
    logging.basicConfig(
        format='%(asctime)s - %(levelname)s - %(name)s - %(message)s',
        level=logging_level
    )
    logging.getLogger('filelock').setLevel(logging.ERROR)
    logging.getLogger('allennlp').setLevel(logging.ERROR)
    logger.setLevel(logging_level)
    # set_lang_logging_level(logging_level)
    # set_chainer_logging_level(logging_level)
    # set_annotator_logging_level(logging_level)
    # set_ccg2lamda_logging_level(logging_level)
    annotator_fun = english_annotator.get('spacy', annotate_XX)
    supertagger, config = load_model(None, -1)

    (
        apply_binary_rules,
        apply_unary_rules,
        category_dict,
        _
    ) = read_params(config.config, False, False)
    root_categories = [
        Category.parse(category)
        for category in 'S[dcl]|S[wq]|S[q]|S[qem]|NP'.split('|')
    ]
    semantic_templates = config.semantic_templates
    kwargs = dict(
        unary_penalty=unary_penalty,
        nbest=nbest,
        pruning_size=pruning_size,
        beta=beta,
        use_beta=use_beta,
        max_length=max_length,
        max_step=max_step,
        processes=num_processes    
    )

    # if args.input is not None:
    #     input_type = open(args.input)
    # elif not sys.stdin.isatty():
    #     input_type = sys.stdin
    # else:
    #     # reading from keyboard
    #     input_type = None
    #     sys.stdout.flush()
    #     sys.stderr.flush()
    #     logging.getLogger().setLevel(logging.CRITICAL)

    categories = None
    fin = [
        line for line in map(str.strip, sents)
        if len(line) > 0
    ]
    if len(fin) == 0:
        return None

    doc = annotator_fun(
        [
            [word for word in sentence.split(' ')]
            for sentence in fin
            if len(sentence) > 0
        ],
        tokenize=True,
    )

    # logger.info("supertagging")
    score_result, categories_ = supertagger.predict_doc(
        [[token.word for token in sentence] for sentence in doc]
    )
    if categories is None:
        categories = [
            Category.parse(category) for category in categories_
        ]

    if category_dict is not None:
        doc, score_result = depccg.parsing.apply_category_filters(
            doc,
            score_result,
            categories,
            category_dict,
        )
    
    # for i in range(len(doc)):
    #     for j in range(len(doc[i])):
    #         print(doc[i][j]['word'])
    #         doc[i][j]['word'] += '{' + doc[i][j]['pos'] + '}'
    #         doc[i][j]['lemma'] += '{' + doc[i][j]['pos'] + '}'

    # logger.info("parsing")
    results = depccg.parsing.run(
        doc,
        score_result,
        categories,
        root_categories,
        apply_binary_rules,
        apply_unary_rules,
        **kwargs,
    )

    return print_(
        results,
        format='ccg2lambda',
        semantic_templates=semantic_templates
    )
    # sys.stdout.flush()


def main(source_code_dir: str, javadoc_xml_filepath: str) -> None:
    # global_names = get_package_global_info(source_code_dir)
    # javadoc_data = get_javadoc_info(javadoc_xml_filepath)
    pass
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
    if len(sys.argv) == 2:
        print(call_depccg_pipeline(sys.argv[1]))
    # if sys.argv[1] and os.path.exists(sys.argv[1]) and \
    #         sys.argv[2] and os.path.exists(sys.argv[2]):
    #     main(sys.argv[1], sys.argv[2])
    # else:
    #     print('use -h to view the helping messages')
