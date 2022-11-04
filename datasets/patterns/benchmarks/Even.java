public class Even {
    /* Remark:  The NLP does not convert the sentence correctly. 
                In the second sentence, the "not" has been omitted in the resulting MR.
                exists x.(_result@{nn,i-np}(x) & exists z1.(_false_value@{nn,i-np}(z1) & _be@{vbz,i-vp}(x,z1))) -> 
                    exists x.(_input_number@{nn,i-np}(x) & -(_be@{vbz,i-vp}(x) & _even@{rb,i-advp}(x)))
                There should be a negative sign in the MR representing the rear clause.
                If we fix it by putting the sign there, we can generate the correct JML.
                ensures ((\result==false) ==> (!(number%2 == 0)));
    */
    //@ ensures (*If result is true, the input number is even.*);
    //@ ensures (*If result is false, the input number is not even.*);
    public static boolean isEven(int number) {
        if(number % 2 == 0)
            return true;
        else
            return false;
    }
}
