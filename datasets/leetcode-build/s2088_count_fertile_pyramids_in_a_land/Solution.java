package g2001_2100.s2088_count_fertile_pyramids_in_a_land;

// #Hard #Array #Dynamic_Programming #Matrix #2022_05_27_Time_12_ms_(83.56%)_Space_75.5_MB_(41.09%)

public class Solution {
	//@ requires(*The input grid is a rectangular binary matrix with dimensions m x n.*);
	//@ requires(*The grid is 0-indexed.*);
	//@ requires(*The grid represents the farmland, where each cell is either fertile (1) or barren (0).*);
	//@ requires(*All cells outside the grid are considered barren.*);
	//@ ensures(*The method returns the total number of pyramidal and inverse pyramidal plots found in the grid.*);
	//@ ensures(*A pyramidal plot is a set of cells that:*);
	//@ ensures(*- Contains more than 1 cell.*);
	//@ ensures(*- All cells in the plot are fertile (1).*);
	//@ ensures(*- The apex of the pyramid is the topmost cell of the pyramid.*);
	//@ ensures(*- The height of the pyramid is the number of rows it covers.*);
	//@ ensures(*- The plot comprises cells (i, j) where r <= i <= r + h - 1 and c - (i - r) <= j <= c + (i - r), where (r, c) is the apex of the pyramid and h is its height.*);
	//@ ensures(*An inverse pyramidal plot is a set of cells that:*);
	//@ ensures(*- Contains more than 1 cell.*);
	//@ ensures(*- All cells in the plot are fertile (1).*);
	//@ ensures(*- The apex of the inverse pyramid is the bottommost cell of the inverse pyramid.*);
	//@ ensures(*- The height of the inverse pyramid is the number of rows it covers.*);
	//@ ensures(*- The plot comprises cells (i, j) where r - h + 1 <= i <= r and c - (r - i) <= j <= c + (r - i), where (r, c) is the apex of the inverse pyramid and h is its height.*);
    public int countPyramids(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rev = new int[m][n];
        for (int i = 0; i < m; ++i) {
            System.arraycopy(grid[i], 0, rev[m - i - 1], 0, n);
        }
        return cal(grid) + cal(rev);
    }

    private int cal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 1; i < m; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (0 != grid[i][j]) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                if (0 == cnt || 0 == j) {
                    continue;
                }
                grid[i][j] = Math.min(grid[i - 1][j - 1] + 1, (cnt + 1) >> 1);
                res += grid[i][j] - 1;
            }
        }
        return res;
    }
}