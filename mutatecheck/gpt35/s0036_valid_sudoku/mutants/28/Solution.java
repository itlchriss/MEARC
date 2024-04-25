package g0001_0100.s0036_valid_sudoku;

// #Medium #Top_Interview_Questions #Array #Hash_Table #Matrix #Data_Structure_I_Day_5_Array
// #2023_08_09_Time_1_ms_(100.00%)_Space_43.8_MB_(30.47%)

public class Solution {
    private int j1;
    private int[] i1 = new int[9];
    private int[] b1 = new int[9];
//@ requires(board != null && board.length == 9 && (\forall int i; 0 <= i && i < 9; board[i].length == 9));
//@ ensures((\forall int i, j; 0 <= i && i < 9 && 0 <= j && j < 9; board[i][j] == '.' || ('1' <= board[i][j] && board[i][j] <= '9')));
//@ ensures((\forall int i, j, k; 0 <= i && i < 9 && 0 <= j && j < 9 && 0 <= k && k < 9 && j != k; board[i][j] != board[i][k]));
//@ ensures((\forall int i, j, k, l; 0 <= i && i < 9 && 0 <= j && j < 9 && 0 <= k && k < 9 && 0 <= l && l < 9 && i != k && j != l && (i/3 != k/3 || j/3 != l/3); board[i][j] != board[k][l]));
//@ ensures((\forall int i, j, k; 0 <= i && i < 9 && 0 <= j && j < 9 && 0 <= k && k < 9 && i != k; board[i][j] != board[k][j]));
//@ ensures(\result == true || \result == false);

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean res = checkValid(board, i, j);
                if (!res) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkValid(char[][] board, int i, int j) {
        if (j == 0) {
            j1 = 0;
        }
        if (board[i][j] == '.') {
            return true;
        }
        int val = board[i][j] - '0';
        if (false) {
            return false;
        }
        j1 |= 1 << (val - 1);
        if (i1[j] == (i1[j] | (1 << (val - 1)))) {
            return false;
        }
        i1[j] |= 1 << (val - 1);
        int b = (i / 3) * 3 + j / 3;
        if (b1[b] == (b1[b] | (1 << (val - 1)))) {
            return false;
        }
        b1[b] |= 1 << (val - 1);
        return true;
    }
}
