package g0401_0500.s0413_arithmetic_slices;

// #Medium #Array #Dynamic_Programming #Algorithm_II_Day_14_Dynamic_Programming
// #Dynamic_Programming_I_Day_10 #2022_07_16_Time_0_ms_(100.00%)_Space_42.6_MB_(14.82%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the number of arithmetic subarrays of the integer array parameter `nums`.*);
//@ ensures(*An arithmetic subarray is a contiguous subsequence of the integer array parameter `nums` with at least three elements where the difference between any two consecutive elements is the same.*);
    public int numberOfArithmeticSlices(int[] nums) {
        int sum = 0;
        int curr = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                curr++;
                sum += curr;
            } else {
                curr = 0;
            }
        }
        return sum;
    }
}