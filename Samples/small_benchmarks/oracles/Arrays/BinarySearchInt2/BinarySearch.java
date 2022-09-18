import java.util.Arrays;

public class BinarySearch {
    //+ eliminates "(as by the sort(int[]) method)"
    //+ eliminates "prior to making this call"    
    //+ semantics "sort",vbn,1,(x):\forall int k;0 <= k && k < x.length-1;x[k]<=x[k+1]
    //+ semantics "index of the key",nn , 1,(*):Arrays.asList(array).indexOf(key)
    //+ semantics "input array", nn, 1, (*):array
    //+ semantics "specified key", nn, 1, (*):key
    //+ eliminates "defined as the point at which the key would be inserted into the array:"
    //+ eliminates "Note that this guarantees that"
    //+ eliminates "If the array is not sorted, the results are undefined."
    //+ semantics "index of the search key", nn, 1, (*):Arrays.asList(array).indexOf(key)
    //+ semantics "index of the first element greater than the key", nn, 1, (*):\exists int ip; (\forall int k; k < ip && array[ip] > key); ip
    //+ semantics "all elements in the array", nn, 1, (*):\forall int i; 0 <= i < array.length; array[i]
    //+ semantics "some elements in the array", nn, 1, (*):\exists int i; 0 <= i < array.length; array[i]
    //+ semantics "insertion point", nn, 1, (*):-\result-1
    //+ semantics "array.length", nn, 1, (*):array.length
    //+ semantics "contain", vb, 2, (a, b):\exists int i; 0 <= i < a.length; a[i] == b
    //+ semantics "less than", in, 2, (a, b):a < b
    //+ semantics "greater than", vbg, 2, (a, b):a > b
    //+ semantics "do", vbz, 1, (*):I
    //+ eliminates "the array does not contain the key and"
    //@ requires (*The array must be sorted (as by the sort(int[]) method) prior to making this call.*);
    //@ ensures (*The result is the index of the search key, if the array contains the key.*);
    //@ ensures (*If the array does not contain the key and all elements in the array are less than the key, the insertion point is equal to the array.length.*);
    //@ ensures (*If the array does not contain the key and some elements in the array are greater than the key, the insertion point is the index of the first element greater than the key.*);
    public int binarySearch(int[] array, int key) {
        return Arrays.binarySearch(array, key);
    }
}

