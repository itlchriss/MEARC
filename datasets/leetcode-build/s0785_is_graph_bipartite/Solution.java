package g0701_0800.s0785_is_graph_bipartite;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #Graph_Theory_I_Day_14_Graph_Theory #2022_03_26_Time_0_ms_(100.00%)_Space_54.3_MB_(18.06%)

public class Solution {
	//@ requires(*1. The input graph must be represented as a 2D array.*);
	//@ requires(*2. The graph must be undirected.*);
	//@ requires(*3. The graph must have n nodes, where each node is numbered between 0 and n-1.*);
	//@ requires(*4. The graph must satisfy the following properties:*);
	//@ requires(* a. There are no self-edges (graph[u] does not contain u).*);
	//@ requires(* b. There are no parallel edges (graph[u] does not contain duplicate values).*);
	//@ requires(* c. If v is in graph[u], then u is in graph[v] (the graph is undirected).*);
	//@ requires(* d. The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.*);
	//@ ensures(*1. The method should return a boolean value indicating whether the graph is bipartite or not.*);
	//@ ensures(*2. If the graph is bipartite, the nodes should be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.*);
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !helper(graph, i, -1, color)) {
                return false;
            }
        }
        return true;
    }

    private boolean helper(int[][] graph, int curr, int c, int[] color) {
        if (color[curr] == c) {
            return true;
        }
        color[curr] = c;
        for (int x : graph[curr]) {
            if (color[x] == c || !helper(graph, x, c * -1, color)) {
                return false;
            }
        }
        return true;
    }
}