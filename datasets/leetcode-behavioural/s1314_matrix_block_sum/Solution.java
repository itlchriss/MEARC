package g1301_1400.s1314_matrix_block_sum;

// #Medium #Array #Matrix #Prefix_Sum #Dynamic_Programming_I_Day_14
// #2022_03_18_Time_5_ms_(67.46%)_Space_49.1_MB_(41.82%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is not null.*);
//@ ensures(*The input matrix `mat` has at least one row and one column.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(*The input integer `k` is less than or equal to the minimum of the number of rows and columns in `mat`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output matrix `answer` is not null.*);
//@ ensures(*The number of rows in the output matrix `answer` is equal to the number of rows in the input matrix `mat`.*);
//@ ensures(*The number of columns in the output matrix `answer` is equal to the number of columns in the input matrix `mat`.*);
//@ ensures(*For each element `answer[i][j]` in the output matrix `answer`, the value is equal to the sum of all elements `mat[r][c]` where `i - k <= r <= i + k`, `j - k <= c <= j + k`, and `(r, c)` is a valid position in the matrix.*);
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] prefixSum = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSum[i][j] =
                        mat[i - 1][j - 1]
                                - prefixSum[i - 1][j - 1]
                                + prefixSum[i - 1][j]
                                + prefixSum[i][j - 1];
            }
        }

        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int iMin = Math.max(i - k, 0);
                int iMax = Math.min(i + k, rows - 1);
                int jMin = Math.max(j - k, 0);
                int jMax = Math.min(j + k, cols - 1);
                result[i][j] =
                        prefixSum[iMin][jMin]
                                + prefixSum[iMax + 1][jMax + 1]
                                - prefixSum[iMax + 1][jMin]
                                - prefixSum[iMin][jMax + 1];
            }
        }
        return result;
    }
}