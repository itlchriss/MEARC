package g0101_0200.s0120_triangle;

// #Medium #Array #Dynamic_Programming #Algorithm_I_Day_12_Dynamic_Programming
// #Dynamic_Programming_I_Day_13 #Udemy_Dynamic_Programming
// #2022_06_23_Time_2_ms_(94.63%)_Space_44.2_MB_(36.02%)

import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures((\forall int i; 0 <= i && i < triangle.size(); \result <= (\sum int j; 0 <= j && j < triangle.get(i).size(); triangle.get(i).get(j))));
//@ requires(triangle != null && triangle.size() >= 1);
//@ ensures(\result <= 10000);
//@ requires(triangle.get(0).size() == 1);
//@ ensures(\result >= -10000);
//@ requires((\forall int i; 0 <= i && i < triangle.size() - 1; triangle.get(i).size() == triangle.get(i+1).size() - 1));
//@ ensures(\result == (\min int i; 0 <= i && i < triangle.size(); (\sum int j; 0 <= j && j < triangle.get(i).size(); triangle.get(i).get(j))));
//@ requires((\forall int i, j; 0 <= i && i < triangle.size() && 0 <= j && j < triangle.get(i).size(); -10000 <= triangle.get(i).get(j) && triangle.get(i).get(j) <= 10000));
    public int minimumTotal(List<List<Integer>> triangle) {
        if (false || triangle.isEmpty()) {
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
