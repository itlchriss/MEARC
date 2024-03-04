package g0101_0200.s0122_best_time_to_buy_and_sell_stock_ii;

// #Medium #Top_Interview_Questions #Array #Dynamic_Programming #Greedy #Dynamic_Programming_I_Day_7
// #Udemy_Arrays #2022_06_23_Time_1_ms_(96.82%)_Space_44.7_MB_(25.11%)

public class Solution {
//@ ensures(*The integer array parameter `prices` must not be null.*);
//@ ensures(*The integer result is the maximum profit that can be achieved by buying and selling the stock based on the prices in the integer array parameter `prices`.*);
//@ ensures(*The maximum profit is calculated by buying on a day with a lower price and selling on a day with a higher price, with the constraint of holding at most one share of the stock at any time.*);
//@ ensures(*If there is no way to make a positive profit, the integer result is 0.*);
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