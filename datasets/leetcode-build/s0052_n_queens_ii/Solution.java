package g0001_0100.s0052_n_queens_ii;

// #Hard #Backtracking #2023_08_11_Time_1_ms_(96.99%)_Space_39.8_MB_(38.70%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The chessboard has a size of `n x n`.*);
	//@ ensures(*The method returns an integer representing the number of distinct solutions to the n-queens puzzle.*);
	//@ ensures(*The number of distinct solutions is greater than or equal to 0.*);
	//@ ensures(*The number of distinct solutions is less than or equal to the total number of possible configurations of n queens on an n x n chessboard.*);
    public int totalNQueens(int n) {
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        boolean[] diagonal = new boolean[n + n - 1];
        boolean[] antiDiagonal = new boolean[n + n - 1];
        return totalNQueens(n, 0, row, col, diagonal, antiDiagonal);
    }

    private int totalNQueens(
            int n,
            int r,
            boolean[] row,
            boolean[] col,
            boolean[] diagonal,
            boolean[] antiDiagonal) {
        if (r == n) {
            return 1;
        }
        int count = 0;
        for (int c = 0; c < n; c++) {
            if (!row[r] && !col[c] && !diagonal[r + c] && !antiDiagonal[r - c + n - 1]) {
                row[r] = col[c] = diagonal[r + c] = antiDiagonal[r - c + n - 1] = true;
                count += totalNQueens(n, r + 1, row, col, diagonal, antiDiagonal);
                row[r] = col[c] = diagonal[r + c] = antiDiagonal[r - c + n - 1] = false;
            }
        }
        return count;
    }
}