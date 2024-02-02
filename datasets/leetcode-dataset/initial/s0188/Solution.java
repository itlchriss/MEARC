package g0101_0200.s0188_best_time_to_buy_and_sell_stock_iv;

// #Hard #Array #Dynamic_Programming #2022_06_27_Time_1_ms_(100.00%)_Space_42.7_MB_(47.38%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Find the maximum profit you can achieve. You may complete at most k transactions.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[] dp = new int[k + 1];
        int[] maxdp = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            maxdp[i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            maxdp[0] = Math.max(maxdp[0], dp[0] - prices[i - 1]);
            for (int j = k; j >= 1; j--) {
                maxdp[j] = Math.max(maxdp[j], dp[j] - prices[i - 1]);
                dp[j] = Math.max(maxdp[j - 1] + prices[i - 1], dp[j]);
            }
        }
        return dp[k];
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
