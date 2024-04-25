package g0001_0100.s0062_unique_paths;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Dynamic_Programming #Math
// #Combinatorics #Algorithm_II_Day_13_Dynamic_Programming #Dynamic_Programming_I_Day_15
// #Level_1_Day_11_Dynamic_Programming #Big_O_Time_O(m*n)_Space_O(m*n)
// #2023_08_11_Time_0_ms_(100.00%)_Space_39.2_MB_(67.74%)

public class Solution {
//@ ensures(\result == 1 ==> (m == 1 || n == 1));
//@ requires(m >= 1 && n >= 1 && m <= 100 && n <= 100);
//@ ensures(\result == 2 ==> (m == 2 && n == 2));
//@ ensures(\result >= 0 && \result <= 2 * 10^9);
//@ ensures(\result == 28 ==> (m == 3 && n == 7) || (m == 7 && n == 3));
//@ ensures(\result == 3 ==> (m == 3 && n == 2));
//@ ensures(\result == 6 ==> (m == 3 && n == 3));
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i != m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
