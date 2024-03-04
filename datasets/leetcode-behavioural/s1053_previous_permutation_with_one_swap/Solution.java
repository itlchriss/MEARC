package g1001_1100.s1053_previous_permutation_with_one_swap;

// #Medium #Array #Greedy #2022_02_28_Time_0_ms_(100.00%)_Space_56.1_MB_(27.21%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 2.*);
//@ ensures(*The elements in the input array `arr` are positive integers.*);
//@ ensures(*The elements in the input array `arr` are not necessarily distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array is not null.*);
//@ ensures(*The length of the returned array is the same as the input array `arr`.*);
//@ ensures(*The elements in the returned array are in the same order as the input array `arr`, except for at most one swap.*);
//@ ensures(*If a swap is made, the swapped elements are in lexicographically largest order.*);
//@ ensures(*If no swap is made, the returned array is the same as the input array `arr`.*);
    public int[] prevPermOpt1(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int diff = Integer.MAX_VALUE;
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] - arr[j] > 0 && diff > arr[i] - arr[j]) {
                    diff = arr[i] - arr[j];
                    index = j;
                }
            }
            if (diff != Integer.MAX_VALUE) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                break;
            }
        }
        return arr;
    }
}