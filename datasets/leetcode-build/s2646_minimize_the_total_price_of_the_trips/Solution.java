package g2601_2700.s2646_minimize_the_total_price_of_the_trips;

// #Hard #Array #Dynamic_Programming #Depth_First_Search #Tree #Graph
// #2023_09_06_Time_7_ms_(100.00%)_Space_44.6_MB_(6.40%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
    private List<Integer>[] graph;
    private int[] count;
	//@ requires(*The input integer `n` represents the number of nodes in the tree.*);
	//@ requires(*The input 2D integer array `edges` of length `n - 1` represents the edges in the tree.*);
	//@ requires(*Each element `edges[i]` in `edges` is a 2-element array `[a, b]` where `a` and `b` are node indices indicating an edge between nodes `a` and `b`.*);
	//@ requires(*The input integer array `price` of length `n` represents the prices of each node.*);
	//@ requires(*Each element `price[i]` in `price` is the price of the `i`-th node.*);
	//@ requires(*The input 2D integer array `trips` represents the trips to be performed.*);
	//@ requires(*Each element `trips[i]` in `trips` is a 2-element array `[start, end]` where `start` and `end` are node indices indicating a trip from node `start` to node `end`.*);
	//@ requires(*The prices in `price` are even integers.*);
	//@ requires(*The number of trips in `trips` is between 1 and 100.*);
	//@ requires(*The node indices in `edges` and `trips` are between 0 and `n - 1`.*);
	//@ ensures(*The method returns an integer representing the minimum total price sum to perform all the given trips.*);

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        count = new int[n];
        for (int[] t : trips) {
            getPath(t[0], -1, t[1]);
        }
        int[] maxSum = getMax(0, -1, price);
        int res = -Math.max(maxSum[0], maxSum[1]) / 2;
        for (int i = 0; i < n; i++) {
            res += count[i] * price[i];
        }
        return res;
    }

    private int[] getMax(int current, int prev, int[] price) {
        int[] res = new int[] {price[current] * count[current], 0};
        for (int v : graph[current]) {
            if (v == prev) {
                continue;
            }
            int[] curr = getMax(v, current, price);
            res[0] += curr[1];
            res[1] += Math.max(curr[0], curr[1]);
        }
        return res;
    }

    private boolean getPath(int current, int prev, int search) {
        if (current == search) {
            count[current]++;
            return true;
        }
        for (int v : graph[current]) {
            if (v == prev) {
                continue;
            }
            if (getPath(v, current, search)) {
                count[current]++;
                return true;
            }
        }
        return false;
    }
}