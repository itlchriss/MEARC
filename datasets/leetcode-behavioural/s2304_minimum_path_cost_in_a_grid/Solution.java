package g2301_2400.s2304_minimum_path_cost_in_a_grid;

// #Medium #Array #Dynamic_Programming #Matrix #2022_06_16_Time_6_ms_(99.18%)_Space_65_MB_(98.71%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input grid must be a 2D integer matrix.*);
//@ ensures(*2. The input moveCost must be a 2D integer matrix.*);
//@ ensures(*3. The dimensions of the input grid and moveCost must be consistent.*);
//@ ensures(*4. The input grid must have distinct integers from 0 to m * n - 1.*);
//@ ensures(*5. The input moveCost must have a size of (m * n) x n.*);
//@ ensures(*6. The values in the input moveCost must be positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns an integer representing the minimum cost of a path.*);
//@ ensures(*2. The minimum cost of a path is the sum of the values of cells visited plus the sum of costs of all the moves made.*);
//@ ensures(*3. The path starts from any cell in the first row and ends at any cell in the last row.*);
//@ ensures(*4. The method ignores the cost of moving from cells in the last row of the grid.*);
//@ ensures(*5. The method returns the minimum cost of a path that satisfies the given conditions.*);
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        System.arraycopy(grid[m - 1], 0, dp[m - 1], 0, n);
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    min = Math.min(min, grid[i][j] + moveCost[grid[i][j]][k] + dp[i + 1][k]);
                }
                dp[i][j] = min;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int s : dp[0]) {
            min = Math.min(min, s);
        }
        return min;
    }
}