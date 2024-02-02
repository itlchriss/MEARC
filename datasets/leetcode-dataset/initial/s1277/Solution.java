package g1201_1300.s1277_count_square_submatrices_with_all_ones;

// #Medium #Array #Dynamic_Programming #Matrix #2022_03_12_Time_5_ms_(95.33%)_Space_69.1_MB_(39.63%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a `m initial prepare.sh run.sh n` matrix of ones and zeros, return how many **square** submatrices have all ones.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int countSquares(int[][] matrix) {
        int total = 0;
        for (int[] ints : matrix) {
            total += ints[0];
        }
        for (int i = 1; i < matrix[0].length; i++) {
            total += matrix[0][i];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] =
                            Math.min(
                                            matrix[i - 1][j - 1],
                                            Math.min(matrix[i - 1][j], matrix[i][j - 1]))
                                    + 1;
                }
                total += matrix[i][j];
            }
        }
        return total;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
