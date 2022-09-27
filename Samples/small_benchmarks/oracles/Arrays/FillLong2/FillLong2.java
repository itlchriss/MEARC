import java.util.Arrays;

public class Fill {
    // Assigns the specified long value to each element of the specified array of longs.
    // ensures (\forall int i; 0 <= i < a.length; a[i] == val);
    //+ semantics "elements in array a", nn, 1, (*):\forall int i; 0 <= i < a.length; a[i]    
    //+ semantics "specified long value", nn, 1, (*):val
    //+ semantics "each element of the specified array of longs", nn, 1, (*):\forall int i; 0 <= i < a.length; a[i]  
    //@ ensures (*Every element in parameter x equals to the parameter val.*);
    // ensures (*The method assigns the specified long value to each element of the specified array of longs.*);
    public void fill(long[] x, long val) {
        Arrays.fill(x, val);
    }
}
