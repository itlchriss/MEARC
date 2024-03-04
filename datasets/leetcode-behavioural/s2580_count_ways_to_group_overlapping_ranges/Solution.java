package g2501_2600.s2580_count_ways_to_group_overlapping_ranges;

// #Medium #Array #Sorting #2023_08_22_Time_16_ms_(98.28%)_Space_76.7_MB_(93.97%)

import java.util.Arrays;

public class Solution {
    private static final int MOD = (int) 1e9 + 7;

    private long powMod(long e) {
        long ans = 1;
        long b = 2;
        while (e != 0) {
            if ((e & 1) == 1) {
                ans *= b;
                ans %= MOD;
            }
            b *= b;
            b %= MOD;
            e >>= 1;
        }
        return ans;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input `ranges` is a 2D integer array.*);
//@ ensures(*Each element `ranges[i]` is an array of length - The values in `ranges[i]` represent the start and end of a range, where `start` is less than or equal to `end`.*);
//@ ensures(*The ranges in `ranges` may overlap.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total number of ways to split `ranges` into two groups.*);
//@ ensures(*The result is modulo `10^9 + 7`.*);

    public int countWays(int[][] ranges) {
        int cnt = 1;
        Arrays.sort(ranges, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int[] curr = ranges[0];
        for (int i = 1; i < ranges.length; i++) {
            if (ranges[i][1] < curr[0] || ranges[i][0] > curr[1]) {
                ++cnt;
                curr = ranges[i];
            } else {
                curr[1] = Math.max(curr[1], ranges[i][1]);
            }
        }
        return (int) powMod(cnt);
    }
}