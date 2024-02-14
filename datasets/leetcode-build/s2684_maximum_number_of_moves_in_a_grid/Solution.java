package g2601_2700.s2684_maximum_number_of_moves_in_a_grid;

// #Medium #Array #Dynamic_Programming #Matrix #2023_09_19_Time_3_ms_(99.53%)_Space_55.4_MB_(15.49%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input grid must be a 2D array of positive integers.*);
	//@ requires(*The grid must have at least 2 rows and 2 columns.*);
	//@ requires(*The grid must have at most 1000 rows and 1000 columns.*);
	//@ requires(*The total number of cells in the grid must be between 4 and 10^5.*);
	//@ requires(*The values in the grid must be between 1 and 10^6.*);
	//@ ensures(*The method should return an integer representing the maximum number of moves that can be performed.*);
	//@ ensures(*The maximum number of moves should be calculated based on the given rules:*);
	//@ ensures(*- Starting from any cell in the first column, the method should find the maximum number of moves that can be made by moving to cells with strictly bigger values.*);
	//@ ensures(*- The method should return 0 if no moves can be made from any cell in the first column.*);
    public int maxMoves(int[][] grid) {
        int h = grid.length;
        boolean[] dp1 = new boolean[h];
        boolean[] dp2 = new boolean[h];
        int rtn = 0;
        Arrays.fill(dp1, true);
        for (int col = 1; col < grid[0].length; col++) {
            boolean f = false;
            for (int row = 0; row < h; row++) {
                int pr = row - 1;
                int nr = row + 1;
                dp2[row] = false;
                if (pr >= 0 && dp1[pr] && grid[pr][col - 1] < grid[row][col]) {
                    dp2[row] = true;
                    f = true;
                }
                if (nr < h && dp1[nr] && grid[nr][col - 1] < grid[row][col]) {
                    dp2[row] = true;
                    f = true;
                }
                if (dp1[row] && grid[row][col - 1] < grid[row][col]) {
                    dp2[row] = true;
                    f = true;
                }
            }
            boolean[] t = dp1;
            dp1 = dp2;
            dp2 = t;
            if (f) {
                rtn++;
            } else {
                break;
            }
        }
        return rtn;
    }
}