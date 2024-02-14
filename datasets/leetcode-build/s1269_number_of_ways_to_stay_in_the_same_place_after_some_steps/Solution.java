package g1201_1300.s1269_number_of_ways_to_stay_in_the_same_place_after_some_steps;

// #Hard #Dynamic_Programming #2022_03_14_Time_12_ms_(89.11%)_Space_42.7_MB_(67.85%)

import java.util.Arrays;

public class Solution {
    private int n;
    private int[][] dp;

    private int dfs(int i, int st) {
        if (i < 0 || i >= n) {
            return 0;
        }
        if (st == 0) {
            return i == 0 ? 1 : 0;
        }
        if (dp[i][st] == -1) {
            int mod = 1000000007;
            dp[i][st] = ((dfs(i + 1, st - 1) + dfs(i, st - 1)) % mod + dfs(i - 1, st - 1)) % mod;
        }
        return dp[i][st];
    }
	//@ requires(*The input `steps` is a positive integer.*);
	//@ requires(*The input `arrLen` is a positive integer.*);
	//@ requires(*The value of `steps` is less than or equal to 500.*);
	//@ requires(*The value of `arrLen` is less than or equal to 1,000,000.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the number of ways such that the pointer is still at index 0 after exactly `steps` steps.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int numWays(int steps, int arrLen) {
        n = Math.min(steps, arrLen);
        dp = new int[n][steps + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dfs(0, steps);
        return dp[0][steps];
    }
}