import spacy
nlp = spacy.load("en_core_web_sm")

# object and subject constants
OBJECT_DEPS = {"dobj", "dative", "attr", "oprd"}
SUBJECT_DEPS = {"nsubj", "nsubjpass", "csubj", "agent", "expl"}
# tags that define wether the word is wh-
WH_WORDS = {"WP", "WP$", "WRB"}

# extract the subject, object and verb from the input
def extract_svo(doc):
    sub = []
    at = []
    ve = []
    aux = []
    for token in doc:
        # is this a verb?
        if token.pos_ == "VERB":
            ve.append(token.text)
        if token.pos_ == "AUX":
            aux.append(token.text)
        # is this the object?
        if token.dep_ in OBJECT_DEPS or token.head.dep_ in OBJECT_DEPS:
            at.append(token.text)
        # is this the subject?
        if token.dep_ in SUBJECT_DEPS or token.head.dep_ in SUBJECT_DEPS:
            sub.append(token.text)
    if not ve:
        ve = aux
    return ", ".join(sub).strip().lower(), ", ".join(ve).strip().lower(), ", ".join(at).strip().lower()

# wether the doc is a question, as well as the wh-word if any
def is_question(doc):
    # is the first token a verb?
    if len(doc) > 0 and doc[0].pos_ == "VERB":
        return True, ""
    # go over all words
    for token in doc:
        # is it a wh- word?
        if token.tag_ in WH_WORDS:
            return True, token.text.lower()
    return False, ""

# gather the user input and gather the info
# while True:    
# doc = nlp(input("> "))
# doc = nlp("If the value is positive, the result is true.")
import sys
with open(sys.argv[1]) as fp:
    f = fp.read()
f = f.replace('_', '')
## we have to merge the words within ** **
# f = f.replace('*', '')
f = f.split('. ')
print(f)
for s in f:
    doc = nlp(s.strip())
    # print out the pos and deps
    # for token in doc:
        # print("Token {} POS: {}, dep: {}".format(token.text, token.pos_, token.dep_))

    # get the input information
    subject, verb, attribute = extract_svo(doc)
    # question, wh_word = is_question(doc)
    print("svo:, subject: {}, verb: {}, attribute: {}".format(subject, verb, attribute))