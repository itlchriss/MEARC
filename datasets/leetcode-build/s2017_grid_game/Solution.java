package g2001_2100.s2017_grid_game;

// #Medium #Array #Matrix #Prefix_Sum #2022_05_24_Time_10_ms_(41.46%)_Space_95.2_MB_(32.32%)

public class Solution {
	//@ requires(*The input grid must be a 2D array of size 2 x n.*);
	//@ requires(*The grid must be 0-indexed.*);
	//@ requires(*The grid must contain positive integers representing the number of points at each position.*);
	//@ requires(*The number of columns in the grid must be equal to n.*);
	//@ requires(*The number of columns, n, must be greater than or equal to 1 and less than or equal to 5 * 10^4.*);
	//@ requires(*The number of rows in the grid must be equal to 2.*);
	//@ requires(*The number of points at each position in the grid must be greater than or equal to 1 and less than or equal to 10^5.*);
	//@ ensures(*The method must return a long value representing the number of points collected by the second robot.*);
	//@ ensures(*The cells visited by the first robot must be set to 0.*);
	//@ ensures(*The optimal path taken by the first robot must be shown in red.*);
	//@ ensures(*The optimal path taken by the second robot must be shown in blue.*);
	//@ ensures(*The number of points collected by the second robot must be equal to the sum of the points at the positions visited by the second robot.*);
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[] cum0 = new long[n + 1];
        long[] cum1 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            cum0[i + 1] = cum0[i] + grid[0][i];
            cum1[i + 1] = cum1[i] + grid[1][i];
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, Math.max(cum0[n] - cum0[i + 1], cum1[i]));
        }
        return ans;
    }
}