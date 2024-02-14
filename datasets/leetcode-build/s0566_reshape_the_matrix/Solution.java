package g0501_0600.s0566_reshape_the_matrix;

// #Easy #Array #Matrix #Simulation #Data_Structure_I_Day_4_Array #Programming_Skills_I_Day_7_Array
// #2022_08_10_Time_1_ms_(90.08%)_Space_51.4_MB_(11.21%)

public class Solution {
	//@ requires(*1. The input matrix `mat` must not be null.*);
	//@ requires(*2. The number of rows `r` and the number of columns `c` must be positive integers.*);
	//@ requires(*3. The number of elements in the input matrix `mat` must be equal to `r * c`.*);
	//@ ensures(*1. If the reshape operation is possible and legal, the method should return a new reshaped matrix with dimensions `r x c`.*);
	//@ ensures(*2. The elements in the reshaped matrix should be filled with all the elements of the original matrix `mat` in the same row-traversing order as they were.*);
	//@ ensures(*3. If the reshape operation is not possible or legal, the method should return the original matrix `mat`.*);
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if ((mat.length * mat[0].length) != r * c) {
            return mat;
        }
        int p = 0;
        int[] flatArr = new int[mat.length * mat[0].length];
        for (int[] ints : mat) {
            for (int anInt : ints) {
                flatArr[p++] = anInt;
            }
        }
        int[][] ansMat = new int[r][c];
        int k = 0;
        for (int i = 0; i < ansMat.length; i++) {
            for (int j = 0; j < ansMat[i].length; j++) {
                ansMat[i][j] = flatArr[k++];
            }
        }
        return ansMat;
    }
}