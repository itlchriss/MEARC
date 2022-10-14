import java.util.Arrays;

public class EqualsObject2 {
    //@ ensures (((x != null && y != null && x.length == y.length && ((\forall int i; 0 <= i < x.length;  (x[i] == null && y[i] == null) || (x[i].equals(y[i]))))) && (x.length==y.length)) ==> (\result==true));
    //@ ensures (((y==null) && (x==null)) ==> (\result==true));
    // ensures (*Two arrays are considered equal if both arrays contain the same number of elements, and all corresponding pairs of elements in the two arrays are equal.*);
    //- semantics "length of array a", nn, 1, (*):a.length
    //- semantics "length of array b", nn, 1, (*):b.length
    //- semantics "equal", jj, 1, (*): ==
    //- semantics "elements of array a", nn, 1, (*):a
    //- semantics "elements of array b", nn, 1, (*):b
    //- semantics "correspondingly equal to", vbg, 2, (x, y):\forall int i; 0 <= i < x.length; x[i] == y[i]
    //@ ensures (*If the length of array x is equal to the length of array y and the elements of array x are correspondingly equal to the elements of array y, the result is true.*);
    //- semantics "array a reference", nn, 1, (*): a
    //- semantics "array b reference", nn, 1, (*): b
    //@ ensures (*If the reference of array x is null and the reference of array y is null, the result is true.*);
    public boolean equals(Object[] x, Object[] y) {
        return Arrays.equals(x, y);
    }
}


/*
    Original documentation:
    method description:
        Returns true if the two specified arrays of ints are equal to one another. 
        Two arrays are considered equal if both arrays contain the same number of elements, 
        and all corresponding pairs of elements in the two arrays are equal. 
        In other words, two arrays are equal if they contain the same elements in the same order. 
        Also, two array references are considered equal if both are null.
    Returns:
        true if the two arrays are equal
*/