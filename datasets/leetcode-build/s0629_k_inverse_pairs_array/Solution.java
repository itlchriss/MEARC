package g0601_0700.s0629_k_inverse_pairs_array;

// #Hard #Dynamic_Programming #2022_03_21_Time_19_ms_(94.44%)_Space_41.1_MB_(94.44%)

public class Solution {
	//@ requires(*The input integers `n` and `k` must be greater than or equal to 1.*);
	//@ requires(*The input integer `n` must be less than or equal to 1000.*);
	//@ requires(*The input integer `k` must be less than or equal to 1000.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned integer value is the number of different arrays consisting of numbers from 1 to `n` that have exactly `k` inverse pairs.*);
	//@ ensures(*The returned integer value is modulo 10^9 + 7.*);
    public int kInversePairs(int n, int k) {
        k = Math.min(k, n * (n - 1) / 2 - k);
        if (k < 0) {
            return 0;
        }
        int[] dp = new int[k + 1];
        int[] dp1 = new int[k + 1];
        dp[0] = 1;
        dp1[0] = 1;
        int mod = 1_000_000_007;
        for (int i = 1; i <= n; i++) {
            int[] temp = dp;
            dp = dp1;
            dp1 = temp;
            for (int j = 1, m = Math.min(k, i * (i - 1) / 2); j <= m; j++) {
                dp[j] = (dp1[j] + dp[j - 1] - (j >= i ? dp1[j - i] : 0)) % mod;
                if (dp[j] < 0) {
                    dp[j] += mod;
                }
            }
        }
        return dp[k];
    }
}