import sys
import openai
import time
import re
import random
import os
from text_generation import Client

NLP = '/Users/chrissleong/Documents/Phd_Studies/NLP/ccg2lambda'
PYCMD = '/Users/chrissleong/Documents/Phd_Studies/venv/py3/bin/python'
KEY = '/Users/chrissleong/Documents/Phd_Studies/openai.key'

PRPS = ['I', 'she', 'he', 'it', 'you', 'we', 'they', 'them', 'him', 'her']


def normalisation(sentence: str) -> str:
    if sentence[-1] != '.':
        sentence += '.'
    return sentence + '\n'

def get_key() -> str:
    key = None
    with open(KEY, 'r') as fp:
        key = fp.read()
    return key


def send_prompt(prompt: str) -> str:
    s = None
    while True:
        try:
            print('Sending prompt to GPT....')
            response = openai.ChatCompletion.create(
                            # model="gpt-3.5-turbo",
                            model="gpt-4o",
                            messages=[
                                {"role": "user", "content": prompt}
                            ],
                            temperature=0
                        )
            s = response.choices[0].message.content
            print('Message received...')
        except openai.error.APIError:
            print('API Error. Retry...')
            continue
        except openai.error.ServiceUnavailableError:
            print('Server overloaded...Retry after 120 seconds...')
            time.sleep(120)
            continue
        else:
            break
    return s


def starchat_send_prompt(prompt: str) -> str:
    API_TOKEN = "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"
    ENDPOINT = "https://api-inference.huggingface.co/models/HuggingFaceH4/starchat-beta"

    model2endpoint = {
        "starchat-alpha": "https://api-inference.huggingface.co/models/HuggingFaceH4/starcoderbase-finetuned-oasst1",
        "starchat-beta": "https://api-inference.huggingface.co/models/HuggingFaceH4/starchat-beta",
        "starchat-2": "https://api-inference.huggingface.co/models/HuggingFaceH4/starchat2-15b-v0.1"
    }

    client = Client(
        model2endpoint["starchat-2"],
        headers={"Authorization": f"Bearer {API_TOKEN}"},
        timeout=1000,
    )

    # generate_kwargs = dict(
    #     temperature=0.2,
    #     max_new_tokens=1024,
    #     top_p=0.5,
    #     repetition_penalty=1.2,
    #     do_sample=True,
    #     truncate=4096,
    #     seed=random.randint(0, 1000000),
    #     stop_sequences=["<|end|>"],
    # )
    generate_kwargs = dict(
        temperature=0.1,
        max_new_tokens=1024,
        top_p=0.5,
        repetition_penalty=1.2,
        do_sample=True,
        truncate=4096,
        seed=random.randint(0, 1000000),
        stop_sequences=["<|end|>"],
    )

    print('Sending prompt to Starchat....')
    stream = client.generate_stream(prompt, **generate_kwargs)
    print('Message received....')
    output = "" 

    for idx, response in enumerate(stream):
        if response.token.special:
            continue
        output += response.token.text

    print(output)
    return output



def main(filename):
    openai.api_key = get_key().strip()

    sentence = None
    with open(filename, 'r') as fp:
        sentence = fp.read()

    if not sentence.strip():
        print('Warning: %s is empty...Nothing to be done in phase 1.....' % filename)
        exit(-1)

    _arr = filename.split('/')
    folder = '/'.join(_arr[:-1])
    index = _arr[-1].split('.')[0]

    signature = None
    with open('%s/methodsignature' % folder, 'r') as fp:
        signature = fp.read()
    signature = signature.strip()
    if not signature:
        print('error in getting signature of ' % folder)
        exit(-1)

    prompt = 'Please convert this sentence to JML: "%s"'
    if not os.path.exists('%s/gpt-results/jml-gpt-4o.txt' % (folder)):
        gpt4_s = send_prompt(prompt % (sentence))
    # starchat_s = starchat_send_prompt(prompt % (sentence))

        print('Writing result...')

    
        with open('%s/gpt-results/jml-gpt-4o.txt' % (folder), 'w') as fp:
            fp.write(gpt4_s)
    # with open('%s/gpt-results/jml-starchat.txt' % (folder), 'w') as fp:
        # fp.write(starchat_s)
   



if __name__ == "__main__":
    main(filename = sys.argv[1])