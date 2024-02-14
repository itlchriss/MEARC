package g0101_0200.s0122_best_time_to_buy_and_sell_stock_ii;

// #Medium #Top_Interview_Questions #Array #Dynamic_Programming #Greedy #Dynamic_Programming_I_Day_7
// #Udemy_Arrays #2022_06_23_Time_1_ms_(96.82%)_Space_44.7_MB_(25.11%)

public class Solution {
	//@ requires(*The input array `prices` is not null.*);
	//@ requires(*The length of the input array `prices` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `prices` are non-negative integers.*);
	//@ ensures(*The method returns an integer representing the maximum profit that can be achieved.*);
	//@ ensures(*The maximum profit is calculated by buying and selling the stock on different days.*);
	//@ ensures(*The maximum profit is the sum of all positive differences between the selling price and the buying price.*);
	//@ ensures(*If there is no way to make a positive profit, the method returns 0.*);
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