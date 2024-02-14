package g1401_1500.s1466_reorder_routes_to_make_all_paths_lead_to_the_city_zero;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph
// #Graph_Theory_I_Day_10_Standard_Traversal #2022_03_29_Time_39_ms_(97.71%)_Space_65.2_MB_(94.87%)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	//@ requires(*The input `n` represents the number of cities, and it must be greater than or equal to 2 and less than or equal to 5 * 10^4.*);
	//@ requires(*The input `connections` is a 2D array representing the roads between cities. It must have a length of `n - 1`.*);
	//@ requires(*Each element in `connections` is an array of length 2, representing a road from city `a` to city `b`.*);
	//@ requires(*The values of `a` and `b` in each element of `connections` must be between 0 and `n - 1`, inclusive.*);
	//@ requires(*The values of `a` and `b` in each element of `connections` must be different.*);
	//@ ensures(*The method returns an integer representing the minimum number of edges that need to be changed in order for each city to be able to reach city 0.*);
	//@ ensures(*The returned value is non-negative.*);
	//@ ensures(*It is guaranteed that each city can reach city 0 after the edges are reoriented.*);
    public int minReorder(int n, int[][] connections) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        List<List<Integer>> adj = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] tup : connections) {
            adj.get(tup[0]).add(tup[1]);
            adj.get(tup[1]).add(-tup[0]);
        }
        q.offer(0);
        vis[0] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int it : adj.get(node)) {
                if (!vis[Math.abs(it)]) {
                    vis[Math.abs(it)] = true;

                    if (it > 0) {
                        count++;
                    }
                    q.offer(Math.abs(it));
                }
            }
        }
        return count;
    }
}