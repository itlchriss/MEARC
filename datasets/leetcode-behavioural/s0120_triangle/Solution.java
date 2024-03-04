package g0101_0200.s0120_triangle;

// #Medium #Array #Dynamic_Programming #Algorithm_I_Day_12_Dynamic_Programming
// #Dynamic_Programming_I_Day_13 #Udemy_Dynamic_Programming
// #2022_06_23_Time_2_ms_(94.63%)_Space_44.2_MB_(36.02%)

import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*The integer collection parameter `triangle` must not be null.*);
//@ ensures(*The integer result is the minimum path sum from the top to the bottom of the triangle.*);
//@ ensures(*The minimum path sum is calculated by moving to an adjacent number of the row below at each step.*);
//@ ensures(*The minimum path sum is the sum of the numbers from the top to the bottom that results in the smallest possible sum.*);
//@ ensures(*The minimum path sum is calculated by choosing the smaller of the two adjacent numbers at each step.*);
//@ ensures(*The minimum path sum is the sum of the numbers that form the path with the smallest sum from the top to the bottom of the triangle.*);
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int[] temp : dp) {
            Arrays.fill(temp, -10001);
        }
        return dfs(triangle, dp, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int[][] dp, int row, int col) {
        if (row >= triangle.size()) {
            return 0;
        }
        if (dp[row][col] != -10001) {
            return dp[row][col];
        }
        int sum =
                triangle.get(row).get(col)
                        + Math.min(
                                dfs(triangle, dp, row + 1, col),
                                dfs(triangle, dp, row + 1, col + 1));
        dp[row][col] = sum;
        return sum;
    }
}