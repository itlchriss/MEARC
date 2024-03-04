package g0601_0700.s0646_maximum_length_of_pair_chain;

// #Medium #Array #Dynamic_Programming #Sorting #Greedy
// #2022_03_21_Time_11_ms_(88.84%)_Space_54.4_MB_(18.92%)

import java.util.Arrays;

public class Solution {
//@ ensures(*The integer array parameter `pairs` must not be null.*);
//@ ensures(*The integer array parameter `pairs` must contain pairs where the first element is less than the second element.*);
//@ ensures(*The integer result is the length of the longest chain that can be formed from the pairs in the integer array parameter `pairs`.*);
    public int findLongestChain(int[][] pairs) {
        if (pairs.length == 1) {
            return 1;
        }
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int min = pairs[0][1];
        int max = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > min) {
                max++;
                min = pairs[i][1];
            }
        }
        return max;
    }
}