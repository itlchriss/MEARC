package g1501_1600.s1572_matrix_diagonal_sum;

// #Easy #Array #Matrix #Programming_Skills_I_Day_7_Array #Udemy_2D_Arrays/Matrix
// #2023_09_03_Time_0_ms_(100.00%)_Space_43.9_MB_(57.91%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is a square matrix.*);
//@ ensures(*The length of `mat` is equal to the length of each row in `mat`.*);
//@ ensures(*The length of `mat` is greater than or equal to 1.*);
//@ ensures(*The elements in `mat` are integers.*);
//@ ensures(*The elements in `mat` are between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the sum of the matrix diagonals.*);
//@ ensures(*The sum includes the elements on the primary diagonal and the elements on the secondary diagonal that are not part of the primary diagonal.*);
//@ ensures(*Each element on the primary diagonal is only counted once.*);
    public int diagonalSum(int[][] mat) {
        int len = mat.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += mat[i][i] + mat[i][len - 1 - i];
        }
        if (len % 2 != 0) {
            sum -= mat[len / 2][len / 2];
        }
        return sum;
    }
}