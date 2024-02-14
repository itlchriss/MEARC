package g1401_1500.s1460_make_two_arrays_equal_by_reversing_sub_arrays;

// #Easy #Array #Hash_Table #Sorting #2022_06_23_Time_4_ms_(74.61%)_Space_45.8_MB_(56.42%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The lengths of `target` and `arr` are equal.*);
	//@ requires(*The length of `target` and `arr` is at least 1.*);
	//@ requires(*The elements of `target` and `arr` are integers.*);
	//@ requires(*The elements of `target` and `arr` are between 1 and 1000 (inclusive).*);
	//@ ensures(*The method returns `true` if it is possible to make `arr` equal to `target` by reversing sub-arrays, and `false` otherwise.*);
    public boolean canBeEqual(int[] target, int[] arr) {
        int n = target.length;
        Arrays.sort(target);
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < target.length; i++) {
            if (target[i] == arr[i]) {
                count++;
            }
        }
        return count == n;
    }
}