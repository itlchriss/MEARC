package g0801_0900.s0813_largest_sum_of_averages;

// #Medium #Array #Dynamic_Programming #2022_03_23_Time_4_ms_(97.01%)_Space_42.5_MB_(18.51%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in `nums` are integers.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ requires(*The value of `k` is less than or equal to the length of `nums`.*);
	//@ ensures(*The output is a double value representing the maximum score.*);
	//@ ensures(*The output is within 10^-6 of the actual answer.*);
	//@ ensures(*The output is the sum of the averages of each subarray in the partition.*);
	//@ ensures(*The partition must use every integer in `nums`.*);
	//@ ensures(*The maximum score is achieved by partitioning `nums` into at most `k` non-empty adjacent subarrays.*);
    public double largestSumOfAverages(int[] nums, int k) {
        return helper(nums, k, 0, new Double[k + 1][nums.length]);
    }

    private double helper(int[] nums, int k, int idx, Double[][] memo) {
        if (memo[k][idx] != null) {
            return memo[k][idx];
        }
        double maxAvg = 0;
        double sum = 0;
        for (int i = idx; i <= nums.length - k; i++) {
            sum += nums[i];
            if (k - 1 > 0) {
                maxAvg = Math.max(maxAvg, (sum / (i - idx + 1)) + helper(nums, k - 1, i + 1, memo));
            } else if (i == nums.length - 1) {
                maxAvg = Math.max(maxAvg, sum / (i - idx + 1));
            }
        }
        memo[k][idx] = maxAvg;
        return maxAvg;
    }
}