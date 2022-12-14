// based upon the example written by Viktorio S. el Hakim
public class Sort {
    //@ requires (*The input arr should not be null.*);
    //@ ensures (*The input arr should be sorted in descending order.*);
    public static void sort(int[] arr) {
        //@ final ghost int n = arr.length;
        
        // bounds
        //@ loop_invariant 0 <= i <= n;
        // elements up-to i are sorted
        //@ loop_invariant \forall int k; 0<= k < i; \forall int l; k < l < n; arr[k] >= arr[l];
        //@ decreasing n-i;
        for (int i = 0; i < arr.length; i++) {
            
            // bounds
            //@ loop_invariant i <= j <= n-1; 
            // j-th element is always the largest
            //@ loop_invariant \forall int k; j <= k < n; arr[j] >= arr[k]; 
            // elements up-to i remain sorted
            //@ loop_invariant \forall int k; 0 <= k < i; \forall int l; k < l < n; arr[k] >= arr[l]; 
            //@ decreasing j;
            for (int j = arr.length-1; j > i; j--) {
                if (arr[j-1] < arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }
    }
}
