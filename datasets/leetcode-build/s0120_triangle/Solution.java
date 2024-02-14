package g0101_0200.s0120_triangle;

// #Medium #Array #Dynamic_Programming #Algorithm_I_Day_12_Dynamic_Programming
// #Dynamic_Programming_I_Day_13 #Udemy_Dynamic_Programming
// #2022_06_23_Time_2_ms_(94.63%)_Space_44.2_MB_(36.02%)

import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input `triangle` is not null.*);
	//@ requires(*The input `triangle` is a valid triangle array.*);
	//@ requires(*The length of `triangle` is greater than or equal to 1.*);
	//@ requires(*The length of `triangle[0]` is equal to 1.*);
	//@ requires(*The length of `triangle[i]` is equal to `triangle[i-1] + 1` for all `i` from 1 to `triangle.length - 1`.*);
	//@ requires(*The values in `triangle` are within the range of -10^4 to 10^4.*);
	//@ ensures(*The method returns an integer representing the minimum path sum from top to bottom of the triangle.*);
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