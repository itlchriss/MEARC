import java.util.Arrays;

public class CopyOf {
    /*
         Original: 
         returns: 
         a copy of the original array, truncated or padded with zeros to obtain the specified length
         description:
         Copies the specified array, truncating or padding with zeros (if necessary) 
         so the copy has the specified length. 
         For all indices that are valid in both the original array and the copy, 
         the two arrays will contain identical values. 
         For any indices that are valid in the copy but not the original, 
         the copy will contain 0. 
         Such indices will exist if and only if the specified length is greater than 
         that of the original array.
    */
    /*
        Missing specifications:
    */
    //+ semantics "parameter original", nn, 1, (*):original
    //+ semantics "parameter newlength", nn, 1, (*):newLength
    //+ semantics "length of result", nn, 1, (*):\result.length
    //@ requires (*The parameter original should not be null.*);
    //@ requires (*The parameter newLength should be greater than or equal to 0.*);
    //@ ensures (*The length of result should be equal to the parameter newLength.*);
    /*
        Modified specifications:
    */
    //+ eliminates "will"
    // semantics "identical values", nn, 1, (*): original[i] == \result[i]
    // ensures (*For all indices that are valid in both the original array and the copy, the two arrays will contain identical values. *);
    // ensures (*For all indices that are valid in both the original array and the copy, the two arrays will contain the identical values. *);
    //+ semantics "copy", nn, 1, (*):\result
    //+ semantics "identical values for all valid indices", nns, 1, (*):\exists int[] iv; iv.length == original.length && iv.length < newLength
    //+ semantics "have", vbp, 2, (x,y):y;(\forall int i; 0 <= i < iv.length; x[i] == iv[i])
    //@ ensures (*The parameter original and the copy will have identical values for all valid indices.*);
    // 'for any indices that are valid in the copy but not the original' this sentence is too complicated and imprecise
    //+ semantics "for any indices that are valid in the copy but not the original", jj, 1, (*):\forall int i; original.length <= i < newLength; 
    //+ semantics "contain", vbp, 2, (a,b):a[i] == b
    //@ ensures (*The copy will contain 0 for any indices that are valid in the copy but not the original.*);
    /*
        Reference specifications:
    */
    //@   requires original != null;
    //@   requires newLength >= 0;
    //@   ensures \fresh(\result);
    //@   ensures \result.length == newLength;
    //   ensures (\forall int i; 0 <= i < original.length && i < newLength; \result[i] == original[i]);
    /*   ensures (\exists int[] iv; iv.length == original.length && iv.length < newLength; 
                ((\forall int i; 0 <= i < iv.length; original[i] == iv[i]) &&
                (\forall int i; 0 <= i < iv.length; \result[i] == iv[i]))
                );
    */
    /*@
        ensures ((\exists int[] iv; iv.length == original.length && iv.length < newLength;(\forall int i; 0 <= i < iv.length; original[i] == iv[i])) && (\exists int[] iv; iv.length == original.length && iv.length < newLength;(\forall int i; 0 <= i < iv.length; \result[i] == iv[i])));
    @*/
    //@   ensures (\forall int i; original.length <= i < newLength; \result[i] == 0);

    public int[] copyOf(int[] original, int newLength) {
        return Arrays.copyOf(original, newLength);
    }
}