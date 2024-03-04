package g0701_0800.s0764_largest_plus_sign;

// #Medium #Array #Dynamic_Programming #2022_03_26_Time_39_ms_(87.23%)_Space_53.3_MB_(82.98%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The input `mines` is a non-empty array of arrays, where each inner array contains two integers `x` and `y`.*);
//@ ensures(*The values of `x` and `y` in each inner array are non-negative integers less than `n`.*);
//@ ensures(*The pairs `(x, y)` in `mines` are unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the order of the largest axis-aligned plus sign of 1's contained in `grid`.*);
//@ ensures(*If there is no plus sign, the output is 0.*);
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        boolean[][] mat = new boolean[n][n];
        for (int[] pos : mines) {
            mat[pos[0]][pos[1]] = true;
        }
        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] up = new int[n][n];
        int[][] down = new int[n][n];
        int ans = 0;
        // For Left and Up only
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int i1 = j == 0 ? 0 : left[i][j - 1];
                left[i][j] = mat[i][j] ? 0 : 1 + i1;
                int i2 = i == 0 ? 0 : up[i - 1][j];
                up[i][j] = mat[i][j] ? 0 : 1 + i2;
            }
        }
        // For Right and Down and simoultaneously get answer
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int i1 = j == n - 1 ? 0 : right[i][j + 1];
                right[i][j] = mat[i][j] ? 0 : 1 + i1;
                int i2 = i == n - 1 ? 0 : down[i + 1][j];
                down[i][j] = mat[i][j] ? 0 : 1 + i2;
                int x = Math.min(Math.min(left[i][j], up[i][j]), Math.min(right[i][j], down[i][j]));
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}