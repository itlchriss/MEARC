package g2301_2400.s2328_number_of_increasing_paths_in_a_grid;

// #Hard #Array #Dynamic_Programming #Depth_First_Search #Breadth_First_Search #Matrix #Graph
// #Memoization #Topological_Sort #2022_07_04_Time_43_ms_(100.00%)_Space_89.1_MB_(63.64%)

public class Solution {
    private int help(int[][] a, int i, int j, int n, int m, int[][] dp) {
        if (i < 0 || i >= n || j >= m || j < 0) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        long res = 0;
        if (i < n - 1 && a[i + 1][j] > a[i][j]) {
            res += 1 + help(a, i + 1, j, n, m, dp);
        }
        if (i > 0 && a[i - 1][j] > a[i][j]) {
            res += 1 + help(a, i - 1, j, n, m, dp);
        }
        if (j > 0 && a[i][j - 1] > a[i][j]) {
            res += 1 + help(a, i, j - 1, n, m, dp);
        }
        if (j < m - 1 && a[i][j + 1] > a[i][j]) {
            res += 1 + help(a, i, j + 1, n, m, dp);
        }
        dp[i][j] = (int) res % 1000000007;
        return dp[i][j];
    }
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input grid must not be null.*);
//@ ensures(*2. The input grid must have at least one row and one column.*);
//@ ensures(*3. The input grid must have a valid size, where the number of rows and columns is between 1 and 1000.*);
//@ ensures(*4. The values in the input grid must be positive integers between 1 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return an integer value representing the number of strictly increasing paths in the grid.*);
//@ ensures(*2. The returned value should be modulo 10^9 + 7 to handle large results.*);

    public int countPaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long ans = (long) n * m;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += help(grid, i, j, n, m, dp) % 1000000007;
            }
        }
        ans = ans % 1000000007;
        return (int) ans;
    }
}