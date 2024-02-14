package g2301_2400.s2368_reachable_nodes_with_restrictions;

// #Medium #Array #Hash_Table #Depth_First_Search #Breadth_First_Search #Tree #Graph
// #2022_08_16_Time_59_ms_(85.71%)_Space_89.6_MB_(85.71%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*The input `n` is an integer representing the number of nodes in the tree.*);
	//@ requires(*The input `edges` is a 2D integer array of length `n - 1`, where each element `edges[i]` is an array of length 2 representing an edge between nodes `a_i` and `b_i` in the tree.*);
	//@ requires(*The input `restricted` is an integer array representing the restricted nodes.*);
	//@ requires(*Node `0` is not a restricted node.*);
	//@ requires(*The values of `restricted` are unique.*);
	//@ ensures(*The method returns an integer representing the maximum number of nodes that can be reached from node `0` without visiting a restricted node.*);
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            graph[src].add(dest);
            graph[dest].add(src);
        }
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        q.offer(0);
        visited[0] = true;
        for (int node : restricted) {
            visited[node] = true;
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int vertex = q.poll();
            ans++;
            for (int neighbour : graph[vertex]) {
                if (!visited[neighbour]) {
                    q.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        return ans;
    }
}