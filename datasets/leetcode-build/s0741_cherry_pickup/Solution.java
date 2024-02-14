package g0701_0800.s0741_cherry_pickup;

// #Hard #Array #Dynamic_Programming #Matrix #2022_03_25_Time_11_ms_(99.40%)_Space_42.5_MB_(90.61%)

public class Solution {
	//@ requires(*The input grid is a square grid of size n x n.*);
	//@ requires(*The grid contains only three possible integers: 0, 1, and -1.*);
	//@ requires(*The starting position (0, 0) and the ending position (n - 1, n - 1) are valid positions in the grid.*);
	//@ requires(*The starting position (0, 0) contains a cherry (1) that can be picked up.*);
	//@ requires(*The ending position (n - 1, n - 1) is not a thorn (-1).*);
	//@ ensures(*The method returns an integer representing the maximum number of cherries that can be collected.*);
	//@ ensures(*The grid is modified such that all picked cherries (1) are replaced with empty cells (0).*);
	//@ ensures(*If there is no valid path between (0, 0) and (n - 1, n - 1), the method returns 0.*);
    public int cherryPickup(int[][] grid) {
        int[][][] dp = new int[grid.length][grid.length][grid.length];
        int ans = solve(0, 0, 0, grid, dp);
        return Math.max(ans, 0);
    }

    private int solve(int r1, int c1, int r2, int[][] arr, int[][][] dp) {
        int c2 = r1 + c1 - r2;
        if (r1 >= arr.length
                || r2 >= arr.length
                || c1 >= arr[0].length
                || c2 >= arr[0].length
                || arr[r1][c1] == -1
                || arr[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }

        if (r1 == arr.length - 1 && c1 == arr[0].length - 1) {
            return arr[r1][c1];
        }

        if (dp[r1][c1][r2] != 0) {
            return dp[r1][c1][r2];
        }

        int cherries = 0;
        if (r1 == r2 && c1 == c2) {
            cherries += arr[r1][c1];
        } else {
            cherries += arr[r1][c1] + arr[r2][c2];
        }

        int a = solve(r1, c1 + 1, r2, arr, dp);
        int b = solve(r1 + 1, c1, r2 + 1, arr, dp);
        int c = solve(r1, c1 + 1, r2 + 1, arr, dp);
        int d = solve(r1 + 1, c1, r2, arr, dp);

        cherries += Math.max(Math.max(a, b), Math.max(c, d));
        dp[r1][c1][r2] = cherries;
        return cherries;
    }
}