package g0101_0200.s0123_best_time_to_buy_and_sell_stock_iii;

// #Hard #Array #Dynamic_Programming #2022_06_23_Time_4_ms_(87.18%)_Space_78.4_MB_(61.70%)

public class Solution {
	//@ requires(*The input array `prices` is not null.*);
	//@ requires(*The length of the input array `prices` is greater than or equal to 2.*);
	//@ ensures(*The method returns an integer representing the maximum profit that can be achieved.*);
	//@ ensures(*The maximum profit is calculated by buying and selling stocks at different days, with at most two transactions.*);
	//@ ensures(*The method returns 0 if no transactions can be done (i.e., the maximum profit is 0).*);
	//@ ensures(*The method returns the correct maximum profit for the given input array `prices`.*);
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