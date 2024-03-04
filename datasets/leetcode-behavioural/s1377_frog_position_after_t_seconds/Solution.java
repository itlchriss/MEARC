package g1301_1400.s1377_frog_position_after_t_seconds;

// #Hard #Depth_First_Search #Breadth_First_Search #Tree #Graph
// #2022_03_21_Time_6_ms_(81.30%)_Space_48.5_MB_(40.65%)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` represents the number of vertices in the tree and must be a positive integer.*);
//@ ensures(*The input `edges` represents the edges of the undirected tree and must be a 2D array of size `n-1` where each element is a pair of vertices `[a, b]`.*);
//@ ensures(*The input `t` represents the number of seconds and must be a positive integer.*);
//@ ensures(*The input `target` represents the target vertex and must be a valid vertex in the tree.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a double value representing the probability that after `t` seconds the frog is on the vertex `target`.*);
//@ ensures(*The returned probability is within `10^-5` of the actual answer.*);
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0] - 1].add(edge[1] - 1);
            graph[edge[1] - 1].add(edge[0] - 1);
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        double[] probabilities = new double[n];
        probabilities[0] = 1f;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty() && t-- > 0) {
            for (int i = queue.size(); i > 0; i--) {
                int vertex = queue.poll();
                int nextVerticesCount = 0;
                for (int next : graph[vertex]) {
                    if (!visited[next]) {
                        nextVerticesCount++;
                    }
                }
                for (int next : graph[vertex]) {
                    if (!visited[next] && nextVerticesCount > 0) {
                        visited[next] = true;
                        queue.offer(next);
                        probabilities[next] = probabilities[vertex] / nextVerticesCount;
                    }
                }
                if (nextVerticesCount > 0) {
                    probabilities[vertex] = 0;
                }
            }
        }
        return probabilities[target - 1];
    }
}