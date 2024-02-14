package g0701_0800.s0730_count_different_palindromic_subsequences;

// #Hard #String #Dynamic_Programming #2022_03_25_Time_45_ms_(92.84%)_Space_56.3_MB_(34.99%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*3. The characters in the input string `s` are limited to the characters 'a', 'b', 'c', or 'd'.*);
	//@ ensures(*1. The method returns an integer value representing the number of different non-empty palindromic subsequences in the input string `s`.*);
	//@ ensures(*2. The returned value is modulo 10^9 + 7.*);
    public int countPalindromicSubsequences(String s) {
        int big = 1000000007;
        int len = s.length();
        if (len < 2) {
            return len;
        }
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int deta = 1;
            dp[i][i] = 1;
            int l2 = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(j) == c) {
                    if (l2 < 0) {
                        l2 = j;
                        deta = dp[j + 1][i - 1] + 1;
                    } else {
                        deta = dp[j + 1][i - 1] - dp[j + 1][l2 - 1];
                    }
                    deta = deal(deta, big);
                }
                dp[j][i] = deal(dp[j][i - 1] + deta, big);
            }
        }
        return deal(dp[0][len - 1], big);
    }

    private int deal(int x, int big) {
        x %= big;
        if (x < 0) {
            x += big;
        }
        return x;
    }
}