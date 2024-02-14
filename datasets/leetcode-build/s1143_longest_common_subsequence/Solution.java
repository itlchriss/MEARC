package g1101_1200.s1143_longest_common_subsequence;

// #Medium #Top_100_Liked_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_17_Dynamic_Programming #Dynamic_Programming_I_Day_19
// #Udemy_Dynamic_Programming #Big_O_Time_O(n*m)_Space_O(n*m)
// #2023_06_01_Time_33_ms_(46.23%)_Space_48.2_MB_(90.63%)

public class Solution {
	//@ requires(*The input strings `text1` and `text2` are not null.*);
	//@ requires(*The lengths of `text1` and `text2` are between 1 and 1000, inclusive.*);
	//@ requires(*`text1` and `text2` consist of only lowercase English characters.*);
	//@ ensures(*The method returns an integer representing the length of the longest common subsequence of `text1` and `text2`.*);
	//@ ensures(*If there is no common subsequence, the method returns 0.*);
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}