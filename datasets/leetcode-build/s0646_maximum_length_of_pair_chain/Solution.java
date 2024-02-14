package g0601_0700.s0646_maximum_length_of_pair_chain;

// #Medium #Array #Dynamic_Programming #Sorting #Greedy
// #2022_03_21_Time_11_ms_(88.84%)_Space_54.4_MB_(18.92%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `pairs` is not null.*);
	//@ requires(*The length of the input array `pairs` is greater than or equal to 1.*);
	//@ requires(*Each pair in the input array `pairs` is a valid pair, where the left element is less than the right element.*);
	//@ ensures(*The returned value is an integer representing the length of the longest chain that can be formed.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to the length of the input array `pairs`.*);
	//@ ensures(*The pairs in the longest chain are selected in such a way that each pair follows the previous pair according to the given condition.*);
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