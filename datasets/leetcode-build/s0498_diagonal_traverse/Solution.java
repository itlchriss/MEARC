package g0401_0500.s0498_diagonal_traverse;

// #Medium #Array #Matrix #Simulation #2022_07_24_Time_4_ms_(59.46%)_Space_55.6_MB_(10.90%)

public class Solution {
	//@ requires(*1. The input matrix `mat` is not null.*);
	//@ requires(*2. The input matrix `mat` is a valid 2D matrix.*);
	//@ requires(*3. The input matrix `mat` has at least one row and one column.*);
	//@ ensures(*1. The output array `result` is not null.*);
	//@ ensures(*2. The length of the output array `result` is equal to the total number of elements in the input matrix `mat`.*);
	//@ ensures(*3. The elements in the output array `result` are in diagonal order, starting from the top-left element and moving towards the bottom-right element.*);
	//@ ensures(*4. The elements in the output array `result` are in the same order as they would be if we traverse the input matrix `mat` in a diagonal manner.*);
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] output = new int[m * n];
        int idx = 0;
        for (int diag = 0; diag <= m + n - 2; ++diag) {
            if (diag % 2 == 0) {
                for (int k = Math.max(0, diag - m + 1); k <= Math.min(diag, n - 1); ++k) {
                    output[idx++] = mat[diag - k][k];
                }
            } else {
                for (int k = Math.max(0, diag - n + 1); k <= Math.min(diag, m - 1); ++k) {
                    output[idx++] = mat[k][diag - k];
                }
            }
        }
        return output;
    }
}