package g0301_0400.s0309_best_time_to_buy_and_sell_stock_with_cooldown;

// #Medium #Array #Dynamic_Programming #Dynamic_Programming_I_Day_8
// #2022_07_07_Time_0_ms_(100.00%)_Space_42.3_MB_(44.85%)

public class Solution {
    /*
     * The series of problems are typical dp. The key for dp is to find the variables to
     * represent the states and deduce the transition function.
     *
     * Of course one may come up with a O(1) space solution directly, but I think it is better
     * to be generous when you think and be greedy when you implement.
     *
     * The natural states for this problem is the 3 possible transactions : buy, sell, rest.
     * Here rest means no transaction on that day (aka cooldown).
     *
     * Then the transaction sequences can end with any of these three states.
     *
     * For each of them we make an array, buy[n], sell[n] and rest[n].
     *
     * buy[i] means before day i what is the maxProfit for any sequence end with buy.
     *
     * sell[i] means before day i what is the maxProfit for any sequence end with sell.
     *
     * rest[i] means before day i what is the maxProfit for any sequence end with rest.
     *
     * Then we want to deduce the transition functions for buy sell and rest. By definition we
     * have:
     *
     * buy[i] = max(rest[i-1]-price, buy[i-1])
     * sell[i] = max(buy[i-1]+price, sell[i-1])
     * rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
     *
     * Where price is the price of day i. All of these are very straightforward. They simply represents :
     *
     * (1) We have to `rest` before we `buy` and
     * (2) we have to `buy` before we `sell`
     * One tricky point is how do you make sure you sell before you buy, since from the equations it seems that
     * [buy, rest, buy] is entirely possible.
     *
     * Well, the answer lies within the fact that buy[i] <= rest[i] which means rest[i] =
     * max(sell[i-1], rest[i-1]). That made sure [buy, rest, buy] is never occurred.
     *
     * A further observation is that and rest[i] <= sell[i] is also true therefore
     *
     * rest[i] = sell[i-1] Substitute this in to buy[i] we now have 2 functions instead of 3:
     *
     * buy[i] = max(sell[i-2]-price, buy[i-1]) sell[i] = max(buy[i-1]+price, sell[i-1]) This is
     * better than 3, but
     *
     * we can do even better
     *
     * Since states of day i relies only on i-1 and i-2 we can reduce the O(n) space to O(1).
     * And here we are at our final solution:
     */
//@ requires(*The length of the integer array parameter `prices` is less than or equal to 5000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `prices` are less than or equal to 1000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 123\. Best Time to Buy and Sell Stock III*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `ith` day.*);
//@ requires(**);
//@ requires(*Design an algorithm to find the maximum profit. You may complete **at most two transactions**.*);
//@ requires(**);
//@ requires(***Note:** You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** prices = \[3,3,5,0,0,3,1,4\]*);
//@ requires(**);
//@ requires(***Output:** 6*);
//@ requires(**);
//@ requires(***Explanation:** Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.*);
//@ requires(**);
//@ requires(*Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** prices = \[1,2,3,4,5\]*);
//@ requires(**);
//@ requires(***Output:** 4*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** prices = \[7,6,4,3,1\]*);
//@ requires(**);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= prices.length <= 105`*);
//@ requires(**   `0 <= prices[i] <= 105`*);
//@ requires(**);
//@ requires(*Method signature: public int maxProfit(int[] prices)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `prices` is less than or equal to 105 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `prices` are less than or equal to 105 and is greater than or equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the sum of the differences between adjacent elements in the integer array parameter `prices`.*);
//@ ensures(*If the integer array parameter `prices` is equal to [1,2,3,0,2], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `prices` is equal to [1], the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the sum of the differences between adjacent elements in the integer array parameter `prices`.*);
//@ ensures(*If the integer array parameter `prices` is equal to [3,3,5,0,0,3,1,4], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `prices` is equal to [1,2,3,4,5], the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `prices` is equal to [7,6,4,3,1], the integer result is equal to 0.*);
    public int maxProfit(int[] prices) {
        int sell = 0;
        int prevSell = 0;
        int buy = Integer.MIN_VALUE;
        int prevBuy;
        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevSell - price, prevBuy);
            prevSell = sell;
            sell = Math.max(prevBuy + price, prevSell);
        }
        return sell;
    }
}