package g2601_2700.s2643_row_with_maximum_ones;

// #Easy #Array #Matrix #2023_09_05_Time_1_ms_(100.00%)_Space_44.6_MB_(89.31%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is not null.*);
//@ ensures(*The input matrix `mat` is a binary matrix, meaning it only contains 0s and 1s.*);
//@ ensures(*The input matrix `mat` has at least one row and one column.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an array of size - The first element of the output array is the index of the row with the maximum count of ones.*);
//@ ensures(*The second element of the output array is the number of ones in the row with the maximum count of ones.*);
//@ ensures(*If there are multiple rows with the maximum count of ones, the row with the smallest row number is selected.*);
    public int[] rowAndMaximumOnes(int[][] mat) {
        int row = -1;
        int best = -1;
        for (int i = 0; i < mat.length; i++) {
            int sum = 0;
            for (int j = 0; j < mat[i].length; j++) {
                sum += mat[i][j];
            }
            if (sum > best) {
                best = sum;
                row = i;
            }
        }
        return new int[] {row, best};
    }
}