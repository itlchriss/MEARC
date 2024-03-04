package g0101_0200.s0174_dungeon_game;

// #Hard #Array #Dynamic_Programming #Matrix #2022_06_26_Time_0_ms_(100.00%)_Space_44_MB_(47.41%)

public class Solution {
//@ ensures(*The integer 2D array parameter `dungeon` must not be null.*);
//@ ensures(*The integer result is the knight's minimum initial health so that he can rescue the princess.*);
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] memo = new int[m][n];
        return Math.max(1, 1 - traverse(dungeon, 0, 0, memo));
    }

    private int traverse(int[][] dungeon, int r, int c, int[][] memo) {
        if (r == dungeon.length - 1 && c == dungeon[0].length - 1) {
            return dungeon[r][c];
        }
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        int res = Integer.MIN_VALUE;
        if (r + 1 < dungeon.length) {
            res = Math.max(res, traverse(dungeon, r + 1, c, memo));
        }
        if (c + 1 < dungeon[0].length) {
            res = Math.max(res, traverse(dungeon, r, c + 1, memo));
        }
        res = Math.min(dungeon[r][c], res + dungeon[r][c]);
        memo[r][c] = res;
        return res;
    }
}