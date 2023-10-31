# Research questions
1. Based on a grammar constructed from JAVA API documentations, is it enough to translate free-structured code comments found from JAVA open sources into structured sentences?
2. Using an LLM, is it capable to automatically translate free-structured code comments into structured comments?
3. Can the compiler automatically translate the MRs derived from these structured code comments into JMLs?


# Approach
1. Using LLM to convert free-structured natural language comments into structured natural language
2. Checking if the structured natural language fulfils a grammar by a parser
3. Writing SIs for all the terminals listed in the grammar


# Research tasks
[] Checking all sentence structures accepted by ccg2lambda
[] Checking all words and terms used in JAVA API
[] Establishing a grammar

# Grammar
- Runtime generated
    - Extracting the symbols from the program that needs to be verified
        - these symbols must be forced to be nouns        
- generated based on a template grammar
    - consists of terminal list, constructed from analysing JAVA API documentation
        - verbs
        - noun phrases concatenated with underscores ('_')

# NLP flow
- Splitting sentences at '.' (period)

# LLM works (need to be written in a good text)
## Rules
- Converting all passive voice to active voice
- Converting imperative sentences to declarative sentences
- Converting the manner of parameter references from implicit to explicit
    - Replacing
        - 'specified' + [java_api_datatype] -> parameter_with_[datatype]_type
- Defining verb extensions
    - 

## Prompt
Using the following text as an example:
Removes element at index, but instead of copying all elements to the left, moves into the same slot the last element. This avoids the copy costs, but spoils the list order. If index is the last element it is just removed.

Fast method of finding the next power of 2 greater than or equal to the supplied value.
If the value is <= 0 then 1 will be returned.
This method is not suitable for Integer MIN_VALUE or numbers greater than 2^30. When provided
then Integer MIN_VALUE will be returned.

- Preprocessing. Aim to simplify the sentence in this stage.
    ```
    You are a text preprocessor. I will give you a text. Please follow these rules to preprocess the sentence:
    1. Breaking the text into sentences.
    2. Converting imperative sentence into declarative sentence.
    3. Simplifying the sentences whenever possible.
    4. Removing all adverbs and adjectives that are not providing vital information, such as simply and just.
    5.  Replacing pronouns with nouns.
    6. Using only the verbs in the following list in the sentences: be, remove, put. 

    The text is: [text]
    ```
- Analysing. Aim to convert the sentences into behavioural specifications. We need to include the information from the programs.
    ```
    I will give you a comment of a method. Please convert the sentence to behavioural specification. 
    [method information]
    All the information you provide should only base on the given sentence.
    If a sentence is not certain (i.e. need to use "may", "perhaps", etc.), you should not return such sentence.
    I do not concern about the objective of the sentence.
    The sentence is: [sentence]
    ```
    - example method information: This method has two parameters, one is "list" and its datatype is ArrayList, another parameter is "index" and its datatype is integer datatype. Please only return preconditions and postconditions.
    - example sentence: If the index is the last element, the element is removed.

    - This method has one parameter, it is called "value" and its datatype is integer. Please only return preconditions and postconditions.
    - A method is used to find the next power of 2 greater than or equal to the supplied value.

# Potential test datasets
- [argona](https://github.com/real-logic/agrona/tree/master/agrona/src/main/java/org/agrona/collections)
    - Agrona provides a library of data structures and utility methods that are a common need when building high-performance applications in Java. Many of these utilities are used in the Aeron efficient reliable UDP unicast, multicast, and IPC message transport and provides high-performance buffer implementations to support the Simple Binary Encoding Message Codec.
        - https://github.com/real-logic/agrona/blob/master/agrona/src/main/java/org/agrona/BitUtil.java




# Restricting input sentence structures
- To describe the behaviour of methods, the descriptions should provide enough information to specify the behaviours. Both declarative and imperative sentences can use positive manners to provide information specifying the method behaviours, such as specifying components in the program (subject or object) to perform an action (verb) with conditions (clauses). Nevertheless, interrogative and exclamative sentences are incapable to provide these information. Therefore, support declarative and imperative sentence structures are necessary to support developers specifying method behaviours.
1. Declarative sentences
2. Imperative sentences