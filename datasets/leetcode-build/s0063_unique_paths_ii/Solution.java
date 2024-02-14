package g0001_0100.s0063_unique_paths_ii;

// #Medium #Array #Dynamic_Programming #Matrix #Dynamic_Programming_I_Day_15
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.6_MB_(73.18%)

public class Solution {
	//@ requires(*1. The input `obstacleGrid` is a 2D array of integers.*);
	//@ requires(*2. The length of `obstacleGrid` is equal to `m`.*);
	//@ requires(*3. The length of each row in `obstacleGrid` is equal to `n`.*);
	//@ requires(*4. The values in `obstacleGrid` are either 0 or 1.*);
	//@ requires(*5. The robot starts at the top-left corner of the grid.*);
	//@ ensures(*1. The method returns an integer representing the number of unique paths to reach the bottom-right corner of the grid.*);
	//@ ensures(*2. If there is an obstacle at the bottom-right corner, the method returns 0.*);
	//@ ensures(*3. If there are no obstacles, the method returns the total number of unique paths to reach the bottom-right corner.*);
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