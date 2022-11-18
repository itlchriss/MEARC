
from typing import List, Optional, Union

import json
from io import StringIO
from lxml import etree

from depccg.tree import ScoredTree
from depccg.instance_models import SEMANTIC_TEMPLATES
from depccg.semantics.ccg2lambda import parse as ccg2lambda
from depccg.lang import get_global_language

from depccg.printer.html import to_mathml
from depccg.printer.prolog import to_prolog_en, to_prolog_ja
from depccg.printer.xml import xml_of
from depccg.printer.ja import ja_of
from depccg.printer.conll import conll_of
from depccg.printer.my_json import json_of
from depccg.printer.deriv import deriv_of
from depccg.printer.ptb import ptb_of
from depccg.printer.auto import auto_of, auto_extended_of


def _process_xml(xml_node):
    return etree \
        .tostring(xml_node, encoding='utf-8', pretty_print=True) \
        .decode('utf-8')


_formatters = {
    'conll': conll_of,
    'auto': auto_of,
    'auto_extended': auto_extended_of,
    'ja': ja_of,
    'deriv': deriv_of,
    'ptb': ptb_of,
}


def to_string(
    nbest_trees: List[Union[List[ScoredTree], ScoredTree]],
    format: str = 'auto',
    semantic_templates: Optional[str] = None,
) -> str:
    """convert parsing results into one string representation

    Args:
        nbest_trees (List[Union[List[ScoredTree], ScoredTree]]): 
            parsed results for multiple sentences
        format (str, optional): format type. Defaults to 'auto'.
        available options are: 'auto', 'auto_extended', 'conll', 'deriv', 'html', 'ja',
        'json', 'ptb', 'jigg_xml', 'jigg_xml_ccg2lambda', 'ccg2lambda', 'prolog'.
        semantic_templates (Optional[str], optional): semantic template used for
        obtaining semantic formula using ccg2lambda. Defaults to None.

    Raises:
        KeyError: if the format option is not supported, this error occurs.

    Returns:
        str: string in the target format
    """
    if isinstance(nbest_trees[0], ScoredTree):
        nbest_trees = [nbest_trees]
    elif not (
        isinstance(nbest_trees[0], list)
        and isinstance(nbest_trees[0][0], ScoredTree)
    ):
        raise RuntimeError('invalid argument type for stringifying trees')

    if format in ('jigg_xml_ccg2lambda', 'ccg2lambda'):
        lang = get_global_language()
        templates = semantic_templates or SEMANTIC_TEMPLATES.get(lang)
        assert templates is not None, \
            f'semantic_templates must be specified for language: {lang}'

    with StringIO() as file:
        jigg_xml = to_jigg_xml(nbest_trees)
        _, formulas_list = ccg2lambda.parse(
            jigg_xml, str(templates), ncores=1
        )
        for sentence_index, (trees, formulas) in enumerate(zip(nbest_trees, formulas_list), 1):
            for (tree, log_prob), formula in zip(trees, formulas):
                # print(header.format(sentence_index, log_prob), file=file)
                print(formula, file=file)
        return file.getvalue()

    try:
        formatter = _formatters[format]
    except KeyError:
        raise KeyError(
            f'unsupported format type: {format}'
        )

    with StringIO() as file:
        for sentence_index, trees in enumerate(nbest_trees, 1):
            for tree, log_prob in trees:
                print(formatter(tree), file=file)

        return file.getvalue()


def print_(
    nbest_trees: List[Union[List[ScoredTree], ScoredTree]],
    format: str = 'auto',
    semantic_templates: Optional[str] = None,
    **kwargs,
) -> None:
    """print parsing results into one string representation

    Args:
        nbest_trees (List[Union[List[ScoredTree], ScoredTree]]):
         parsed results for multiple sentences
        format (str, optional): format type. Defaults to 'auto'.
        available options are: 'auto', 'auto_extended', 'conll', 'deriv', 'html', 'ja',
        'json', 'ptb', 'jigg_xml', 'jigg_xml_ccg2lambda', 'ccg2lambda', 'prolog'.
        semantic_templates (Optional[str], optional): semantic template used for
        obtaining semantic formula using ccg2lambda. Defaults to None.

    other keyword arguments for Python 'print' function are also available.

    Raises:
        KeyError: if the format option is not supported, this error occurs.
    """

    print(
        to_string(
            nbest_trees,
            format=format,
            semantic_templates=semantic_templates,
        ),
        **kwargs,
    )


