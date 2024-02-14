package g0001_0100.s0059_spiral_matrix_ii;

// #Medium #Array #Matrix #Simulation #Data_Structure_II_Day_3_Array
// #2023_08_11_Time_0_ms_(100.00%)_Space_40_MB_(98.82%)

public class Solution {
	//@ requires(*The input `n` must be a positive integer.*);
	//@ requires(*The value of `n` must be between 1 and 20 (inclusive).*);
	//@ ensures(*The output `matrix` must be a 2D array of integers.*);
	//@ ensures(*The dimensions of the `matrix` must be `n x n`.*);
	//@ ensures(*The elements of the `matrix` must be filled in spiral order, starting from 1 and incrementing by 1 for each element.*);
	//@ ensures(*The `matrix` must be filled in a clockwise spiral pattern.*);
	//@ ensures(*The `matrix` must be filled row by row, starting from the top-left corner and moving towards the right.*);
	//@ ensures(*The `matrix` must be filled in a spiral pattern until all elements from 1 to n^2 are filled.*);
	//@ ensures(*The `matrix` must be returned as the output of the method.*);
    public int[][] generateMatrix(int n) {
        int num = 1;
        int rStart = 0;
        int rEnd = n - 1;
        int cStart = 0;
        int cEnd = n - 1;
        int[][] spiral = new int[n][n];
        while (rStart <= rEnd && cStart <= cEnd) {
            for (int k = cStart; k <= cEnd; k++) {
                spiral[rStart][k] = num++;
            }
            rStart++;
            for (int k = rStart; k <= rEnd; k++) {
                spiral[k][cEnd] = num++;
            }
            cEnd--;
            if (rStart <= rEnd) {
                for (int k = cEnd; k >= cStart; k--) {
                    spiral[rEnd][k] = num++;
                }
            }
            rEnd--;
            if (cStart <= cEnd) {
                for (int k = rEnd; k >= rStart; k--) {
                    spiral[k][cStart] = num++;
                }
            }
            cStart++;
        }
        return spiral;
    }
}