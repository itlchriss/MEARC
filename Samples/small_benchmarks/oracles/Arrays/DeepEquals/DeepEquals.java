import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeepEquals {
    /*
         Original: 
Returns true if the two specified arrays are deeply equal to one another. 
Unlike the equals(Object[],Object[]) method, 
this method is appropriate for use with nested arrays of arbitrary depth.

Two array references are considered deeply equal if both are null, 
or if they refer to arrays that contain the same number of elements and all corresponding pairs of elements 
in the two arrays are deeply equal.

Two possibly null elements e1 and e2 are deeply equal if any of the following conditions hold:

    e1 and e2 are both arrays of object reference types, and Arrays.deepEquals(e1, e2) would return true
    e1 and e2 are arrays of the same primitive type, 
        and the appropriate overloading of Arrays.equals(e1, e2) would return true.
    e1 == e2
    e1.equals(e2) would return true. 

Note that this definition permits null elements at any depth.

If either of the specified arrays contain themselves as elements either directly or 
    indirectly through one or more levels of arrays, the behavior of this method is undefined.
    */

    //+ semantics "elements of parameter a1", cd, 1, (*):a1
    //+ semantics "elements of parameter a2", nn, 1, (*):a2
    //+ semantics "length of parameter a1", nn, 1, (*):a1.length
    //+ semantics "length of parameter a2", nn, 1, (*):a2.length
    //+ semantics "deeply equal to", vbg, 2, (X, Y):\forall int i; 0 <= i < X.length; ((X[i] == null && Y[i] == null) || (X[i] == Y[i]) || (X[i].equals( Y[i] )) || (X.getClass().isArray() && X.getClass().isArray() && Arrays.equals(X, Y)))
    //@ ensures (*If the length of parameter x is equal to the length of parameter y and the elements of parameter x are deeply equal to the elements of parameter y, the result is true.*);
    // missing postcondition
    // length of parameter a1 is equal to length of parameter a2
    //@ ensures (((\forall int i; 0 <= i < x.length; ((x[i] == null && y[i] == null) || (x[i] ==y[i]) || (x[i].equals(y[i])) || (x.getClass().isArray() && x.getClass().isArray() && Arrays.equals(x,y)))) && (x.length==y.length)) ==> (\result==true));
    public boolean deepEquals(Object[] x, Object[] y) {
        return Arrays.deepEquals(x, y);
    }
}