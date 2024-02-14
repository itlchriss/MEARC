package g2301_2400.s2360_longest_cycle_in_a_graph;

// #Hard #Depth_First_Search #Graph #Topological_Sort
// #2022_08_14_Time_37_ms_(90.19%)_Space_102.8_MB_(72.92%)

public class Solution {
	//@ requires(*1. The input array `edges` is not null.*);
	//@ requires(*2. The length of the input array `edges` is greater than or equal to 2.*);
	//@ requires(*3. The length of the input array `edges` is less than or equal to 10^5.*);
	//@ requires(*4. Each element in the input array `edges` is an integer.*);
	//@ requires(*5. Each element in the input array `edges` is between -1 and n-1, inclusive.*);
	//@ requires(*6. The number of nodes in the graph is equal to the length of the input array `edges`.*);
	//@ requires(*7. Each node in the graph has at most one outgoing edge.*);
	//@ ensures(*1. The output is an integer representing the length of the longest cycle in the graph.*);
	//@ ensures(*2. If no cycle exists in the graph, the output is -1.*);
    public int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] vis = new boolean[n];
        boolean[] dfsvis = new boolean[n];
        int[] path = new int[n];
        int maxLength = -1;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                path[i] = 1;
                maxLength = Math.max(maxLength, dfs(i, 1, path, vis, dfsvis, edges));
            }
        }
        return maxLength;
    }

    private int dfs(
            int node, int pathLength, int[] path, boolean[] vis, boolean[] dfsvis, int[] edges) {
        vis[node] = true;
        dfsvis[node] = true;
        int length = -1;
        if (edges[node] != -1 && !vis[edges[node]]) {
            path[edges[node]] = pathLength + 1;
            length = dfs(edges[node], pathLength + 1, path, vis, dfsvis, edges);
        } else if (edges[node] != -1 && dfsvis[edges[node]]) {
            length = pathLength - path[edges[node]] + 1;
        }
        dfsvis[node] = false;
        return length;
    }
}