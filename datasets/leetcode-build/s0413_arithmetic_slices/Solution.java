package g0401_0500.s0413_arithmetic_slices;

// #Medium #Array #Dynamic_Programming #Algorithm_II_Day_14_Dynamic_Programming
// #Dynamic_Programming_I_Day_10 #2022_07_16_Time_0_ms_(100.00%)_Space_42.6_MB_(14.82%)

public class Solution {
	//@ requires(*1. The input array `nums` must not be null.*);
	//@ requires(*2. The length of the input array `nums` must be at least 3.*);
	//@ ensures(*1. The method returns an integer representing the number of arithmetic subarrays in the input array `nums`.*);
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