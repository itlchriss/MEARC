package g0101_0200.s0121_best_time_to_buy_and_sell_stock;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Data_Structure_I_Day_3_Array #Dynamic_Programming_I_Day_7 #Level_1_Day_5_Greedy #Udemy_Arrays
// #Big_O_Time_O(N)_Space_O(1) #2022_06_23_Time_1_ms_(100.00%)_Space_58.9_MB_(93.57%)

public class Solution {
//@ requires((\forall int i; 0 <= i && i < prices.length; 0 <= prices[i] && prices[i] <= 10000));
//@ ensures(\result >= 0);
//@ requires(prices != null && prices.length >= 1 && prices.length <= 100000);
//@ ensures((\forall int i, j; 0 <= i && i < j && j < prices.length; prices[j] - prices[i] <= \result));
//@ ensures((\exists int i, j; 0 <= i && i < j && j < prices.length; prices[j] - prices[i] == \result));
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= min) {
                maxProfit = Math.max(maxProfit, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return maxProfit;
    }
}
