package g1001_1100.s1039_minimum_score_triangulation_of_polygon;

// #Medium #Array #Dynamic_Programming #2022_02_27_Time_6_ms_(38.60%)_Space_42.2_MB_(8.48%)

import java.util.Arrays;

public class Solution {
    private int[][] dp = new int[101][101];
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `values` is not null.*);
//@ ensures(*The length of the input array `values` is greater than or equal to - The length of the input array `values` is less than or equal to - Each element in the input array `values` is an integer.*);
//@ ensures(*Each element in the input array `values` is greater than or equal to - Each element in the input array `values` is less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the smallest possible total score achieved with some triangulation of the polygon.*);

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return util(values, 1, n - 1);
    }

    private int util(int[] values, int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int temp =
                    util(values, i, k)
                            + util(values, k + 1, j)
                            + (values[i - 1] * values[k] * values[j]);

            ans = Math.min(ans, temp);
            dp[i][j] = ans;
        }
        return dp[i][j];
    }
}