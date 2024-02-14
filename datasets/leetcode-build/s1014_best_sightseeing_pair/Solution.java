package g1001_1100.s1014_best_sightseeing_pair;

// #Medium #Array #Dynamic_Programming #Dynamic_Programming_I_Day_7
// #2022_02_25_Time_2_ms_(99.86%)_Space_51.3_MB_(73.75%)

public class Solution {
	//@ requires(*The input array `values` is not null.*);
	//@ requires(*The length of the input array `values` is at least 2.*);
	//@ requires(*The values in the input array `values` are positive integers.*);
	//@ requires(*The values in the input array `values` are not greater than 1000.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the maximum score of a pair of sightseeing spots.*);
    public int maxScoreSightseeingPair(int[] values) {
        int bestPrevious = values[0];
        int maxSum = 0;
        for (int i = 1; i < values.length; ++i) {
            maxSum = Math.max(maxSum, bestPrevious + values[i] - i);
            bestPrevious = Math.max(bestPrevious, values[i] + i);
        }
        return maxSum;
    }
}