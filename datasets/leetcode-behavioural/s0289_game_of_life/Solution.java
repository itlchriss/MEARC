package g0201_0300.s0289_game_of_life;

// #Medium #Top_Interview_Questions #Array #Matrix #Simulation
// #2022_07_06_Time_0_ms_(100.00%)_Space_42.9_MB_(10.73%)

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer 2D array parameter `board` must not be null.*);
//@ ensures(*The next state of the `m x n` grid `board` must be calculated according to the rules of the Game of Life.*);
//@ ensures(*Any live cell with fewer than two live neighbors must die.*);
//@ ensures(*Any live cell with two or three live neighbors must survive to the next generation.*);
//@ ensures(*Any live cell with more than three live neighbors must die.*);
//@ ensures(*Any dead cell with exactly three live neighbors must become alive.*);
//@ ensures(*The next state of the `m x n` grid `board` must be updated simultaneously.*);
//@ ensures(*The method `gameOfLife` must update the `board` in-place.*);
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