package g2901_3000.s2965_find_missing_and_repeated_values;

// #Easy #Array #Hash_Table #Math #Matrix #2024_01_16_Time_1_ms_(100.00%)_Space_45.4_MB_(17.99%)

public class Solution {
	//@ requires(*The input grid is a 2D integer matrix.*);
	//@ requires(*The size of the grid is n * n.*);
	//@ requires(*The values in the grid are in the range [1, n^2].*);
	//@ requires(*Each integer appears exactly once in the grid, except for two integers a and b.*);
	//@ requires(*The integer a appears twice in the grid.*);
	//@ requires(*The integer b is missing from the grid.*);
	//@ requires(*The grid follows the constraints specified.*);
	//@ ensures(*The method returns a 0-indexed integer array of size 2.*);
	//@ ensures(*The first element of the array (ans[0]) is equal to a.*);
	//@ ensures(*The second element of the array (ans[1]) is equal to b.*);
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int nSquare = grid.length * grid.length;
        int sum = nSquare * (nSquare + 1) / 2;
        boolean[] found = new boolean[nSquare + 1];
        int repeated = 1;
        for (int[] row : grid) {
            for (int n : row) {
                sum -= n;
                if (found[n]) {
                    repeated = n;
                }
                found[n] = true;
            }
        }
        return new int[] {repeated, sum + repeated};
    }
}