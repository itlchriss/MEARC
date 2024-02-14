package g2401_2500.s2482_difference_between_ones_and_zeros_in_row_and_column;

// #Medium #Array #Matrix #Simulation #2023_01_25_Time_9_ms_(94.05%)_Space_76.3_MB_(95.36%)

public class Solution {
	//@ requires(*The input `grid` is a 2D array of integers.*);
	//@ requires(*The length of `grid` is equal to `m`.*);
	//@ requires(*The length of each row in `grid` is equal to `n`.*);
	//@ requires(*The values in `grid` are either 0 or 1.*);
	//@ ensures(*The output is a 2D array of integers.*);
	//@ ensures(*The length of the output array is equal to `m`.*);
	//@ ensures(*The length of each row in the output array is equal to `n`.*);
	//@ ensures(*The values in the output array are calculated based on the formula `onesRow[i] + onesCol[j] - zerosRow[i] - zerosCol[j]`, where `onesRow[i]` is the number of ones in the `i`th row, `onesCol[j]` is the number of ones in the `j`th column, `zerosRow[i]` is the number of zeros in the `i`th row, and `zerosCol[j]` is the number of zeros in the `j`th column.*);
    public int[][] onesMinusZeros(int[][] grid) {
        int[] rowOne = new int[grid.length];
        int[] colOne = new int[grid[0].length];
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    c++;
                }
            }
            rowOne[i] = c;
        }
        for (int i = 0; i < n; i++) {
            int c = 0;
            for (int[] ints : grid) {
                if (ints[i] == 1) {
                    c++;
                }
            }
            colOne[i] = c;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = rowOne[i] + colOne[j] - (m - rowOne[i]) - (n - colOne[j]);
            }
        }
        return grid;
    }
}