from typing import List
from lxml import etree

from depccg.tree import ScoredTree, Tree
from depccg.cat import Category, TernaryFeature, UnaryFeature


def _cat_multi_valued(cat: Category) -> str:
    def rec(x: Category):
        if x.is_atomic:
            if isinstance(x.feature, UnaryFeature):
                if x.feature.value is None:
                    return x.base
                else:
                    return f'{x.base}[{x.feature}=true]'
            elif isinstance(x.feature, TernaryFeature):
                return str(x)
            else:
                raise RuntimeError(
                    f'unsupported feature type: {type(x.feature)}')
        else:
            return f'({_cat_multi_valued(x)})'

    if cat.is_atomic:
        return rec(cat)
    return f'{rec(cat.left)}{cat.slash}{rec(cat.right)}'


class _ConvertToJiggXML(object):
    def __init__(self, sid: int, use_symbol: bool) -> None:
        self.sid = sid
        self._spid = -1
        self.processed = 0
        self.use_symbol = use_symbol

    @property
    def spid(self) -> int:
        self._spid += 1
        return self._spid

    def process(self, tree: Tree, score: float = None) -> None:
        counter = 0

        def traverse(node: Tree) -> None:
            nonlocal counter
            id = f's{self.sid}_sp{self.spid}'
            xml_node = etree.SubElement(res, 'span')
            xml_node.set('category', _cat_multi_valued(node.cat))
            xml_node.set('id', id)
            if node.is_leaf:
                start_of_span = counter
                counter += 1
                xml_node.set('terminal', f's{self.sid}_{start_of_span}')
            else:
                childid, start_of_span = traverse(node.left_child)
                if not node.is_unary:
                    tmp, _ = traverse(node.right_child)
                    childid += ' ' + tmp
                xml_node.set('child', childid)
                xml_node.set(
                    'rule', node.op_symbol if self.use_symbol else node.op_string
                )
            xml_node.set('begin', str(start_of_span))
            xml_node.set('end', str(start_of_span + len(node)))
            return id, start_of_span

        res = etree.Element('ccg')
        res.set('id', f's{self.sid}_ccg{self.processed}')
        id, _ = traverse(tree)
        res.set('root', str(id))
        res[0].set('root', 'true')
        if score is not None:
            res.set('score', str(score))
        self.processed += 1
        return res


def to_jigg_xml(
    trees: List[List[ScoredTree]],
    use_symbol: bool = False
) -> etree.Element:
    """generate etree.Element XML object in jigg format
    containing all the parse results

    Args:
        trees (List[List[ScoredTree]]): parsing result
        use_symbol (bool, optional): [description]. Defaults to False.

    Returns:
        etree.Element: jigg format etree.Element tree
    """

    root_node = etree.Element('root')
    document_node = etree.SubElement(root_node, 'document')
    sentences_node = etree.SubElement(document_node, 'sentences')

    for sentence_index, parsed in enumerate(trees):

        sentence_node = etree.SubElement(sentences_node, 'sentence')
        tokens_node = etree.SubElement(sentence_node, 'tokens')
        cats = [leaf.cat for leaf in parsed[0].tree.leaves]
        tokens = parsed[0].tree.tokens

        for token_index, (token, cat) in enumerate(zip(tokens, cats)):
            token_node = etree.SubElement(tokens_node, 'token')
            token_node.set('start', str(token_index))
            token_node.set('cat', str(cat))
            token_node.set('id', f's{sentence_index}_{token_index}')

            if 'word' in token:                
                token['surf'] = token.pop('word')
            if 'lemma' in token:
                if token['pos'].isalpha():
                    token['base'] = token.pop('lemma') + '{%s}' % token['pos']
                else:
                    token['base'] = token.pop('lemma')
            for k, v in token.items():
                token_node.set(k, v)

        converter = _ConvertToJiggXML(sentence_index, use_symbol)
        for tree, score in parsed:
            sentence_node.append(converter.process(tree, score))

    return root_node
