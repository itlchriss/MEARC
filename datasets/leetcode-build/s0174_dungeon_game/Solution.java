package g0101_0200.s0174_dungeon_game;

// #Hard #Array #Dynamic_Programming #Matrix #2022_06_26_Time_0_ms_(100.00%)_Space_44_MB_(47.41%)

public class Solution {
	//@ requires(*The input `dungeon` is a 2D grid representing the rooms in the dungeon.*);
	//@ requires(*The knight is initially positioned in the top-left room.*);
	//@ requires(*The princess is imprisoned in the bottom-right corner of the dungeon.*);
	//@ requires(*The knight's initial health point is a positive integer.*);
	//@ requires(*The knight loses health when entering rooms guarded by demons (represented by negative integers).*);
	//@ requires(*Some rooms are empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).*);
	//@ requires(*The knight can only move rightward or downward in each step.*);
	//@ ensures(*The knight's minimum initial health is returned.*);
	//@ ensures(*If the knight's health point drops to 0 or below at any point, he dies immediately.*);
	//@ ensures(*The knight follows the optimal path to reach the princess as quickly as possible.*);
	//@ ensures(*The optimal path is determined by moving rightward or downward in each step.*);
	//@ ensures(*The knight's minimum initial health allows him to rescue the princess.*);
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