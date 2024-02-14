package g0801_0900.s0861_score_after_flipping_matrix;

// #Medium #Array #Greedy #Matrix #Bit_Manipulation
// #2022_03_27_Time_1_ms_(70.04%)_Space_42.8_MB_(6.86%)

public class Solution {
	//@ requires(*The input matrix `grid` is not null.*);
	//@ requires(*The input matrix `grid` has at least one row and one column.*);
	//@ requires(*The input matrix `grid` has dimensions `m x n`, where `m` is the number of rows and `n` is the number of columns.*);
	//@ requires(*Each element in the input matrix `grid` is either `0` or `1`.*);
	//@ ensures(*The method returns an integer representing the highest possible score after making any number of moves.*);
	//@ ensures(*The highest possible score is calculated by interpreting each row of the matrix as a binary number and summing these numbers.*);
	//@ ensures(*The method does not modify the input matrix `grid`.*);
    public int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = 1;
                    } else {
                        grid[i][j] = 0;
                    }
                }
            }
        }
        for (int j = 1; j < n; j++) {
            int ones = 0;
            for (int[] ints : grid) {
                if (ints[j] == 1) {
                    ones++;
                }
            }
            if (ones <= m / 2) {
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 0;
                    } else {
                        grid[i][j] = 1;
                    }
                }
            }
        }
        int result = 0;
        for (int[] ints : grid) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(ints[j]);
            }
            result += Integer.parseInt(sb.toString(), 2);
        }
        return result;
    }
}