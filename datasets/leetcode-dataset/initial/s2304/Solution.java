package g2301_2400.s2304_minimum_path_cost_in_a_grid;

// #Medium #Array #Dynamic_Programming #Matrix #2022_06_16_Time_6_ms_(99.18%)_Space_65_MB_(98.71%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*The cost of a path in `grid` is the **sum** of all values of cells visited plus the **sum** of costs of all the moves made. Return _the **minimum** cost of a path that starts from any cell in the **first** row and ends at any cell in the **last** row._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
