package g1501_1600.s1582_special_positions_in_a_binary_matrix;

// #Easy #Array #Matrix #2022_04_11_Time_2_ms_(82.12%)_Space_52.5_MB_(17.36%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is not null.*);
//@ ensures(*The input matrix `mat` has at least one row and one column.*);
//@ ensures(*The input matrix `mat` has a valid size, where `m` is the number of rows and `n` is the number of columns.*);
//@ ensures(*The elements of the input matrix `mat` are either 0 or *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of special positions in the matrix `mat`.*);
//@ ensures(*The returned value is greater than or equal to The returned value is less than or equal to the total number of positions in the matrix `mat`.*);
//@ ensures(*The matrix `mat` is not modified by the method.*);
    public int numSpecial(int[][] mat) {
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1 && isSpecial(mat, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isSpecial(int[][] mat, int row, int col) {
        for (int i = 0; i < mat.length; i++) {
            if (i != row && mat[i][col] == 1) {
                return false;
            }
        }
        for (int j = 0; j < mat[0].length; j++) {
            if (j != col && mat[row][j] == 1) {
                return false;
            }
        }
        return true;
    }
}