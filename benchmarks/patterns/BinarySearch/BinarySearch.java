public class BinarySearch {
    
    // requires sortedArray != null && 0 < sortedArray.length < Integer.MAX_VALUE;
    // requires \forall int i; 0 <= i < sortedArray.length; \forall int j; i < j < sortedArray.length; sortedArray[i] <= sortedArray[j];
    // old boolean containsValue = (\exists int i; 0 <= i < sortedArray.length; sortedArray[i] == value);
    // ensures containsValue <==> 0 <= \result < sortedArray.length;
    // ensures !containsValue <==> \result == -1;
    //@ requires (0 < sortedarray.length < Integer.MAX_VALUE);
    //@ requires !(sortedarray==null);
    //@ requires (\forall int k; 0 <= k < sortedarray.length - 1; sortedarray[k] <= sortedarray[k + 1]);
    /*@ 
        ensures (
            (
                (
                    \exists int i; 0 <= i < sortedarray.length;sortedarray[i] == value
                )
            ) ==> (\result >= 0)
        );
    @*/
    public static int search(int[] sortedarray, int value) {
        // ghost boolean containsValue = (\exists int i; 0 <= i < sortedarray.length; sortedarray[i] == value);
        if (value < sortedarray[0]) return -1;
        if (value > sortedarray[sortedarray.length-1]) return -1;
        int lo = 0;
        int hi = sortedarray.length-1;
        //@ loop_invariant 0 <= lo < sortedarray.length && 0 <= hi < sortedarray.length;
        // loop_invariant containsValue ==> sortedarray[lo] <= value <= sortedarray[hi];
        // loop_invariant \forall int i; 0 <= i < lo; sortedarray[i] < value;
        // loop_invariant \forall int i; hi < i < sortedarray.length; value < sortedarray[i];
        // loop_decreases hi - lo;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (sortedarray[mid] == value) {
                return mid;
            } else if (sortedarray[mid] < value) {
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }
        return -1;
    }
}
