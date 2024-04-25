package g0101_0200.s0123_best_time_to_buy_and_sell_stock_iii;

// #Hard #Array #Dynamic_Programming #2022_06_23_Time_4_ms_(87.18%)_Space_78.4_MB_(61.70%)

public class Solution {
//@ ensures((\forall int i, j; 0 <= i && i < j && j < prices.length; \result >= prices[j] - prices[i]));
//@ ensures((\forall int i, j, k; 0 <= i && i < j && j < k && k < prices.length; \result >= prices[k] - prices[i] + prices[j] - prices[j]));
//@ ensures((\forall int i; 0 <= i && i < prices.length; \result >= prices[i]));
//@ requires(prices != null && prices.length >= 1 && prices.length <= 100000);
//@ requires((\forall int i; 0 <= i && i < prices.length; 0 <= prices[i] && prices[i] <= 100000));
//@ ensures(\result >= 0);
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int fb = Integer.MIN_VALUE;
        int sb = Integer.MIN_VALUE;
        int fs = 0;
        int ss = 0;
        for (int price : prices) {
            fb = Math.max(fb, -price);
            fs = Math.max(fs, fb - price);
            sb = Math.max(sb, fs - price);
            ss = Math.max(ss, sb + price);
        }
        return ss;
    }
}
