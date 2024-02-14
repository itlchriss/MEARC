package g0901_1000.s0969_pancake_sorting;

// #Medium #Array #Sorting #Greedy #Two_Pointers
// #2022_03_31_Time_1_ms_(100.00%)_Space_42.2_MB_(88.99%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is greater than or equal to 1.*);
	//@ requires(*All integers in the input array `arr` are unique.*);
	//@ ensures(*The input array `arr` is sorted in ascending order.*);
	//@ ensures(*The output list `kValues` contains the values of `k` corresponding to a sequence of pancake flips that sort the array `arr`.*);
	//@ ensures(*The length of the output list `kValues` is less than or equal to 10 times the length of the input array `arr`.*);
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for (int i = arr.length; i >= 1; i--) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            for (int j = 0; j < i; j++) {
                if (max < arr[j]) {
                    index = j + 1;
                    max = arr[j];
                }
            }
            result.add(index);
            reverse(arr, index - 1);
            result.add(i);
            reverse(arr, i - 1);
        }
        return result;
    }

    private void reverse(int[] arr, int index) {
        for (int i = 0; i <= (index - 1) / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[index - i];
            arr[index - i] = temp;
        }
    }
}