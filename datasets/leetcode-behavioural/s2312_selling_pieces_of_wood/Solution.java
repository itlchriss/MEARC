package g2301_2400.s2312_selling_pieces_of_wood;

// #Hard #Backtracking #2022_06_20_Time_78_ms_(63.64%)_Space_53.1_MB_(63.64%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `m` and `n` must be greater than or equal to 1 and less than or equal to 200.*);
//@ ensures(*The length of the `prices` array must be greater than or equal to 1 and less than or equal to 20000.*);
//@ ensures(*Each element in the `prices` array must be an array of length 3.*);
//@ ensures(*The first element of each subarray in the `prices` array must be greater than or equal to 1 and less than or equal to `m`.*);
//@ ensures(*The second element of each subarray in the `prices` array must be greater than or equal to 1 and less than or equal to `n`.*);
//@ ensures(*The third element of each subarray in the `prices` array must be greater than or equal to 1 and less than or equal to 1000000.*);
//@ ensures(*All the shapes of wood `(h_i, w_i)` must be distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the maximum money that can be earned after cutting the `m x n` piece of wood.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
    public long sellingWood(int m, int n, int[][] prices) {
        // dp[i][j] = Maximum profit selling wood of size i*j
        long[][] dp = new long[m][n];
        for (int[] price : prices) {
            dp[price[0] - 1][price[1] - 1] = Math.max(dp[price[0] - 1][price[1] - 1], price[2]);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Cut Vertically
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k - 1]);
                }
                // Cut Horizontally
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k - 1][j]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}