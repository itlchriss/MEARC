package g0801_0900.s0867_transpose_matrix;

// #Easy #Array #Matrix #Simulation #2022_03_27_Time_1_ms_(60.83%)_Space_48.6_MB_(49.55%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix is a 2D integer array.*);
//@ ensures(*The input matrix is not null.*);
//@ ensures(*The input matrix has at least one row and one column.*);
//@ ensures(*The input matrix has a maximum size of 1000 rows and 1000 columns.*);
//@ ensures(*The values in the input matrix are within the range of -10^9 to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a 2D integer array representing the transpose of the input matrix.*);
//@ ensures(*The returned matrix has the same number of columns as the input matrix's number of rows.*);
//@ ensures(*The returned matrix has the same number of rows as the input matrix's number of columns.*);
//@ ensures(*The values in the returned matrix are obtained by flipping the row and column indices of the input matrix.*);
    public int[][] transpose(int[][] input) {
        int[][] output = new int[input[0].length][input.length];
        for (int i = 0, b = 0; i < input.length; i++, b++) {
            for (int j = 0, a = 0; j < input[0].length; j++, a++) {
                output[a][b] = input[i][j];
            }
        }
        return output;
    }
}