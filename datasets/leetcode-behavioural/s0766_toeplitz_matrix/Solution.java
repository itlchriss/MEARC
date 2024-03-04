package g0701_0800.s0766_toeplitz_matrix;

// #Easy #Array #Matrix #2022_03_26_Time_1_ms_(83.45%)_Space_45.8_MB_(49.31%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix is not null.*);
//@ ensures(*2. The input matrix has at least one row and one column.*);
//@ ensures(*3. The input matrix has dimensions m x n, where m is the number of rows and n is the number of columns.*);
//@ ensures(*4. The elements of the input matrix are integers.*);
//@ ensures(*5. The elements of the input matrix are within the range of 0 to 99.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns a boolean value indicating whether the matrix is Toeplitz.*);
//@ ensures(*2. If the matrix is Toeplitz, the method returns true.*);
//@ ensures(*3. If the matrix is not Toeplitz, the method returns false.*);
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;
        int sameVal = matrix[i][j];
        while (++i < m && ++j < n) {
            if (matrix[i][j] != sameVal) {
                return false;
            }
        }

        for (i = 1, j = 0; i < m; i++) {
            int tmpI = i;
            int tmpJ = j;
            sameVal = matrix[i][j];
            while (++tmpI < m && ++tmpJ < n) {
                if (matrix[tmpI][tmpJ] != sameVal) {
                    return false;
                }
            }
        }
        for (i = 0, j = 1; j < n; j++) {
            int tmpJ = j;
            int tmpI = i;
            sameVal = matrix[tmpI][tmpJ];
            while (++tmpI < m && ++tmpJ < n) {
                if (matrix[tmpI][tmpJ] != sameVal) {
                    return false;
                }
            }
        }
        return true;
    }
}