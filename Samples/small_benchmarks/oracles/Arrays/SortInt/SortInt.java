import java.util.Arrays;

public class Sort {
    /*
        Remark: The specification should focus on what the method affects the status.
                The original specification focus on what the method does.
    */
    // ensures (\forall int i; 0 < i < a.length; a[i-1] <= a[i]);
    // Original: Sorts the specified array into ascending numerical order.
    //+ semantics "specified array", nn, 1, (*):a
    //+ semantics "parameter a", nn, 1, (*):a
    //+ semantics "sorted in ascending numerical order", nn, 1, (x):\forall int k;0 <= k && k < x.length-1;x[k]<=x[k+1]
    //+ semantics "ascending numerical order", nn, 1, (x):\forall int k;0 <= k && k < x.length-1;x[k]<=x[k+1]
    //@ ensures (*The parameter x is sorted in ascending numerical order.*);
    public void sort(int[] x) {
        Arrays.sort(x);
    }
}
