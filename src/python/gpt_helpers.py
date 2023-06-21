import re
import time
from typing import List, Dict

# orthopedics professional query
#  "without listhesis" is not related; 
#                 "spondylolysis" is not "spondylolisthesis", so it is not related;
#                 "no spinal pathology" is "normal". 

import openai
import json
import spacy

nlp = spacy.load("en_core_web_sm")

def text_cleansing(text: str):
    tmp = ''
    if '\n' in text:
        for s in text.split('\n'):
            if len(s) > 5:
                tmp += s
    else:
        tmp = text
    return tmp


class Gpt_preprocessing:
    features: List[str] = None
    abbr: Dict[str, List[str]] = None
    classes: List[str] = None
    gpt_model = "gpt-3.5-turbo"
    text_completion_model = "text-davinci-003"
    __professional_query: str = None
    allinonequery1: str = None
    __allinonequery2: str = None
    allinonequery3: str = None
    allinonequery3_1: str = None

    count = 0
    debug = False

    def __init__(
            self, 
            features: List[str], 
            classes: List[str], 
            professional_query: str = None, 
            openai_key: str = None, 
            relationships: Dict = None,
            debug=True):
        self.features = features
        self.classes = classes
        self.relationships = relationships
        openai.api_key = openai_key
        self.allinonequery1 = """
            You break the sentence into separate sentences with consistent sentence structure.
            If and only if there are no conjunctions, you return the input sentence.
            If there are colons, you break the sentence by clarifying the sentence before the colons with the sentences after the colons.
            """
        pq = professional_query
        if not professional_query:
            pq = ''
        self.__allinonequery2 = """
                I am going to send you some sentences.
                Classify the sentences that are related to one of these topics: %s.
                %s
                You don't have to add extra explanations.
                Return "topic: sentence" in your reply.
                "sentence" are the complete sentences that you consider to match the above topics,
                "topic" are sentences in the format "The result is topic".
                All return messages must base on the sentences that I gave you.
            """ % (','.join(['"%s"' % c for c in classes]), pq)
        self.allinonequery3_1 = """
            I am going to send you some sentences.
            Summarise the sentence without adding new words;
            return "parameter verb value" in your reply; "parameter" is in one of these names: %s;
            a numerical value means integer or floating point number;
            a range means one or more numerical value with an operator, an example can be 'number ± number',
            another example of range can be 'between value to value';
            an inequality means using comparative expressions with number, an example can be 'greater than number' and 'less than number'; 
            "value" is a numerical value or a range or an inequality;
            the "verb" is "is equal to" for "value" is a numerical value; the "verb" is "ranges" for "value" is a range;
            the "verb" is "is" for "value" is an inequality;
            all of the information in the your sentence must be derived based on the given sentence;  
            remove all information except the parameter, verb and value;
            if you cannot find a parameter or a value, you should return unrelated;
            your sentences must have a parameter and a value and a verb.
            """ % ','.join(['"%s"' % f for f in features])
        debug and print(self.allinonequery1)
        debug and print(self.__allinonequery2)
        # debug and print(self.allinonequery3)
        debug and print(self.allinonequery3_1)
        self.abbr = self.__get_feature_abbr()
        self.debug = debug

    def __get_feature_abbr(self) -> Dict[str, List[str]]:
        abbr = {}
        for f in self.features:
            if '_' in f:
                words = f.split('_')
                _abbr = (''.join([word[0] for word in words])).upper()
                if len(_abbr) > 2:
                    abbr[f] = []
                    for i in range(len(_abbr) - 1):
                        abbr[f].append(_abbr[i:])
                else:
                    abbr[f] = [_abbr]
        return abbr

    def __call_gpt_chat(self, query: str):
        response = openai.ChatCompletion.create(
            model=self.gpt_model,
            messages=[
                {"role": "user", "content": query}
            ],
            temperature=0
        )
        self.count = self.count + 1
        if (self.count + 1) % 3 == 0:
            print('Pausing for 60 seconds...')
            time.sleep(60)
        return response

    def __call_gpt_text_completion(self, prompt: str):
        response = openai.Completion.create(
            model=self.text_completion_model,
            prompt=prompt,
            max_tokens=150,
            temperature=0
        )
        return response

    # This is a heuristic function judging if the sentence is related in providing information about the features
    # It simply checks any feature is in the sentence syntactically
    def __related__(self, sent: str):                
        if sent and sent[-1] == '.':
            _sent = sent[:-1].lower()
        else:
            _sent = sent.lower()
        abbr = self.abbr
        for f in self.features:
            if f.replace('_', ' ') in _sent:
                return True
        for key in abbr:
            # if a in _sent:
            if any(x.lower() in _sent.split(' ') for x in abbr[key]):
                return True
        _rterms = []
        # we have defined a relationship list, which is a list of commonly recognised related terms, such as years old and age
        #  each element in this list is a list, representing all items in this list are related to each other
        #  if one of the terms in a list is a feature, we check if the sentence contains all other terms
        if self.relationships:
            for rterms in self.relationships:
                if any(x in self.features for x in rterms):
                    _rterms += rterms
        for r in _rterms:
            if r in _sent:
                return True
        return False
        # TODO: some sentences have indirectly related feature, such as years old and age, we have to consider the related terms
        #       currently setting the checking to all TRUE can process with the ACS sentences
        # return True

    def process(self, text: str) -> Dict[str, List[str]]:
        self.debug and print('input: %s' % text)
        response = self.__call_gpt_text_completion(self.allinonequery1 + 'Sentence: ' + text)

        tmp: str = response['choices'][0]['text']
        broken_sents = []
        doc = nlp(tmp)
        for s in doc.sents:
            if len(s.sent.text.strip()) < 2:
                continue
            else:
                broken_sents.append(s.sent.text.strip())
        self.debug and print('text to sentences: ', broken_sents)

        response = self.__call_gpt_chat(self.__allinonequery2 + 'Sentences: ' + '\n'.join(broken_sents))

        filtered = []
        tmp = response.choices[0].message.content
        self.debug and print('gpt processed: ', tmp)
        for s in tmp.split('\n'):
            if s and len(s) > 2 and len(s.split(':')) == 2:
                s1 = s.split(':')[0]
                s2 = s.split(':')[1]
                # checking if any features present in the sentence
                if all(x not in s1.lower() for x in self.classes):
                    self.debug and print('No features are present in the sentence')
                    continue
                if s1[-1] != '.':
                    s1 += '.'
                data = nlp(s2)
                sents = []
                for d in data.sents:
                    s = d.sent.text.strip()
                    if not self.__related__(s):
                        self.debug and print('This sentence is checked by symbolic, it is not related.')
                        continue
                    if len(s) > 2:
                        self.debug and print('Before replacement: ', s)
                        # this replacement is to replace the abbreviation with the full name reason why we do this is
                        # because we don't want to ask NLP to deal with the singular and plural problem
                        _s = s
                        # TODO: do the replacement of the abbr using the full form by myself
                        abbr = self.abbr
                        for i, f in enumerate(abbr):
                            f_abbr = abbr[f]
                            # for a in f_abbr:
                            #     _s = _s.replace(a, f.replace('_', ' '))
                            _tmp = _s.split(' ')
                            for a in f_abbr:
                                for w, i in enumerate(_tmp):
                                    if w == a:
                                        _tmp[i] = f.replace('_', ' ')
                            _s = ' '.join(_tmp)                                    

                        if _s[-1] != '.':
                            _s += '.'
                        _s = text_cleansing(_s)
                        self.debug and print('After replacement: ', _s)
                        sents.append(_s)
                filtered.append([s1, sents])
            else:
                self.debug and print('This sentence is invalid(%s)' % s)
        self.debug and print('filtered sentences: ', filtered)
        # TO BE CHECKED
        results = {}
        for i, data in enumerate(filtered):
            sum_sents = []
            for s in data[1]:
                # self.debug and print('Calling gpt: ' + self.allinonequery3_1 + 'Sentences: ' + s)
                response = self.__call_gpt_chat(self.allinonequery3_1 + 'Sentences: ' + s)
                # self.debug and print('response: ', response.choices[0].message.content)
                text = response.choices[0].message.content
                text = text.split('\n')
                # sum_sents.append(response.choices[0].message.content)
                sum_sents += text
            if data[0] not in results:                
                results[data[0]] = []
            results[data[0]].append(sum_sents)        
        self.debug and print('result: ', results)
        # return broken_sents, filtered
        return results
    
