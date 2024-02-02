import re

# processing the text related to parameter, including the symbols, types that explicitly mentioned in the sentence
# processing the text related to method return, including the return type, text refer to the result, etc.

alt_rules = [
    ('the method returns', 'the result is')
]

def _parameter_syntax_processor(sent: str) -> str:
    r = re.findall(r'`.*`', sent, re.ASCII)
    if r:        
        for p in r:
            pattern = 'PARAM_%s' % p.replace('`', '')
            sent = sent.replace(p, pattern)
    return sent


def contextprocessor(sent: str) -> str:
    okay = False
    sent = _parameter_syntax_processor(sent)
    return sent