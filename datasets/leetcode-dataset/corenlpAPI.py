import os
import json
#Set standford parser and models in your environment variables.
os.environ['STANFORD_PARSER'] = '/home/chrissleong/code/NLP/corenlp/stanford-corenlp-4.5.5'
os.environ['STANFORD_MODELS'] = '/home/chrissleong/code/NLP/corenlp/stanford-corenlp-4.5.5'
from pycorenlp import StanfordCoreNLP

# parser = StanfordParser()
nlp = StanfordCoreNLP('http://localhost:9000')

def depparse(text):
    parsed=""
    output = nlp.annotate(text, properties={
      'annotators': 'kbp',
      'outputFormat': 'json'
      })
    output = json.loads(output)        
    print(output)
    # for i in output["sentences"]:
    #     for j in i["basicDependencies"]:
    #         parsed=parsed+str(j["dep"]+'('+ j["governorGloss"]+' ')+str(j["dependentGloss"]+')'+' ')
    #     return parsed

def main(sentence):
    print(depparse(sentence))

if __name__=="__main__" :
    import sys   
    # Parse the example sentence
    sent = 'Mary is a girl.'
    main(sent)