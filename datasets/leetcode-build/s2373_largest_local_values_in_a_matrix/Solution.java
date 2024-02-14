package g2301_2400.s2373_largest_local_values_in_a_matrix;

// #Easy #Array #Matrix #2022_08_19_Time_2_ms_(99.97%)_Space_43.1_MB_(96.65%)

public class Solution {
	//@ requires(*The input `grid` is a square matrix of size `n x n`.*);
	//@ requires(*The size of `grid` is at least 3x3 (i.e., `n >= 3`).*);
	//@ requires(*The values in `grid` are integers.*);
	//@ requires(*The values in `grid` are within the range of 1 to 100 (inclusive).*);
	//@ ensures(*The output `maxLocal` is a matrix of size `(n - 2) x (n - 2)`.*);
	//@ ensures(*Each element `maxLocal[i][j]` is equal to the largest value of the 3x3 matrix in `grid` centered around row `i + 1` and column `j + 1`.*);
	//@ ensures(*The values in `maxLocal` are integers.*);
	//@ ensures(*The values in `maxLocal` are within the range of 1 to 100 (inclusive).*);
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int p = i; p < i + 3; p++) {
                    for (int q = j; q < j + 3; q++) {
                        res[i][j] = Math.max(res[i][j], grid[p][q]);
                    }
                }
            }
        }
        return res;
    }
}