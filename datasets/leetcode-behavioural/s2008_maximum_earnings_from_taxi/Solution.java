package g2001_2100.s2008_maximum_earnings_from_taxi;

// #Medium #Array #Dynamic_Programming #Sorting #Binary_Search
// #2022_05_23_Time_116_ms_(55.59%)_Space_135.7_MB_(5.00%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer representing the number of points on the road.*);
//@ ensures(*The input `rides` is a 2D integer array where each element `rides[i]` represents a passenger request with the format `[start_i, end_i, tip_i]`.*);
//@ ensures(*The length of `rides` is between 1 and 3 * 10^- Each element `rides[i]` has a length of - The start point `start_i` is a positive integer less than the end point `end_i` and less than or equal to `n`.*);
//@ ensures(*The tip amount `tip_i` is a positive integer less than or equal to 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long integer representing the maximum number of dollars that can be earned by picking up passengers optimally.*);
//@ ensures(*The maximum earnings are calculated by summing the earnings from each passenger, where the earnings for each passenger `i` is equal to `end_i - start_i + tip_i`.*);
//@ ensures(*The taxi can only drive at most one passenger at a time.*);
//@ ensures(*The taxi cannot change its direction.*);
//@ ensures(*The taxi can drop off a passenger and pick up a different passenger at the same point.*);
    public long maxTaxiEarnings(int n, int[][] rides) {
        if (rides.length == 1) {
            return calculateEarnings(rides[0]);
        }

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] ride : rides) {
            map.compute(ride[1], (k, v) -> (v == null) ? new ArrayList<>() : v).add(ride);
        }

        long[] maximisedEarnings = new long[n + 1];
        Arrays.fill(maximisedEarnings, 0);
        for (int i = 1; i < maximisedEarnings.length; i++) {
            maximisedEarnings[i] = maximisedEarnings[i - 1];
            List<int[]> passengers = map.get(i);
            if (passengers != null) {
                for (int[] passenger : passengers) {
                    final int earning = calculateEarnings(passenger);
                    maximisedEarnings[i] =
                            Math.max(
                                    maximisedEarnings[i],
                                    maximisedEarnings[passenger[0]] + earning);
                }
            }
        }
        return maximisedEarnings[n];
    }

    private int calculateEarnings(final int[] currentRide) {
        return currentRide[1] - currentRide[0] + currentRide[2];
    }
}