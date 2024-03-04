package g2401_2500.s2435_paths_in_matrix_whose_sum_is_divisible_by_k;

// #Hard #Array #Dynamic_Programming #Matrix #2022_12_07_Time_70_ms_(99.20%)_Space_84.5_MB_(87.93%)

import java.util.Arrays;

public class Solution {
    private int[][] p = new int[50005][50];
    private int[][] r = new int[50005][50];
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `grid` is not null.*);
//@ ensures(*The input matrix `grid` has at least one row and one column.*);
//@ ensures(*The input matrix `grid` contains only non-negative integers.*);
//@ ensures(*The input integer `k` is positive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is greater than or equal to 0.*);
//@ ensures(*The return value is less than or equal to 10^9 + 7.*);
//@ ensures(*The return value represents the number of paths from position (0, 0) to position (m - 1, n - 1) in the matrix `grid` where the sum of the elements on the path is divisible by `k`.*);

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int x = 0;
        for (int i = 0; i < n; ++i) {
            Arrays.fill(r[i], 0, k, 0);
            x += grid[0][i];
            r[i][x % k] = 1;
        }
        for (int i = 1; i < m; ++i) {
            int[][] t = p;
            p = r;
            r = t;
            x = grid[i][0] % k;
            for (int j = 0; j < k; ++j) {
                r[0][(x + j) % k] = p[0][j];
            }
            for (int j = 1, pj = 0; j < n; ++j, ++pj) {
                x = grid[i][j] % k;
                int y = (x > 0) ? (k - x) : 0;
                for (int l = 0; l < k; ++l, ++y) {
                    if (y == k) {
                        y = 0;
                    }
                    int modulo = 1000000007;
                    r[j][l] = (p[j][y] + r[pj][y]) % modulo;
                }
            }
        }
        return r[n - 1][0];
    }
}