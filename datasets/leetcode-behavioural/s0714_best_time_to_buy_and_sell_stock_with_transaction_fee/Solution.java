package g0701_0800.s0714_best_time_to_buy_and_sell_stock_with_transaction_fee;

// #Medium #Array #Dynamic_Programming #Greedy #Dynamic_Programming_I_Day_8
// #2022_03_24_Time_4_ms_(78.57%)_Space_75.9_MB_(33.27%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `prices` must not be null.*);
//@ ensures(*The length of the input array `prices` must be greater than or equal to - The elements in the input array `prices` must be positive integers.*);
//@ ensures(*The input integer `fee` must be a non-negative integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum profit that can be achieved.*);
//@ ensures(*The maximum profit is calculated by buying and selling stocks from the input array `prices` while considering the transaction fee.*);
//@ ensures(*The maximum profit is the sum of the differences between the selling prices and the buying prices, minus the transaction fees.*);
//@ ensures(*The buying and selling prices must follow the constraint that the stock must be sold before it can be bought again.*);
//@ ensures(*The maximum profit is calculated by considering multiple transactions, where each transaction incurs a transaction fee.*);
//@ ensures(*The method should return 0 if it is not possible to make a profit.*);
    public int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}