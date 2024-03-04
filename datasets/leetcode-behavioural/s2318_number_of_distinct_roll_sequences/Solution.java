package g2301_2400.s2318_number_of_distinct_roll_sequences;

// #Hard #Dynamic_Programming #Memoization #2022_06_26_Time_254_ms_(91.67%)_Space_51.6_MB_(58.33%)

public class Solution {
    private int[][][] memo = new int[10001][7][7];
    private int mod = 1000000007;
    private int[][] m = {
        {1, 2, 3, 4, 5, 6},
        {2, 3, 4, 5, 6},
        {1, 3, 5},
        {1, 2, 4, 5},
        {1, 3, 5},
        {1, 2, 3, 4, 6},
        {1, 5}
    };
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer.*);
//@ ensures(*`n` is greater than or equal to 1 and less than or equal to 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total number of distinct sequences of rolls possible.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int distinctSequences(int n) {
        return dp(n, 0, 0);
    }

    private int dp(int n, int prev, int pprev) {
        if (n == 0) {
            return 1;
        }
        if (memo[n][prev][pprev] != 0) {
            return memo[n][prev][pprev];
        }
        int ans = 0;
        for (int x : m[prev]) {
            if (x != pprev) {
                ans = (ans + dp(n - 1, x, prev)) % mod;
            }
        }
        memo[n][prev][pprev] = ans;
        return ans;
    }
}