package g0401_0500.s0419_battleships_in_a_board;

// #Medium #Array #Depth_First_Search #Matrix #2022_07_16_Time_0_ms_(100.00%)_Space_41.9_MB_(88.66%)

public class Solution {
	//@ requires(*1. The input `board` is a non-null `m x n` matrix.*);
	//@ requires(*2. The elements of `board` are either `'.'` or `'X'`.*);
	//@ requires(*3. The dimensions `m` and `n` of `board` are both greater than or equal to 1 and less than or equal to 200.*);
	//@ ensures(*1. The method returns an integer representing the number of battleships on the board.*);
	//@ ensures(*2. The method does not modify the values of the `board` matrix.*);
	//@ ensures(*3. The method uses only `O(1)` extra memory.*);
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.'
                        && (j <= 0 || board[i][j - 1] != 'X')
                        && (i <= 0 || board[i - 1][j] != 'X')) {
                    count++;
                }
            }
        }
        return count;
    }
}