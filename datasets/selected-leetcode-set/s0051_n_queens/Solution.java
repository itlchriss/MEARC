package g0001_0100.s0051_n_queens;

// #Hard #Top_100_Liked_Questions #Array #Backtracking #Big_O_Time_O(N!)_Space_O(N)
// #2023_08_11_Time_1_ms_(100.00%)_Space_43.6_MB_(97.17%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*The integer parameter `n` is greater than or equal to 1 and is less than or equal to 9.*);
//@ ensures(*The result is a list of lists where each inner list represents a distinct board configuration of the n-queens' placement.*);
//@ ensures(*Each inner list contains strings where 'Q' represents a queen and '.' represents an empty space on the chessboard.*);
//@ ensures(*The solutions in the result list are distinct from each other.*);
//@ ensures(*The order of the solutions in the result list can be arbitrary.*);
    public List<List<String>> solveNQueens(int n) {
        boolean[] pos = new boolean[n + 2 * n - 1 + 2 * n - 1];
        int[] pos2 = new int[n];
        List<List<String>> ans = new ArrayList<>();
        helper(n, 0, pos, pos2, ans);
        return ans;
    }

    //@ requires n == pos2.length;
    //@ requires pos.length == n + 2 * n - 1 + 2 * n - 1;
    private void helper(int n, int row, boolean[] pos, int[] pos2, List<List<String>> ans) {
        if (row == n) {
            construct(n, pos2, ans);
            return;
        }
        //@ loop_invariant 0 <= i <= n;
        //@ maintaining 0 <= n + 2 * n - 1 + n - 1 + i - row < pos.length;
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
        //@ loop_invariant 0 <= r <= n;
        //@ loop_invariant 0 <= r < pos.length;
        //@ maintaining \forall int i; 0 <= i < pos.length; 0 <= pos[i] < n;
        for (int r = 0; r < n; r++) {
            char[] queenRow = new char[n];
            Arrays.fill(queenRow, '.');
            queenRow[pos[r]] = 'Q';
            sol.add(new String(queenRow));
        }
        ans.add(sol);
    }
}