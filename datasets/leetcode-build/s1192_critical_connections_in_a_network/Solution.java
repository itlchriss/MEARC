package g1101_1200.s1192_critical_connections_in_a_network;

// #Hard #Depth_First_Search #Graph #Biconnected_Component
// #2022_03_03_Time_127_ms_(89.39%)_Space_262.1_MB_(59.13%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input `n` is an integer greater than or equal to - The input `connections` is a list of lists, where each inner list contains two integers `a` and `b`, representing a connection between servers `a` and `b`.*);
	//@ requires(*The length of `connections` is greater than or equal to `n - 1`.*);
	//@ requires(*The integers `a` and `b` in each inner list are between 0 and `n - 1`, inclusive.*);
	//@ requires(*The integers `a` and `b` in each inner list are not equal.*);
	//@ requires(*There are no repeated connections.*);
	//@ ensures(*The method returns a list of lists, where each inner list contains two integers `a` and `b`, representing a critical connection between servers `a` and `b`.*);
	//@ ensures(*The returned list contains all critical connections in the network.*);
	//@ ensures(*The order of the critical connections in the returned list can be arbitrary.*);
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // build graph
        for (List<Integer> conn : connections) {
            int x = conn.get(0);
            int y = conn.get(1);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        // record rank
        int[] rank = new int[n];
        // store result
        List<List<Integer>> res = new ArrayList<>();
        dfs(graph, 0, 1, -1, rank, res);
        return res;
    }

    // rank[] records the each node's smallest rank(min (it's natural rank, neighbors's smallest
    // rank))
    private int dfs(
            List<List<Integer>> graph,
            int node,
            int time,
            int parent,
            int[] rank,
            List<List<Integer>> res) {
        if (rank[node] > 0) {
            return rank[node];
        }
        // record the current natural rank for current node
        rank[node] = time;
        for (int nei : graph.get(node)) {
            // skip the parent, since this is undirected graph
            if (nei == parent) {
                continue;
            }
            // step1 : run dfs to get the rank of this nei, if it is visited before, it will reach
            // base case immediately
            int neiTime = dfs(graph, nei, time + 1, node, rank, res);
            // if neiTime is strictly larger than current node's rank, there is no cycle,
            // connections between node and nei is a critically connection.
            if (neiTime > time) {
                res.add(Arrays.asList(nei, node));
            }
            // keep updating current node's rank with nei's smaller ranks
            rank[node] = Math.min(rank[node], neiTime);
        }
        // return current node's rank to caller
        return rank[node];
    }
}