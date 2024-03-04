package g0001_0100.s0063_unique_paths_ii;

// #Medium #Array #Dynamic_Programming #Matrix #Dynamic_Programming_I_Day_15
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.6_MB_(73.18%)

public class Solution {
//@ ensures(*The integer 2D array parameter `obstacleGrid` must not be null.*);
//@ ensures(*The integer result is the number of unique paths to reach the bottom-right corner of the grid.*);
//@ ensures(*If there is an obstacle marked as `1` in the grid, the robot cannot pass through that cell.*);
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // if start point has obstacle, there's no path
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = 0;
            } else {
                obstacleGrid[i][0] = obstacleGrid[i - 1][0];
            }
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                obstacleGrid[0][j] = 0;
            } else {
                obstacleGrid[0][j] = obstacleGrid[0][j - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
}