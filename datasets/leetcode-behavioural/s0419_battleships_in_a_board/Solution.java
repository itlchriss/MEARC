package g0401_0500.s0419_battleships_in_a_board;

// #Medium #Array #Depth_First_Search #Matrix #2022_07_16_Time_0_ms_(100.00%)_Space_41.9_MB_(88.66%)

public class Solution {
//@ ensures(*The character matrix parameter `board` must not be null.*);
//@ ensures(*The integer result is the number of battleships on the character matrix parameter `board`.*);
//@ ensures(*Battleships can only be placed horizontally or vertically on the character matrix parameter `board`.*);
//@ ensures(*Battleships are represented by the character 'X' on the character matrix parameter `board`.*);
//@ ensures(*Battleships are separated by at least one horizontal or vertical cell on the character matrix parameter `board`.*);
//@ ensures(*There are no adjacent battleships on the character matrix parameter `board`.*);
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