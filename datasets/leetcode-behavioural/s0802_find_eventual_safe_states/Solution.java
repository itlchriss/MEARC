package g0801_0900.s0802_find_eventual_safe_states;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Topological_Sort
// #Graph_Theory_I_Day_9_Standard_Traversal #2022_03_23_Time_7_ms_(74.93%)_Space_71.1_MB_(44.58%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input graph is a 2D integer array.*);
//@ ensures(*The graph is 0-indexed.*);
//@ ensures(*Each node in the graph is labeled from 0 to n-1.*);
//@ ensures(*The graph represents a directed graph.*);
//@ ensures(*Each element graph[i] in the graph array is an integer array of nodes adjacent to node i.*);
//@ ensures(*The graph[i] array represents the outgoing edges from node i.*);
//@ ensures(*The graph[i] array is sorted in a strictly increasing order.*);
//@ ensures(*The graph may contain self-loops.*);
//@ ensures(*The number of edges in the graph is in the range [1, 4 * 10^4].*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a List<Integer> containing all the safe nodes of the graph.*);
//@ ensures(*The safe nodes are sorted in ascending order.*);
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int[] vis = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (dfs(graph, i, vis)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean dfs(int[][] graph, int src, int[] vis) {
        if (vis[src] != 0) {
            return vis[src] == 2;
        }
        vis[src] = 1;
        for (int x : graph[src]) {
            if (!dfs(graph, x, vis)) {
                return false;
            }
        }
        vis[src] = 2;
        return true;
    }
}