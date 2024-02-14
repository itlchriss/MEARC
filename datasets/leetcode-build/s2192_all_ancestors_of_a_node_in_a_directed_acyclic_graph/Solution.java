package g2101_2200.s2192_all_ancestors_of_a_node_in_a_directed_acyclic_graph;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Topological_Sort
// #2022_06_07_Time_82_ms_(90.80%)_Space_137.8_MB_(29.00%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> adjList;
    private List<List<Integer>> result;
	//@ requires(*The input integer `n` must be a positive integer.*);
	//@ requires(*The input 2D integer array `edges` must be a valid representation of a directed acyclic graph.*);
	//@ requires(*The number of rows in `edges` must be less than or equal to the maximum number of edges in a directed acyclic graph with `n` nodes, which is `n * (n - 1) / 2`.*);
	//@ requires(*Each row in `edges` must contain exactly 2 elements.*);
	//@ requires(*The elements in each row of `edges` must be integers between 0 and `n - 1`, inclusive.*);
	//@ requires(*The elements in each row of `edges` must be distinct.*);
	//@ requires(*The graph represented by `edges` must be directed and acyclic.*);
	//@ ensures(*The output `answer` must be a list of lists of integers.*);
	//@ ensures(*The length of `answer` must be equal to `n`.*);
	//@ ensures(*Each element in `answer` must be a list of integers representing the ancestors of the corresponding node.*);
	//@ ensures(*The ancestors of each node in `answer` must be sorted in ascending order.*);

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        this.adjList = new ArrayList<>();
        this.result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
            result.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            adjList.get(start).add(end);
        }
        //  DFS for each node from 0 --> n , and add that node as root/parent into each reachable
        // node and their child
        //  Use visited[] to identify if any of the child or their childs are already visited for
        // that perticular root/parent,
        //  so will not add the root to avoid duplicacy and call reduction .
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            List<Integer> childList = adjList.get(i);
            for (Integer child : childList) {
                if (!visited[child]) {
                    dfs(i, child, visited);
                }
            }
        }
        return result;
    }

    private void dfs(int root, int node, boolean[] visited) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        result.get(node).add(root);
        List<Integer> childList = adjList.get(node);
        for (Integer child : childList) {
            if (!visited[child]) {
                dfs(root, child, visited);
            }
        }
    }
}