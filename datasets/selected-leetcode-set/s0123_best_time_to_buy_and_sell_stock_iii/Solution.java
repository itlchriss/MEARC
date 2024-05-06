package g0101_0200.s0123_best_time_to_buy_and_sell_stock_iii;

// #Hard #Array #Dynamic_Programming #2022_06_23_Time_4_ms_(87.18%)_Space_78.4_MB_(61.70%)

public class Solution {
//@ ensures(*The integer array parameter `prices` must not be null.*);
//@ ensures(*The length of the integer array parameter `prices` is greater than or equal to 1 and is less than or equal to 100000.*);
//@ ensures(*All values in the integer array parameter `prices` are greater than or equal to 0 and are less than or equal to 100000.*);
//@ ensures(*The integer result is the maximum profit that can be achieved by completing at most two transactions.*);
//@ ensures(*The maximum profit is calculated by buying and selling the stock according to the given constraints.*);
//@ ensures(*The maximum profit is equal to the sum of profits obtained from the two transactions.*);
//@ ensures(*If no transactions can be done to achieve a profit, the integer result is equal to 0.*);
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
            fs = Math.max(fs, fb + price);
            sb = Math.max(sb, fs - price);
            ss = Math.max(ss, sb + price);
        }
        return ss;
    }
}