''' 
sentence: string, the sentence that requires GPT preprocessing
features: list of string, the features of the decision tree ensembles
classes: list of string, the classes of the decision tree ensembles. If the classes of the model is integers, labels mapping to these integers must be defined
key_path: string, path to the openai key
pq_path: string, path to the professional queries. These queries are provided by the professionals to train the GPT for more precise results
'''
def call_gpt_helper(sentence: str, features: List[str], classes: List[str], key_path: str, pq_path: str = None):
    # getting the openai key
    with open(key_path, 'r') as fp:
        key = fp.read().strip()
    with open(pq_path, 'r') as fp:
        pq = fp.read().strip()
    with open('./professional_queries/relationships.txt', 'r') as fp:
        text = fp.read()
    raw = text.split('\n')
    relationships = []
    for r in raw:
        p = r.split(',')
        relationships.append(p)
    runner = Gpt_preprocessing(features=features, classes=classes, professional_query=pq,
                openai_key=key, relationships=relationships, debug=False)
    r = runner.process(sentence)
    return r


def deterministic_check(sentence: str, features: List[str], classes: List[str], runs: int = 10):
    bucket = []    
    with open('../openai.key', 'r') as fp:
        key = fp.read().strip()
    #pq_type = 'orthopedics'
    pq_type = 'acs'
    with open('./professional_queries/%s.txt' % pq_type, 'r') as fp:
        pq = fp.read().strip()
    with open('./professional_queries/relationships.txt', 'r') as fp:
        text = fp.read()
    raw = text.split('\n')
    relationships = []
    for r in raw:
        p = r.split(',')
        relationships.append(p)
    runner = Gpt_preprocessing(features=features, classes=classes, professional_query=pq,
                openai_key=key, relationships=relationships, debug=False)

    for i in range(runs):
        r = runner.process(sentence)
        bucket.append(r)
        print('Finished %d round...' % (i + 1))

        if runs > 1:
            f = bucket[0]

            if all(x == f for x in bucket):
                print('Deterministic')
                print(bucket[0])
            else:
                print('Not all results are identical')
                print(bucket)
                exit(-1)
        # else:
        #     print(bucket[0])
    return bucket[0]


