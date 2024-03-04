package g0001_0100.s0087_scramble_string;

// #Hard #String #Dynamic_Programming #2022_06_20_Time_16_ms_(70.62%)_Space_43.8_MB_(77.39%)

public class Solution {
//@ ensures(*If the length of the string parameter `s1` is 1, the boolean result is true.*);
//@ ensures(*If the length of the string parameter `s1` is greater than 1, the string parameter `s1` is split into two non-empty substrings at a random index, denoted as `x` and `y`.*);
//@ ensures(*The two substrings `x` and `y` of the string parameter `s1` are randomly decided to be swapped or kept in the same order.*);
//@ ensures(*The algorithm is recursively applied on each of the two substrings `x` and `y`.*);
//@ ensures(*If the string parameter `s2` is a scrambled string of the string parameter `s1`, the boolean result is true.*);
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    if (len == 1) {
                        dp[i][j][len] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        for (int k = 1; k < len && !dp[i][j][len]; k++) {
                            dp[i][j][len] =
                                    (dp[i][j][k] && dp[i + k][j + k][len - k])
                                            || (dp[i][j + len - k][k] && dp[i + k][j][len - k]);
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}