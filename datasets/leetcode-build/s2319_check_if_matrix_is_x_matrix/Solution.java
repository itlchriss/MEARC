package g2301_2400.s2319_check_if_matrix_is_x_matrix;

// #Easy #Array #Matrix #2022_06_28_Time_3_ms_(53.58%)_Space_54_MB_(83.44%)

public class Solution {
	//@ requires(*The input `grid` is a 2D integer array.*);
	//@ requires(*The size of `grid` is `n x n`, where `n` is the length of `grid` and `grid[i]`.*);
	//@ requires(*The length of `grid` is equal to the length of `grid[i]`.*);
	//@ requires(*The value of `n` is between 3 and 100 (inclusive).*);
	//@ requires(*The elements of `grid` are non-negative integers between 0 and 10^5 (inclusive).*);
	//@ ensures(*The method returns a boolean value (`true` or `false`).*);
	//@ ensures(*If `grid` is an X-Matrix, the method returns `true`.*);
	//@ ensures(*If `grid` is not an X-Matrix, the method returns `false`.*);
    public boolean checkXMatrix(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == j || i + j == grid.length - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}