/*
Reference:
https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
Class:
Java SE 8 - Arrays
Method: public static int binarySearch(int[] a, int key)
Original documentations:
Searches the specified array of ints for the specified value using the binary search algorithm. 
The array must be sorted (as by the sort(int[]) method) prior to making this call. 
If it is not sorted, the results are undefined. 
If the array contains multiple elements with the specified value, there is no guarantee which one will be found.
Parameters:
a - the array to be searched
key - the value to be searched for
Returns:
index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). 
The insertion point is defined as the point at which the key would be inserted into the array: 
the index of the first element greater than the key, 
or a.length if all elements in the array are less than the specified key. 
Note that this guarantees that the return value will be >= 0 if and only if the key is found.

Approach:
- Restrictions:
    - Java program without concurrent use
    - NLs are specifications describing program behaviour
-1. NL to MR using ccg2lambda
    - Possible outcomes:
        i.  MR with full reduction (alpha and beta reductions)
            - ideal case. No lambda symbols (\)
        ii. errors thrown by ccg2lambda
            - providing visualisation and log to developers
            - what to do?
                - When to do and how to do
                a. adding semantics by //+ semantics to help the NLP understand idiom phrases, and go to 1.
                b. neglecting phrases that are useless or meaningfulless for specifications, and go to 1.
                c. rewrite the sentence, and go to 1.
        iii. MR without fully reduced
            * MR without fully reduced is not an error to ccg2lambda
            * We provide this detection to the ccg2lambda pipeline and provide error messages
            - developers have to check the log, and deal with the phrase that cannot be reduced.
                We provide semantics construct to help the tool in reducing the phrase.     
            - developers can decide if the non-reducing phrase is important,
                or for debugging the NL to check whether the phrase is actually the ONLY non-reduced aspect,
                they can choose to neglect it.                                                               
-2. MR to JML using transcompiler       
    - Possible outcomes:
        i.  JML
            * explain the algorithm
            - ideal case. All the predicates have corresponding semantics.
        ii. terms unrecognised
            - providing errors to developers
            - what to do?
                a. adding semantics by //+ semantics, and go to 2.
                b. adding neglects
                c. rewrite the sentence, and go to 2.
-3. Evaluate JML and verify program correctness with Z3 (4.3.1/4.7.1) in OpenJML(version?0.17.0)
    - With the -ce, -subexpressions for debugging in Z3
    - Possible outcomes:
        i.  Passed
            - ideal case. The generated JML specifications and program behaviour are consistent to each other.
        ii. Failed
            - developers can check with the log provided by OpenJML
            - what to do? (Following steps of usual OpenJML flow)
                a. checking if the JML pre and post- conditions are complete.
                    - if not, make it complete in the NL, and go to 1.
                b. checking if the JML semantics are the same as the ideas that developers specified in the NL.
                    - if not, modifying semantics of terms specified by //+ semantics, and go to 2.
                        - semantics will prefer the one specified by //+ semantics 
                            when the term has been listed in the semantic interpretation library.                
                c. checking if the program requires excessive support of checking
                    - this should be described in the log of OpenJML
                        - such as, loop invariant potentially lesser than zero when it is used as array index
                    - providing necessary invariants, and go to 3.
                d. the program has not fulfiled the specification, correct the code, and go to 3.
- Evaluations:
    - How many specifications are managed to be translated to JML?

Analysis:
- Bottomline:
    - We select only the sentences that are describing the program behaviour. 
    - The NLP tool cannot parse terms in brackets correctly.
    - The NLP tool cannot parse programming expressions, as it lacks support in software engineering field.

- In the description, the first sentence summarises what we expect the method does with a broad explanation:
    - Searches the specified array of ints for the specified value using the binary search algorithm.
        - Contract specifies interface behaviour, but "using the binary search" is an internal behaviour.
        - The sentence does not specify the return behaviour with a very broad phrase "searches the specified array".
        - It is ambiguous and incomplete. 
    * This is not selected.
    * Developers may use neglect.
- In the description, there are two parts that can be potentially preconditions, they are:
    i. The array must be sorted (as by the sort(int[]) method) prior to making this call. 
        If it is not sorted, the results are undefined. 
        - This part is good and clear.
        * This is selected in the preconditions.
    ii.If the array contains multiple elements with the specified value, 
         there is no guarantee which one will be found.
        - This part is not good. Because it subjects the program behaviour is non-determinated.
        * This is not selected.
- In the return description, 
    i. index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). 
    The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
        - This sentence is grammatically good. 
        * This is selected in the postconditions.
- Attempting to run Step 1. Ending with both preconditions and postconditions are not fully reduced. 
    Pipeline stopped.
    - Arrive Step 1.ii.
        - Developers found that the terms in the bracket in the preconditions are not fully reduced
            - exists x.(_array@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; _RIGHTB@{rrb_COMMAo}(\Q2 F1.Q2(\z4.(exists z1.(_int@{jj_COMMAi_dash_np}(z1) &amp; _[@{nn_COMMAi_dash_np}(z1) &amp; _]@{nn_COMMAi_dash_np}(z1) &amp; _method@{nn_COMMAi_dash_np}(_RIGHTB@{rrb_COMMAo},z1) &amp; TrueP &amp; _LEFTB@{lrb_COMMAo}(z4,z1)) &amp; F1(z4))),\F2 F3.exists z4.(_sort@{nn_COMMAi_dash_np}(z4) &amp; F2(z4) &amp; F3(z4)),\w.TrueP,\y._by@{in_COMMAb_dash_pp}(_LEFTB@{lrb_COMMAo},y),\Q.Q(\w.TrueP,\z4._sort@{vbn_COMMAi_dash_vp}(z4)),\G1 G2.G2(x)) &amp; exists z2.(_call@{nn_COMMAi_dash_np}(z2) &amp; TrueP &amp; Prog(_make@{vbg_COMMAi_dash_vp}(x,z2))))
            - referring to "(as by the sort(int[]) method)"
                - two possible linguistic solutions
                    a. As the term inside in the bracket is not important, it is to say you can sort with the sort method, but it is not compulsory to use the method.
                        What is compulsory is the words "The array must be sorted".
                        Developer can use the construct (//+ neglect) to neglect the bracketed term.
                    b. Developer can rewrite the statement. This is always an option.
                - choosing a., added the construct at line 4. go to run Step 1 again.
- Attempting to run Step 1.
    - The preprocessor throws warnings
        -   Warning: The use of pronoun ( it ) may not be correctly processed by the NLP. Please consider to replace it with proper noun.
            If it is not sorted, the results are undefined.
            ___^^_________________________________________
            Warning: The use of pronoun ( it ) may not be correctly processed by the NLP. Please consider to replace it with proper noun.
            index of the search key, if **it** is contained in the array; otherwise, (-(insertion point) - 1).
            Warning: The use of pronoun ( that ) may not be correctly processed by the NLP. Please consider to replace it with proper noun.
            Note **that** this guarantees **that** the result is greater_than_or_equal_to 0 if the key is found.
        - The warnings warn that the pronouns may be better to be replaced.
    - Still, step 1 is passed, go to step 2.
- >>>>>>>> NLP has failed to process the pre conditions.. please check with the log in the stdout...
    - This indicates that NLP has encountered errors
        - Preconditions
            - ERROR:root:Failed to parse \Q.Q(\w.TrueP,\x.exists z1.(_call@{nn_COMMAi_dash_np}(z1) & TrueP & Prog(_make@{vbg_COMMAi_dash_vp}(\Q.Q(\w.TrueP,\x._sort@{vbn_COMMAi_dash_vp}(x)),z1)))(\F1 F2.F2(x))). Error: The function '(_call@{nn_COMMAi_dash_np}(z1) & TrueP & Prog(_make@{vbg_COMMAi_dash_vp}(\Q.Q(\w.TrueP,\x._sort@{vbn_COMMAi_dash_vp}(x)),z1)))' is not a Lambda Expression, an Application Expression, or a functional predicate, so it may not take arguments.
\Q.Q(\w.TrueP,\x.exists z1.(_call@{nn_COMMAi_dash_np}(z1) & TrueP & Prog(_make@{vbg_COMMAi_dash_vp}(\Q.Q(\w.TrueP,\x._sort@{vbn_COMMAi_dash_vp}(x)),z1)))(\F1 F2.F2(x)))
                                                                                                                                                        ^
ERROR:root:An error occurred: The function '(_call@{nn_COMMAi_dash_np}(z1) & TrueP & Prog(_make@{vbg_COMMAi_dash_vp}(\Q.Q(\w.TrueP,\x._sort@{vbn_COMMAi_dash_vp}(x)),z1)))' is not a Lambda Expression, an Application Expression, or a functional predicate, so it may not take arguments.
\Q.Q(\w.TrueP,\x.exists z1.(_call@{nn_COMMAi_dash_np}(z1) & TrueP & Prog(_make@{vbg_COMMAi_dash_vp}(\Q.Q(\w.TrueP,\x._sort@{vbn_COMMAi_dash_vp}(x)),z1)))(\F1 F2.F2(x)))
                - This indicates that the phrase "prior to making this call" is not reduciable. Actually, the phrase "prior to making this call" indicates the phrase "the array must be sorted" is the actual precondition.
                * Developers can use (\\- neglect) to neglect the phrase.
                    - The beauty of using this is that, developers consider neglecting it can be more precise, but not changing and rewriting the specifications.
                - The construct is added at line 5. Run again. Step 1 Passed.
            - Transcompiler throws error
                -   EEK, parse error!  Message: syntax error in line 2, column 12
                    exists x.( & -_sort@{vbn,i-vp}(x)) -> exists x.(_result@{nns,i-np}(x) & _be@{vbp,i-vp}(x) & _undefined@{jj,i-adjp}(x))
                    ___________^
                -   This error is due to the use of pronoun 'it' which has been warned in the preprocessing stage.
                * Suppose developers specify 'it' as 'the array', as shown at line 12. Run again.          
                -   Another error, but it is due to the semantics not specified.
                -   predicate(_sort) issue:
                    EEK, parse error!  Message: symbol not found for a predicate with the syntax, please check the predicate semantics library/dynamic symbol table in line 2, column 52
                    exists x.(_array@{nn,i-np}(x) & -_sort@{vbn,i-vp}(x)) -> exists x.(_result@{nns,i-np}(x) & _be@{vbp,i-vp}(x) & _undefined@{jj,i-adjp}(x))
                    ___________________________________________________^    
                    - Developers should specify what is the semantic for 'sort'
                    * Developers can use (//+ semantics), suppose they specify it at line 6. And run again.
                -   predicate(undefined) issue:
                    EEK, parse error!  Message: symbol not found for a predicate with the syntax, please check the predicate semantics library/dynamic symbol table in line 2, column 135
                    exists x.(_array@{nn,i-np}(x) & -_sort@{vbn,i-vp}(x)) -> exists x.(_result@{nns,i-np}(x) & _be@{vbp,i-vp}(x) & _undefined@{jj,i-adjp}(x))
                    ______________________________________________________________________________________________________________________________________^
                    - Developers should specify what is the semantic for 'undefined'
                    - However, the sentence 'If ..., the result is ...' should be a postcondition.
                    - If we move it to the postcondition, then the conditions are shown at line 13 and 17. 
                    - Run again, passed. Then, the JML for the precondition will be:
                        - requires (\forall int k;0 <= k && k < array.length-1;array[k]<=array[k+1]); 
                        - The precondition: "The array must be sorted prior to making this call."
                            - neglecting "prior to making this call".
                    * We comment on this precondition that it is incomplete, 
                         because if the array is null, the function can be broken.                    
-   >>>>>>>> Post conditions in ./tmp/BinarySearch_spec_post.mr is not fully reduced... 
           stopped proceeding with this part...
    - This indicates that NLP has encountered errors
        - Follow the flow, we check with the log
            - The result should be the insertion point. The insertion point is .........
             - //+ define ""    (This can be a good strategy!)
            - ERROR:root:Failed to parse \Q2 F1.Q2(\x.((exists x.(_key@{nn_COMMAi_dash_np}(x) & TrueP & _would@{md_COMMAi_dash_vp}(_if@{in_COMMAi_dash_sbar}(all z11.(_element@{nns_COMMAi_dash_np}(z11) -> ((exists z9.(_array@{nn_COMMAi_dash_np}(z9) & TrueP & _in@{in_COMMAi_dash_pp}(z11,z9)) & TrueP) -> (_be@{vbp_COMMAi_dash_vp}(z11) & exists z10.(_specified@{jj_COMMAi_dash_np}(z10) & _key@{nn_COMMAi_dash_np}(z10) & TrueP & _less_than@{in_COMMAi_dash_pp}(z11,z10))))),exists z7.(_array@{nn_COMMAi_dash_np}(z7) & (_key@{nn_COMMAi_dash_np}(\F1 F3.exists z5.(_index@{nn_COMMAi_dash_np}(z5) & exists z4.(_first@{jj_COMMAi_dash_np}(z4) & _element@{nn_COMMAi_dash_np}(z4) & _greater_than@{nn_COMMAi_dash_np}(z4) & TrueP & _of@{in_COMMAi_dash_pp}(z5,z4)) & F1(z5) & F3(z5)),\w.TrueP,\y._:@{in_COMMAo}(z7,y)) | exists z6.(_a_DOTlength@{nn_COMMAi_dash_np}(z6) & TrueP & _:@{in_COMMAo}(z7,z6))) & TrueP & _into@{in_COMMAi_dash_pp}(x,z7) & _insert@{vbn_COMMAi_dash_vp}(x)))))(\w.TrueP))(\y._at@{in_COMMAi_dash_pp}(x,y)) & F1(x))). Error: The function '(_key@{nn_COMMAi_dash_np}(x) & TrueP & _would@{md_COMMAi_dash_vp}(_if@{in_COMMAi_dash_sbar}(all z11.(_element@{nns_COMMAi_dash_np}(z11) -> ((exists z9.(_array@{nn_COMMAi_dash_np}(z9) & TrueP & _in@{in_COMMAi_dash_pp}(z11,z9)) & TrueP) -> (_be@{vbp_COMMAi_dash_vp}(z11) & exists z10.(_specified@{jj_COMMAi_dash_np}(z10) & _key@{nn_COMMAi_dash_np}(z10) & TrueP & _less_than@{in_COMMAi_dash_pp}(z11,z10))))),exists z7.(_array@{nn_COMMAi_dash_np}(z7) & (_key@{nn_COMMAi_dash_np}(\F1 F3.exists z5.(_index@{nn_COMMAi_dash_np}(z5) & exists z4.(_first@{jj_COMMAi_dash_np}(z4) & _element@{nn_COMMAi_dash_np}(z4) & _greater_than@{nn_COMMAi_dash_np}(z4) & TrueP & _of@{in_COMMAi_dash_pp}(z5,z4)) & F1(z5) & F3(z5)),\w.TrueP,\y._:@{in_COMMAo}(z7,y)) | exists z6.(_a_DOTlength@{nn_COMMAi_dash_np}(z6) & TrueP & _:@{in_COMMAo}(z7,z6))) & TrueP & _into@{in_COMMAi_dash_pp}(x,z7) & _insert@{vbn_COMMAi_dash_vp}(x)))))' is not a Lambda Expression, an Application Expression, or a functional predicate, so it may not take arguments.
\Q2 F1.Q2(\x.((exists x.(_key@{nn_COMMAi_dash_np}(x) & TrueP & _would@{md_COMMAi_dash_vp}(_if@{in_COMMAi_dash_sbar}(all z11.(_element@{nns_COMMAi_dash_np}(z11) -> ((exists z9.(_array@{nn_COMMAi_dash_np}(z9) & TrueP & _in@{in_COMMAi_dash_pp}(z11,z9)) & TrueP) -> (_be@{vbp_COMMAi_dash_vp}(z11) & exists z10.(_specified@{jj_COMMAi_dash_np}(z10) & _key@{nn_COMMAi_dash_np}(z10) & TrueP & _less_than@{in_COMMAi_dash_pp}(z11,z10))))),exists z7.(_array@{nn_COMMAi_dash_np}(z7) & (_key@{nn_COMMAi_dash_np}(\F1 F3.exists z5.(_index@{nn_COMMAi_dash_np}(z5) & exists z4.(_first@{jj_COMMAi_dash_np}(z4) & _element@{nn_COMMAi_dash_np}(z4) & _greater_than@{nn_COMMAi_dash_np}(z4) & TrueP & _of@{in_COMMAi_dash_pp}(z5,z4)) & F1(z5) & F3(z5)),\w.TrueP,\y._:@{in_COMMAo}(z7,y)) | exists z6.(_a_DOTlength@{nn_COMMAi_dash_np}(z6) & TrueP & _:@{in_COMMAo}(z7,z6))) & TrueP & _into@{in_COMMAi_dash_pp}(x,z7) & _insert@{vbn_COMMAi_dash_vp}(x)))))(\w.TrueP))(\y._at@{in_COMMAi_dash_pp}(x,y)) & F1(x)))
            - This error comes with the sentence defining what insertion point is, which is the sentence of             
                - The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
                - In fact, this sentence in our approach can be written using the semantics construct.
                    - pros: The specifications can be neat and tidy, and be more specific.
                    - cons: The specifications are not entirely natural language.
        - The current post conditions are listed at line 17. 
        - We dicuss the sentence into three parts
            - If the array is not sorted, the results are undefined. 
                * the term "undefined" is very unprecise, at least in Java.
                * Developers can either define "undefined" properly, or neglect this sentence.
                * As I don't have a clue about "undefined", I choose the to neglect it.
            - index of the search key, if it is contained in the array. 
                * The sentence is grammatically correct, but NLP cannot recognise it properly due to missing subject.
                * Developers can add a few words, such as "the result is". 
                    - Distance is 3 with unit repect to word(s).
                * We have also raised the warning about the pronoun "it", this may not be reduced properly.
                - _if@{in_COMMAi_dash_sbar}
                    (exists x.(TrueP &amp; 
                        exists z2.(_array@{nn_COMMAi_dash_np}(z2) &amp; TrueP &amp; 
                            _in@{in_COMMAi_dash_pp}(x,z2) &amp; _contain@{vbn_COMMAi_dash_vp}(x))),
                        exists x.(_result@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; 
                            exists z1.(_index_of_the_search_key@{nn_COMMAi_dash_np}(z1) &amp; TrueP &amp;
                                 _be@{vbz_COMMAi_dash_vp}(x,z1))))
                - Apparently, the sentence is not converted properly, x is the method, and contain(x), it should be contain is a two arity predicate.
                - We substitute "it" with "the key", and the resulting MR is
                    - _if@{in_COMMAi_dash_sbar}(exists x.(_key@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; exists z2.(_array@{nn_COMMAi_dash_np}(z2) &amp; TrueP &amp; _in@{in_COMMAi_dash_pp}(x,z2) &amp; _contain@{vbn_COMMAi_dash_vp}(x))),exists x.(_result@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; exists z1.(_index_of_the_search_key@{nn_COMMAi_dash_np}(z1) &amp; TrueP &amp; _be@{vbz_COMMAi_dash_vp}(x,z1))))
                    - At least the MR is good, but not good enough for us to process.
                    - We leave the supplementary semantics from developers in the later discussions.
            - otherwise, (-(insertion point) - 1). 
                - _RIGHTB@{rrb_COMMAo}(_HYPHEN(\Q1 Q2 F1.Q2(\x.(Q1(\w.TrueP,\y._LEFTB@{lrb_COMMAo}(x,y)) &amp; F1(x))),\F1._RIGHTB@{rrb_COMMAo}(\F1 F2.exists x.(_insertion@{nn_COMMAi_dash_np}(x) &amp; _point@{nn_COMMAi_dash_np}(x) &amp; F1(x) &amp; F2(x)),_LEFTB@{lrb_COMMAo},\x.(exists z2.(_1@{cd_COMMAi_dash_np}(z2) &amp; TrueP &amp; _dash_@{in_COMMAo}(x,z2)) &amp; F1(x)))),\Q F1.Q(\x.(_otherwise@{rb_COMMAi_dash_advp}(x) &amp; F1(x))))
                - This sentence expression reduction failed.
                * NLP requires advancement on this. 
                    * We put this as limitations: NLP has difficulties in recognising expression terms.
                * Currently, we remove the brackets from the term (-(insertion point)-1), leave it as -(insertion point)-1.
                    - exists x.(_result@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; exists z1.(_RIGHTB@{rrb_COMMAo}(\F1 F2.exists z1.(_insertion@{nn_COMMAi_dash_np}(z1) &amp; _point@{nn_COMMAi_dash_np}(z1) &amp; F1(z1) &amp; F2(z1)),\z1._dash_1@{nn_COMMAi_dash_np}(z1),_HYPHEN,z1) &amp; TrueP &amp; _be@{vbz_COMMAi_dash_vp}(x,z1)))
                    * Still, not working, this is due to the tokeniser recognises the bracket as a single token. Then, we remove all the brackets.
                        -  exists x.(_result@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; exists z1.(_dash_insertion@{jj_COMMAi_dash_np}(z1) &amp; _point_dash_1@{nn_COMMAi_dash_np}(z1) &amp; TrueP &amp; _be@{vbz_COMMAi_dash_vp}(x,z1)))                   
                        * Still, not working, again it is due to the tokeniser.
                        * Suggesting training a tokeniser for software engineering terms, or expressions.
                        * In addition, the meaning of "otherwise" has disappeared.
                        * The only choice is to rewrite the sentence.
                        * We copy the clause from the previous sentence, and added a semantics to denote "insertion point" is a single term.
                        * Note that, "insertion point" is a very COMPLEX single term.
                            - exists x.(_key@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; -exists z2.(_array@{nn_COMMAi_dash_np}(z2) &amp; TrueP &amp; _in@{in_COMMAi_dash_pp}(x,z2) &amp; _contain@{vbn_COMMAi_dash_vp}(x))) -&gt; exists x.(_result@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; _be@{vbz_COMMAi_dash_vp}(x) &amp; _dash_insertion_point_dash_1@{jj_COMMAi_dash_adjp}(x))
                            - Better, at least we can leave the MR to the translation stage.
            - The insertion point is defined as the point at which the key would be inserted into the array:
             the index of the first element greater than the key, 
             or a.length if all elements in the array are less than the specified key. 
             ==> - Putting "the index of the first element greater than the key" as a term 
             - OR, "the first element greater than the key" as term, then we implement "index of"
                - This sentence is too complicated for the NLP to reduce.
                - Actually, the phrase "defined as the point at which the key would be inserted into the array" is a business logic. For method specification, I suggest to neglect it.
                - Neglecting it, the problem comes to the first clause has no conditions for the NLP which is
                    - The insertion point is the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. 
                        - This sentence should be interpreted as
                            - The insertion point is the index of the first element greater than the key 
                               if not all elements in the array are less than the specified key.
                            - Or, a.length if all elements in the array are less than the specified key.
                        - Added some text, but the resulting MR has unreduced part in "the index of the first element greater than the key"
                        * Adding a semantics for "first element greater than the key", the MR is fully reduced
                            * but this can be unacceptable for an idiom, as it is too much information
            - Note that this guarantees that the return value will be >= 0 if and only if the key is found.
                                                                           (      if     )
                * "Note that this guarantees that" is a clause for emphasis, but not necessarily providing extra information in postcondition.
                    - adding a neglect to make the specification more simple.
                    - resulting MR
                         - exists .......  -> exists ......
                        - _if@{in_COMMAi_dash_sbar}
                            (exists x.(_key@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; 
                                 _find@{vbn_COMMAi_dash_vp}(x)),
                            exists x.(_result@{nn_COMMAi_dash_np}(x) &amp; TrueP &amp; 
                            _be@{vbz_COMMAi_dash_vp}(x) &amp; exists z5.(_0@{cd_COMMAi_dash_np}(z5) &amp; 
                            TrueP &amp; _greater_than_or_equal_to@{jj_COMMAi_dash_np}(x,z5))))
                        - The result suggests that NLP needs to advance, as "if" clause is not converted properly here, it should be converted to "->"
                        

*/ 