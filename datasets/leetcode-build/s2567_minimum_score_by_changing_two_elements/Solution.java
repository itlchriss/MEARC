package g2501_2600.s2567_minimum_score_by_changing_two_elements;

// #Medium #Array #Sorting #Greedy #2023_08_21_Time_15_ms_(98.21%)_Space_55.1_MB_(23.21%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is at least - Each element in `nums` is a positive integer.*);
	//@ ensures(*The method returns an integer representing the minimum possible score after changing the value of at most two elements in `nums`.*);
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length - 1;
        int d1 = (nums[n] - nums[2]);
        int d2 = (nums[n - 2] - nums[0]);
        int d3 = (nums[n - 1] - nums[1]);
        return Math.min(d1, Math.min(d2, d3));
    }
}