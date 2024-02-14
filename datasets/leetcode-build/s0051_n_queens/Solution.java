package g0001_0100.s0051_n_queens;

// #Hard #Top_100_Liked_Questions #Array #Backtracking #Big_O_Time_O(N!)_Space_O(N)
// #2023_08_11_Time_1_ms_(100.00%)_Space_43.6_MB_(97.17%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The value of `n` is within the range of 1 to 9.*);
	//@ ensures(*The method returns a list of lists of strings, representing distinct board configurations of the n-queens' placement.*);
	//@ ensures(*Each inner list represents a row on the chessboard.*);
	//@ ensures(*Each string in the inner list represents a cell on the chessboard, where `'Q'` indicates a queen and `'.'` indicates an empty space.*);
	//@ ensures(*The number of inner lists in the returned list is equal to `n`.*);
	//@ ensures(*The length of each inner list is equal to `n`.*);
	//@ ensures(*No two queens in any board configuration attack each other.*);
    public List<List<String>> solveNQueens(int n) {
        boolean[] pos = new boolean[n + 2 * n - 1 + 2 * n - 1];
        int[] pos2 = new int[n];
        List<List<String>> ans = new ArrayList<>();
        helper(n, 0, pos, pos2, ans);
        return ans;
    }

    private void helper(int n, int row, boolean[] pos, int[] pos2, List<List<String>> ans) {
        if (row == n) {
            construct(n, pos2, ans);
            return;
        }
        for (int i = 0; i < n; i++) {
            int index = n + 2 * n - 1 + n - 1 + i - row;
            if (pos[i] || pos[n + i + row] || pos[index]) {
                continue;
            }
            pos[i] = true;
            pos[n + i + row] = true;
            pos[index] = true;
            pos2[row] = i;
            helper(n, row + 1, pos, pos2, ans);
            pos[i] = false;
            pos[n + i + row] = false;
            pos[index] = false;
        }
    }

    private void construct(int n, int[] pos, List<List<String>> ans) {
        List<String> sol = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            char[] queenRow = new char[n];
            Arrays.fill(queenRow, '.');
            queenRow[pos[r]] = 'Q';
            sol.add(new String(queenRow));
        }
        ans.add(sol);
    }
}