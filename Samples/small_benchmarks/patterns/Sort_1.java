// based upon the example written by Viktorio S. el Hakim

public class Sort_1 {
    //@ requires \exists int[] x; x == arr; x != null;
    //@ requires 1 <= arr.length <= 100;
    //@ ensures \exists int[] x; x == arr; \exists int[] z; \forall int k;0 <= k && k < z.length-1;z[k] >= z[k+1]; x == z;
    public static void sort(int [] arr) {
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
