package g2401_2500.s2477_minimum_fuel_cost_to_report_to_the_capital;

// #Medium #Depth_First_Search #Breadth_First_Search #Tree #Graph
// #2023_01_25_Time_70_ms_(72.49%)_Space_102.5_MB_(74.76%)

import java.util.ArrayList;

public class Solution {
    private long ans = 0L;
	//@ requires(*The input `roads` is a 2D integer array representing the roads between cities.*);
	//@ requires(*The input `seats` is an integer representing the number of seats in each car.*);
	//@ requires(*The number of cities `n` is greater than or equal to 1 and less than or equal to 10^5.*);
	//@ requires(*The length of `roads` is equal to `n - 1`.*);
	//@ requires(*Each element in `roads` is an array of length 2, representing a bidirectional road between two cities.*);
	//@ requires(*The values in `roads` are integers between 0 and `n - 1`.*);
	//@ requires(*The capital city is city 0.*);
	//@ ensures(*The method returns a long integer representing the minimum number of liters of fuel needed to reach the capital city.*);
	//@ ensures(*Note: The given requirements do not specify any additional preconditions or postconditions for the `minimumFuelCost` method.*);

    public long minimumFuelCost(int[][] roads, int seats) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = roads.length + 1;
        ans = 0L;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] a : roads) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        solve(adj, seats, 0, -1);
        return ans;
    }

    private long solve(ArrayList<ArrayList<Integer>> adj, int seats, int src, int parent) {
        long people = 1L;
        for (int i : adj.get(src)) {
            if (i != parent) {
                people += solve(adj, seats, i, src);
            }
        }
        if (src != 0) {
            ans += (long) Math.ceil((double) people / seats);
        }
        return people;
    }
}