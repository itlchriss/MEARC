package g0101_0200.s0121_best_time_to_buy_and_sell_stock;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Data_Structure_I_Day_3_Array #Dynamic_Programming_I_Day_7 #Level_1_Day_5_Greedy #Udemy_Arrays
// #Big_O_Time_O(N)_Space_O(1) #2022_06_23_Time_1_ms_(100.00%)_Space_58.9_MB_(93.57%)

public class Solution {
//@ ensures(*The integer array parameter `prices` must not be null.*);
//@ ensures(*The integer result is the maximum profit that can be achieved from buying and selling stocks based on the prices in the integer array parameter `prices`.*);
//@ ensures(*If no profit can be achieved, the integer result is 0.*);
//@ ensures(*The maximum profit is calculated by buying a stock on one day and selling it on a different day in the future to maximize profit.*);
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                maxProfit = Math.max(maxProfit, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return maxProfit;
    }
}