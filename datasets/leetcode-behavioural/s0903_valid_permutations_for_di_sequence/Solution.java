package g0901_1000.s0903_valid_permutations_for_di_sequence;

// #Hard #Dynamic_Programming #2022_03_28_Time_3_ms_(74.00%)_Space_42.9_MB_(46.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is greater than or equal to 1.*);
//@ ensures(*The characters in `s` are either 'I' or 'D'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the number of valid permutations of `n + 1` integers.*);
//@ ensures(*The returned value is modulo `10^9 + 7`.*);
    public int numPermsDISequence(String s) {
        int n = s.length();
        int mod = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < n; i++) {
            int cur = 0;
            if (s.charAt(i) == 'I') {
                for (int j = 0; j < n - i; j++) {
                    cur = (cur + dp[i][j]) % mod;
                    dp[i + 1][j] = cur;
                }
            } else {
                for (int j = n - i - 1; j >= 0; j--) {
                    cur = (cur + dp[i][j + 1]) % mod;
                    dp[i + 1][j] = cur;
                }
            }
        }
        return dp[n][0];
    }
}