package g2701_2800.s2735_collecting_chocolates;

// #Medium #Array #Enumeration #2023_09_22_Time_24_ms_(96.97%)_Space_43.5_MB_(92.12%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` must be positive integers.*);
	//@ requires(*The input integer `x` must be a positive integer.*);
	//@ ensures(*The output must be a long integer representing the minimum cost to collect all types of chocolates.*);
	//@ ensures(*The output must be greater than or equal to 0.*);
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] dp = new int[n];
        long res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            res += nums[i];
        }
        for (int i = 1; i < n; i++) {
            long sum = (long) i * x;
            for (int j = 0; j < n; j++) {
                int currIndex = (j + i >= n) ? j + i - n : j + i;
                dp[j] = Math.min(dp[j], nums[currIndex]);
                sum += dp[j];
            }
            res = Math.min(res, sum);
        }
        return res;
    }
}