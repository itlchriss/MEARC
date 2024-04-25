package g0101_0200.s0122_best_time_to_buy_and_sell_stock_ii;

// #Medium #Top_Interview_Questions #Array #Dynamic_Programming #Greedy #Dynamic_Programming_I_Day_7
// #Udemy_Arrays #2022_06_23_Time_1_ms_(96.82%)_Space_44.7_MB_(25.11%)

public class Solution {
// requires(prices != null && prices.length >= 1 && prices.length <= 3 * 10^4);
//@ ensures((\forall int i, j; 0 <= i && i < j && j < prices.length; \result >= prices[j] - prices[i]));
//@ ensures(\result >= 0);
// requires((\forall int i; 0 <= i && i < prices.length; 0 <= prices[i] && prices[i] <= 10^4));
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }
}
