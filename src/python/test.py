import nltk

patterns = ['NNS', 'WP', '']

def shorten(phrase):
    tokens = nltk.word_tokenize(phrase)
    tags = nltk.pos_tag(tokens)

    needed_tags = ['DT', 'NN', 'NNS', 'JJ', 'JJR', 'JJS', 'VBG']
    print(tags)
    # for i in range(len(tags)):
    #     word = [word for word, pos in tags if (pos in needed_tags)]
    # #   word[0], word[1], word[2], word[3], word[4], word[5] = word[0], word[2], word[3], word[4], word[5], word[1]
    #     tmp = word[1]
    #     print(tmp)
    #     word.pop(1)
    #     word += tmp

    #     for i in word[1]:
    #         if i == 'a' and 'o' and 'i' and 'u' and 'e' and 'y':
    #             word[0] = 'an'
    #         return ' '.join(word)
    #     return ' '.join(word)

print(shorten('All patients who had degenerative_spondylolithesis'))

# nlp = spacy.load("en_core_web_sm")
# doc = nlp(sys.argv[1])

# needed_tags = ['DT', 'NN', 'NNS', 'JJ', 'JJR', 'JJS', 'VBG']
# for i in range(len(tags)):
#     word = [word for word, pos in tags if (pos in needed_tags)]
#     word[0], word[1], word[2], word[3], word[4], word[5] = word[0], word[2], word[3], word[4], word[5], word[1]

#     for i in word[1]:
#         if i == 'a' and 'o' and 'i' and 'u' and 'e' and 'y':
#         word[0] = 'an'
#         return ' '.join(word)
#     return ' '.join(word)


# needed_tags = ['DT', 'NN', 'NNS', 'JJ', 'JJR', 'JJS', 'VBG']
# for i in range(len(doc)):
#     word = [word for text, tag_ in doc if (pos in needed_tags)]
#     word[0], word[1], word[2], word[3], word[4], word[5] = word[0], word[2], word[3], word[4], word[5], word[1]

#     for i in word[1]:
#         if i == 'a' and 'o' and 'i' and 'u' and 'e' and 'y':
#             word[0] = 'an'
#             # return ' '.join(word)
#     # return ' '.join(word)
#     print(' '.join(word))

# print(f"{'text':{8}} {'POS':{6}} {'TAG':{6}} {'Dep':{6}} {'POS explained':{20}} {'tag explained'} ")
# for token in doc:
    # print(f'{token.text:{8}} {token.pos_:{6}} {token.tag_:{6}} {token.dep_:{6}} {spacy.explain(token.pos_):{20}} {spacy.explain(token.tag_)}')
