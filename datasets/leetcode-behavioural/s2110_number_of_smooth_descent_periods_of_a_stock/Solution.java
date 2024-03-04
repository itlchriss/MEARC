package g2101_2200.s2110_number_of_smooth_descent_periods_of_a_stock;

// #Medium #Array #Dynamic_Programming #Math #2022_05_31_Time_3_ms_(77.27%)_Space_95.2_MB_(34.45%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `prices` is not null.*);
//@ ensures(*The length of the input array `prices` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `prices` are integers.*);
//@ ensures(*The elements in the input array `prices` are greater than or equal to 1.*);
//@ ensures(*The elements in the input array `prices` are less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of smooth descent periods.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
    public long getDescentPeriods(int[] prices) {
        long descendantCount = 0;
        int previousCounts = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] - prices[i + 1] == 1) {
                descendantCount++;
                if (previousCounts > 0) {
                    descendantCount += previousCounts;
                }
                previousCounts++;
            } else {
                previousCounts = 0;
            }
        }
        descendantCount += prices.length;
        return descendantCount;
    }
}