package g2001_2100.s2045_second_minimum_time_to_reach_destination;

// #Hard #Breadth_First_Search #Graph #Shortest_Path
// #2022_05_26_Time_65_ms_(74.03%)_Space_51.6_MB_(97.24%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(******);
	//@ requires(*The input `n` is a positive integer representing the number of vertices in the graph.*);
	//@ requires(*The input `edges` is a 2D integer array representing the edges in the graph.*);
	//@ requires(*The length of `edges` is at least `n - 1` and at most `2 * 10^4` or `n * (n - 1) / 2`, whichever is smaller.*);
	//@ requires(*Each element `edges[i]` is a 2-element array `[u_i, v_i]` representing a bi-directional edge between vertex `u_i` and vertex `v_i`.*);
	//@ requires(*Each vertex pair is connected by at most one edge.*);
	//@ requires(*No vertex has an edge to itself.*);
	//@ requires(*The input `time` is a positive integer representing the time taken to traverse any edge.*);
	//@ requires(*The input `change` is a positive integer representing the time it takes for a traffic signal to change its color from green to red or vice versa.*);
	//@ requires(*All signals change at the same time.*);
	//@ requires(*The journey starts when all signals have just turned green.*);
	//@ requires(****);
	//@ ensures(****);
	//@ ensures(*The method returns an integer representing the second minimum time it will take to go from vertex 1 to vertex n.*);
	//@ ensures(*The returned time is the smallest value strictly larger than the minimum time.*);
	//@ ensures(*The minimum time is calculated by finding the shortest path from vertex 1 to vertex n, considering the time taken to traverse each edge and the time spent waiting at vertices with red signals.*);
	//@ ensures(*The second minimum time is calculated by finding a path from vertex 1 to vertex n that has a time greater than the minimum time and is the smallest among all such paths.*);
	//@ ensures(*The path from vertex 1 to vertex n is determined by traversing the graph using the given edges.*);
	//@ ensures(*At each vertex, the method checks the color of the traffic signal and waits if the signal is red.*);
	//@ ensures(*The method cannot wait at a vertex if the signal is green.*);
	//@ ensures(*The method can go through any vertex any number of times, including vertices 1 and n.*);
	//@ ensures(*Each vertex can be reached directly or indirectly from every other vertex.*);
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int p = edge[0] - 1;
            int q = edge[1] - 1;
            adj[p].add(q);
            adj[q].add(p);
        }
        int[] dis1 = new int[n];
        int[] dis2 = new int[n];
        Arrays.fill(dis1, Integer.MAX_VALUE);
        Arrays.fill(dis2, Integer.MAX_VALUE);
        dis1[0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int cur = temp[0];
            int path = temp[1];
            for (int node : adj[cur]) {
                int newPath = path + 1;
                if (newPath < dis1[node]) {
                    dis2[node] = dis1[node];
                    dis1[node] = newPath;
                    queue.offer(new int[] {node, newPath});
                } else if ((newPath > dis1[node]) && (newPath < dis2[node])) {
                    dis2[node] = newPath;
                    queue.offer(new int[] {node, newPath});
                }
            }
        }
        return helper(dis2[n - 1], time, change);
    }

    private int helper(int pathValue, int time, int change) {
        int sum = 0;
        for (int i = 0; i < pathValue; i++) {
            sum += time;
            if (i == pathValue - 1) {
                break;
            }
            if ((sum / change) % 2 != 0) {
                sum = (sum / change + 1) * change;
            }
        }
        return sum;
    }
}