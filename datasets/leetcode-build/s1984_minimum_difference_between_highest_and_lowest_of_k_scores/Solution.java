package g1901_2000.s1984_minimum_difference_between_highest_and_lowest_of_k_scores;

// #Easy #Array #Sorting #Sliding_Window #2022_05_23_Time_4_ms_(95.43%)_Space_42.1_MB_(94.99%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to `k`.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ requires(*The values in the input array `nums` are non-negative integers.*);
	//@ ensures(*The method returns an integer representing the minimum possible difference between the highest and lowest scores.*);
	//@ ensures(*The minimum possible difference is non-negative.*);
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int minDiff = nums[nums.length - 1];
        for (int i = 0; i <= nums.length - k; i++) {
            minDiff = Math.min(minDiff, nums[i + k - 1] - nums[i]);
        }
        return minDiff;
    }
}