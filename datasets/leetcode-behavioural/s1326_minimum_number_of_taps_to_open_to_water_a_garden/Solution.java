package g1301_1400.s1326_minimum_number_of_taps_to_open_to_water_a_garden;

// #Hard #Array #Dynamic_Programming #Greedy #2022_03_19_Time_3_ms_(90.04%)_Space_47.7_MB_(59.59%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The input `ranges` is an array of integers with length `n + 1`.*);
//@ ensures(*Each element in `ranges` is a non-negative integer.*);
//@ ensures(*The length of `ranges` is equal to `n + 1`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of taps that should be open to water the whole garden.*);
//@ ensures(*If the garden cannot be watered, the output is -1.*);
    public int minTaps(int n, int[] ranges) {
        if (n == 0 || ranges.length == 0) {
            return n == 0 ? 0 : -1;
        }
        int[] dp = new int[n + 1];

        int nxtLargest = 0;
        int current = 0;
        int amount = 0;
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] > 0) {
                int ind = Math.max(0, i - ranges[i]);
                dp[ind] = Math.max(dp[ind], i + ranges[i]);
            }
        }
        for (int i = 0; i <= n; i++) {
            nxtLargest = Math.max(nxtLargest, dp[i]);
            if (i == current && i < n) {
                current = nxtLargest;
                amount++;
            }
            if (current < i) {
                return -1;
            }
        }
        return amount;
    }
}