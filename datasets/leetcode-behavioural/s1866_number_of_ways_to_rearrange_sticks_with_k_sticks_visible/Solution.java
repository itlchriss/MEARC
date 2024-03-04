package g1801_1900.s1866_number_of_ways_to_rearrange_sticks_with_k_sticks_visible;

// #Hard #Dynamic_Programming #Math #Combinatorics
// #2022_05_10_Time_67_ms_(96.33%)_Space_55.1_MB_(80.73%)

import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;
//@ ensures(*Preconditions:*);
//@ ensures(*The value of `n` must be greater than or equal to 1.*);
//@ ensures(*The value of `k` must be greater than or equal to 1.*);
//@ ensures(*The value of `k` must be less than or equal to `n`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the number of arrangements such that exactly `k` sticks are visible from the left.*);
//@ ensures(*The returned value is modulo `10^9 + 7`.*);

    public int rearrangeSticks(int n, int k) {
        if (k > n || k < 1) {
            return 0;
        }
        if (k == n) {
            return 1;
        }
        long[] dp = new long[k + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i + k <= n; i++) {
            long[] dp2 = new long[k + 1];
            for (int j = 1; j <= k; j++) {
                dp2[j] = (dp2[j - 1] + (i + j - 1) * dp[j]) % MOD;
            }
            dp = dp2;
        }
        return (int) dp[k];
    }
}