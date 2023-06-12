import re
import time
from typing import List, Dict

import openai
import json
import spacy

nlp = spacy.load("en_core_web_sm")

openai.api_key = "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"

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
    allinonequery1: str = None
    allinonequery2: str = None
    allinonequery3: str = None
    allinonequery3_1: str = None

    count = 0
    debug = False

    def __init__(self, features: List[str], classes: List[str], debug=True):
        self.features = features
        self.classes = classes
        self.allinonequery1 = """
            You break the sentence into separate sentences with consistent sentence structure.
            If and only if there are no conjunctions, you return the input sentence.
            If there are colons, you break the sentence by clarifying the sentence before the colons with the sentences after the colons.
            """
        self.allinonequery2 = """
                I am going to send you some sentences.
                Classify the sentences that are related to one of these topics: %s.
                 "without listhesis" is not related; 
                "spondylolysis" is not "spondylolisthesis", so it is not related;
                "no spinal pathology" is "normal". You don't have to add extra explanations. 
                Return "topic: sentence" in your reply.
                "sentence" are the complete sentences that you consider to match the above topics,
                "topic" are sentences in the format "The result is topic".
                All return messages must base on the sentences that I gave you.
            """ % ','.join(['"%s"' % c for c in classes])
        # self.allinonequery3 = """
        #     derive a complete sentence based on each sentence, without adding any new words, with a fixed format
        #     "parameter verb value", where the "parameter" is in one of these
        #     features: %s; the "value" can only be a numerical value or a range without relative comparison;
        #     a range is represented by expressions of "between number to number", "between number and number",
        #     "number ± number", etc; if the "value" is numerical, "verb" should be "is equal to", if the "value" is
        #     a range, "verb" should be "ranges", remove all words except the parameter names and the
        #     values, if no features are related to a certain sentence, you must remove this sentence.
        #     """ % ','.join(['"%s"' % f for f in features])
        # self.allinonequery3 = """
        #             derive a complete sentence based on each sentence, without adding any new words, with a fixed format
        #             "parameter verb value", where the "parameter" is in one of these
        #             features: %s; the "value" can only be a numerical value or a range without relative comparison
        #             or an inequality with a numerical value;
        #             a range is represented by expressions of "between number to number", "between number and number",
        #             "number ± number", etc;
        #             an inequality is represented by using a comparative such as "higher than number",
        #             "greater than number", etc;
        #             if the "value" is numerical, "verb" should be "is equal to", if the "value" is
        #             a range or an inequality, "verb" should be "ranges";
        #             remove all words except the parameter names and the values,
        #             if no features are related to a certain sentence, you must remove this sentence.
        #             """ % ','.join(['"%s"' % f for f in features])
        self.allinonequery3_1 = """
            I am going to send you some sentences.
            Summarise the sentence without adding new words;
            return "parameter verb value" in your reply; "parameter" is in one of these names: %s;
            a numerical value means integer or floating point number;
            a range means one or more numerical value with an operator, an example can be 'number ± number',
            another example of range can be 'between value to value';
            an inequality means using comparative expressions with number, an example can be 'greater than number'; 
            "value" is a numerical value or a range or an inequality;
            the "verb" is "is equal to" for "value" is a numerical value; the "verb" is "ranges" for "value" is a range;
            the "verb" is "is" for "value" is an inequality;
            all of the information in the your sentence must be derived based on the given sentence;  
            remove all information except the parameter, verb and value;
            if you cannot find a parameter or a value, you should return unrelated;
            your sentences must have a parameter and a value and a verb.
            """ % ','.join(['"%s"' % f for f in features])
        debug and print(self.allinonequery1)
        debug and print(self.allinonequery2)
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
        return False

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

        response = self.__call_gpt_chat(self.allinonequery2 + 'Sentences: ' + '\n'.join(broken_sents))

        filtered = []
        tmp = response.choices[0].message.content
        self.debug and print('gpt processed: ', tmp)
        for s in tmp.split('\n'):
            if s and len(s) > 2 and len(s.split(':')) == 2:
                s1 = s.split(':')[0]
                s2 = s.split(':')[1]
                if all(x not in s1 for x in self.classes):
                    continue
                if s1[-1] != '.':
                    s1 += '.'
                data = nlp(s2)
                sents = []
                for d in data.sents:
                    s = d.sent.text.strip()
                    if not self.__related__(s):
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
                            for a in f_abbr:
                                _s = _s.replace(a, f.replace('_', ' '))

                        if _s[-1] != '.':
                            _s += '.'
                        _s = text_cleansing(_s)
                        self.debug and print('After replacement: ', _s)
                        sents.append(_s)
                filtered.append([s1, sents])
            else:
                print('This sentence is invalid(%s)' % s)
        # TO BE CHECKED
        results = {}
        for i, data in enumerate(filtered):
            sum_sents = []
            for s in data[1]:
                response = self.__call_gpt_chat(self.allinonequery3_1 + 'Sentences: ' + s)
                sum_sents.append(response.choices[0].message.content)
            results[data[0]] = sum_sents
            # filtered[i].append(sum_sents)
        self.debug and print('result: ', results)
        # return broken_sents, filtered
        return results


def deterministic_check(sentence: str, features: List[str], classes: List[str], runs: int = 10):
    bucket = []
    runner = Gpt_preprocessing(features=features, classes=classes)

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
        else:
            print(bucket[0])


# _classes = ['spondylolisthesis', 'hernia', 'normal']
# _features = ['pelvic_tilt',
#             'pelvic_incidence',
#             'lumbar_lordosis_angle',
#             'sacral_slope', 'pelvic_radius', 'degree_spondylolisthesis']
#
# deterministic_check(
#     sentence="""
#     PI higher than 85° are seen in patients with isthmic spondylolisthesis as reported by Labelle et al.
#     """,
#     features=_features,
#     classes=_classes,
#     runs=1
# )
