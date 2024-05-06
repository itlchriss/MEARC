package g0101_0200.s0122_best_time_to_buy_and_sell_stock_ii;

// #Medium #Top_Interview_Questions #Array #Dynamic_Programming #Greedy #Dynamic_Programming_I_Day_7
// #Udemy_Arrays #2022_06_23_Time_1_ms_(96.82%)_Space_44.7_MB_(25.11%)

public class Solution {
//@ ensures(*The integer array parameter `prices` must not be null.*);
//@ ensures(*The length of the integer array parameter `prices` is greater than or equal to 1 and is less than or equal to 30000.*);
//@ ensures(*All values in the integer array parameter `prices` are greater than or equal to 0 and are less than or equal to 10000.*);
//@ ensures(*The integer result is the maximum profit that can be achieved by buying and selling the stock based on the given prices.*);
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