# Load your usual SpaCy model (one of SpaCy English models)
import spacy
nlp = spacy.load('en')

# load NeuralCoref and add it to the pipe of SpaCy's model
import neuralcoref
coref = neuralcoref.NeuralCoref(nlp.vocab)
nlp.add_pipe(coref, name='neuralcoref')

# Solve coreferences in sentences
doc = nlp('The patients who had degenerative spondylolithesis had an lumbar lordosis angle from 52.6 to 62.1.')
print(doc._.has_coref)
print(doc._.coref_clusters)
print(doc._.coref_resolved)

