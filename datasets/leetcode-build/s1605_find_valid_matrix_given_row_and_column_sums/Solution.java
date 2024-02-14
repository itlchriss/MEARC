package g1601_1700.s1605_find_valid_matrix_given_row_and_column_sums;

// #Medium #Array #Greedy #Matrix #2022_04_11_Time_2_ms_(98.45%)_Space_50.4_MB_(89.85%)

public class Solution {
	//@ requires(*The lengths of `rowSum` and `colSum` arrays are equal.*);
	//@ requires(*The lengths of `rowSum` and `colSum` arrays are not greater than 500.*);
	//@ requires(*The elements of `rowSum` and `colSum` arrays are non-negative integers.*);
	//@ requires(*The sum of all elements in `rowSum` array is equal to the sum of all elements in `colSum` array.*);
	//@ ensures(*The returned matrix is a 2D array.*);
	//@ ensures(*The dimensions of the returned matrix are `rowSum.length x colSum.length`.*);
	//@ ensures(*The sum of each row in the returned matrix is equal to the corresponding element in `rowSum` array.*);
	//@ ensures(*The sum of each column in the returned matrix is equal to the corresponding element in `colSum` array.*);
	//@ ensures(*All elements in the returned matrix are non-negative integers.*);
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int[][] ans = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                if (rowSum[i] != 0 && colSum[j] != 0) {
                    ans[i][j] = Math.min(rowSum[i], colSum[j]);
                    rowSum[i] -= ans[i][j];
                    colSum[j] -= ans[i][j];
                }
            }
        }
        return ans;
    }
}