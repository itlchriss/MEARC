package g2301_2400.s2389_longest_subsequence_with_limited_sum;

// #Easy #Array #Sorting #Greedy #Binary_Search #Prefix_Sum
// #2022_09_02_Time_4_ms_(99.97%)_Space_42.7_MB_(95.35%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The length of the `nums` array, `n`, is greater than or equal to - The length of the `queries` array, `m`, is greater than or equal to - The elements in the `nums` array are integers.*);
	//@ requires(*The elements in the `queries` array are integers.*);
	//@ requires(*The elements in the `nums` array are greater than or equal to 1 and less than or equal to 10^- The elements in the `queries` array are greater than or equal to 1 and less than or equal to 10^*);
	//@ ensures(*The `answer` array has a length of `m`.*);
	//@ ensures(*The elements in the `answer` array are integers.*);
	//@ ensures(*The elements in the `answer` array are the maximum size of a subsequence that can be taken from the `nums` array such that the sum of its elements is less than or equal to the corresponding element in the `queries` array.*);
    public int[] answerQueries(int[] nums, int[] queries) {
        // we can sort the nums because the order of the subsequence does not matter
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            int j = Arrays.binarySearch(nums, queries[i]);
            if (j < 0) {
                j = -j - 2;
            }
            queries[i] = j + 1;
        }
        return queries;
    }
}