package g0201_0300.s0289_game_of_life;

// #Medium #Top_Interview_Questions #Array #Matrix #Simulation
// #2022_07_06_Time_0_ms_(100.00%)_Space_42.9_MB_(10.73%)

public class Solution {
	//@ requires(*1. The input `board` must be a 2D array.*);
	//@ requires(*2. The dimensions of the `board` must be `m x n`, where `m` and `n` are positive integers.*);
	//@ requires(*3. Each element in the `board` array must be either `0` or `1`.*);
	//@ requires(*4. The `board` array must represent the current state of the game.*);
	//@ ensures(*1. The `board` array should be updated to represent the next state of the game.*);
	//@ ensures(*2. The dimensions of the `board` array should remain the same.*);
	//@ ensures(*3. Each element in the `board` array should still be either `0` or `1`.*);
	//@ ensures(*4. The next state of the game should follow the rules of the Game of Life as described in the requirements.*);
	//@ ensures(*5. The births and deaths of cells should occur simultaneously.*);
	//@ ensures(*6. The updated `board` array should be returned as the output.*);
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = lives(board, i, j, m, n);
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                } else if (board[i][j] == 1 && (lives == 2 || lives == 3)) {
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int lives(int[][] board, int i, int j, int m, int n) {
        int lives = 0;
        for (int r = Math.max(0, i - 1); r <= Math.min(m - 1, i + 1); r++) {
            for (int c = Math.max(0, j - 1); c <= Math.min(n - 1, j + 1); c++) {
                lives += board[r][c] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}