_classes = ['spondylolisthesis', 'hernia', 'normal']
_features = ['pelvic_tilt',
            'pelvic_incidence',
            'lumbar_lordosis_angle',
            'sacral_slope', 'pelvic_radius', 'degree_spondylolisthesis']

# TODO: we need to consider how to relate 0 and 1 with high and low risk
# _classes = ['high risk', 'low risk']
# TODO: [optional and minor] we need to think of how to break the feature names into words
# _features = [
#     'age',
#     'heart_Rate', 'systolic_Blood_Pressure', 'serum_Creatinine', 'cardiac_Arrest_At_Admission', 'deviated_ST_Segment',
#     'elevated_Cardiac_Enzymes', 'killip_Class'
# ]
# _features = [
#     'age',
#     'heartRate', 'systolicBloodPressure', 'serumCreatinine', 'cardiacArrestAtAdmission', 'deviatedSTSegment',
#     'elevatedCardiacEnzymes', 'killipClass'
# ]


#The low mortality would be
#consistent with the fact that high-risk patients
#(age N80, hemodynamically unstable, overt congestive
#cardiac failure, renal impairment which was sometimes
#deemed a contraindication to percutanous coronary
#intervention because of contrast nephropathy) were
#underrepresented in trials. This also explained the
#possible drawbacks of the risk scores based on trial
#cohorts.

# dataset = 'acs'
dataset = 'orthopedics'
queries = []
with open('./datasets/medical/%s/queries.txt' % dataset, 'r') as fp:
    text = fp.read()
queries = text.split('\n')
results = []
for q in queries:
    results.append(
        deterministic_check(
            sentence=q,
            features=_features,
            classes=_classes,
            runs=1            
        )
    )
[print(r) for r in results]
