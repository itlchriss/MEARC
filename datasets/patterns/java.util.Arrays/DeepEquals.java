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

    //@ ensures (*If the length of parameter x is equal to the length of parameter y and the elements of parameter x are deeply equal to the elements of parameter y, the result is true.*);
    /*@ ensures (
            (
                (
                    (x == null && y == null) && 
                    (
                        (x == y) || (x.equals(y)) || Arrays.equals(x, y)
                    )
                ) || 
                (
                    (x != null && y != null && x.length == y.length) && 
                    (
                        \forall int i; 0 <= i < x.length; 
                        ( 
                            (x[i] == null && y[i] == null) || 
                            (
                                x[i] != null && y[i] != null && 
                                (
                                    !x[i].getClass().isArray() &&
                                    !y[i].getClass().isArray() &&
                                    x[i].equals(y[i])
                                )
                            )  
                        )
                    )
                )
            ) ==>
            (\result == true)
        );
    @*/
    public boolean deepEquals(Integer[] x, Integer[] y) {
        return Arrays.deepEquals(x, y);
    }
}


// (x[i] instanceof Integer) && 
// (y[i] instanceof Integer) && 
// x[i].equals(y[i])