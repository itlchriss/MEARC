public class BubbleSort {

    /*@
         requires arr != null;
         ensures \forall int k;0 <= k && k < arr.length-1;arr[k] >= arr[k+1];
     @*/
    public static void sort(int [] arr) {
        //@ final ghost int n = arr.length;
        
        // bounds
        //@ loop_invariant 0 <= i <= n;
        // elements up-to i are sorted
        //@ loop_invariant \forall int k; 0<= k < i; \forall int l; k < l < n; arr[k] >= arr[l];
        //@ decreasing n-i;
        for (int i = 0; i < arr.length; i++) {
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