package g0001_0100.s0079_word_search;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Matrix #Backtracking
// #Algorithm_II_Day_11_Recursion_Backtracking #Big_O_Time_O(4^(m*n))_Space_O(m*n)
// #2023_08_11_Time_157_ms_(78.97%)_Space_40.5_MB_(84.41%)

public class Solution {
    private boolean backtrace(
            char[][] board, boolean[][] visited, String word, int index, int x, int y) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        if (word.charAt(index) == board[x][y]) {
            boolean res =
                    backtrace(board, visited, word, index + 1, x, y + 1)
                            || backtrace(board, visited, word, index + 1, x, y - 1)
                            || backtrace(board, visited, word, index + 1, x + 1, y)
                            || backtrace(board, visited, word, index + 1, x - 1, y);
            if (!res) {
                visited[x][y] = false;
            }
            return res;
        } else {
            visited[x][y] = false;
            return false;
        }
    }
	//@ requires(*The input `board` is a 2D array of characters.*);
	//@ requires(*The input `word` is a string.*);
	//@ requires(*The length of `board` is equal to `m`.*);
	//@ requires(*The length of each row in `board` is equal to `n`.*);
	//@ requires(*The length of `word` is greater than or equal to 1 and less than or equal to 15.*);
	//@ requires(*The characters in `board` and `word` are all lowercase or uppercase English letters.*);
	//@ ensures(*The method returns a boolean value indicating whether the `word` exists in the `board`.*);
	//@ ensures(*If the `word` exists in the `board`, the method returns `true`.*);
	//@ ensures(*If the `word` does not exist in the `board`, the method returns `false`.*);
	//@ ensures(*The same letter cell in the `board` cannot be used more than once to construct the `word`.*);
	//@ ensures(*The `word` can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.*);

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (backtrace(board, visited, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}