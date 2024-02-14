package g2501_2600.s2596_check_knight_tour_configuration;

// #Medium #Array #Depth_First_Search #Breadth_First_Search #Matrix #Simulation
// #2023_08_29_Time_1_ms_(100.00%)_Space_43.4_MB_(42.62%)

public class Solution {
	//@ requires(*1. The input grid must be a square matrix, i.e., the number of rows must be equal to the number of columns.*);
	//@ requires(*2. The size of the grid must be between 3 and 7, inclusive.*);
	//@ requires(*3. The integers in the grid must be unique.*);
	//@ ensures(*1. The method should return a boolean value indicating whether the grid represents a valid configuration of the knight's movements.*);
	//@ ensures(*2. If the grid represents a valid configuration, the method should return true.*);
	//@ ensures(*3. If the grid does not represent a valid configuration, the method should return false.*);
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[] rmove = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] cmove = {1, -1, 1, -1, 2, -2, 2, -2};
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = grid[i][j];
                boolean isPoss = false;
                for (int d = 0; d < 8; d++) {
                    int r = i + rmove[d];
                    int c = j + cmove[d];
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == val + 1) {
                        isPoss = true;
                    }
                }
                if (!isPoss) {
                    cnt++;
                }
                if (cnt > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}