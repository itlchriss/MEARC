package g2001_2100.s2089_find_target_indices_after_sorting_array;

// #Easy #Array #Sorting #Binary_Search #2022_05_27_Time_1_ms_(97.90%)_Space_42.5_MB_(83.38%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is not empty.*);
	//@ requires(*The target element `target` is within the range of 1 to - The length of the input array `nums` is within the range of 1 to*);
	//@ ensures(*The returned list is not null.*);
	//@ ensures(*The returned list is sorted in increasing order.*);
	//@ ensures(*The returned list contains the indices of the target element `target` in the sorted array `nums`.*);
	//@ ensures(*If there are no target indices, the returned list is empty.*);
    public List<Integer> targetIndices(int[] nums, int target) {
        int count = 0;
        int lessthan = 0;
        for (int n : nums) {
            if (n == target) {
                count++;
            }
            if (n < target) {
                lessthan++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(lessthan++);
        }
        return result;
    }
}