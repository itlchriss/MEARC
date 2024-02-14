package g1601_1700.s1621_number_of_sets_of_k_non_overlapping_line_segments;

// #Medium #Dynamic_Programming #Math #2022_04_18_Time_16_ms_(94.74%)_Space_39.3_MB_(94.74%)

public class Solution {
	//@ requires(*The value of `n` must be greater than or equal to 2.*);
	//@ requires(*The value of `k` must be greater than or equal to 1 and less than `n`.*);
	//@ ensures(*The method returns an integer value representing the number of ways to draw `k` non-overlapping line segments.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int numberOfSets(int n, int k) {
        if (n - 1 >= k) {
            int[] dp = new int[k];
            int[] sums = new int[k];
            int mod = (int) (1e9 + 7);
            for (int diff = 1; diff < n - k + 1; diff++) {
                dp[0] = ((diff + 1) * diff) >> 1;
                sums[0] = (sums[0] + dp[0]) % mod;
                for (int segments = 2; segments <= k; segments++) {
                    dp[segments - 1] = (sums[segments - 2] + dp[segments - 1]) % mod;
                    sums[segments - 1] = (sums[segments - 1] + dp[segments - 1]) % mod;
                }
            }
            return dp[k - 1];
        } else {
            return 0;
        }
    }
}