package g2901_3000.s2925_maximum_score_after_applying_operations_on_a_tree;

// #Medium #Dynamic_Programming #Depth_First_Search #Tree
// #2023_12_29_Time_22_ms_(79.74%)_Space_57.1_MB_(70.30%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(***Preconditions:***);
//@ ensures(**);
//@ ensures(*1. The input array `edges` is not null.*);
//@ ensures(*2. The input array `values` is not null.*);
//@ ensures(*3. The length of the input array `edges` is equal to `n - 1`, where `n` is the number of nodes in the tree.*);
//@ ensures(*4. The length of the input array `values` is equal to `n`.*);
//@ ensures(*5. The values in the input array `edges` are valid node indices, i.e., `0 <= a_i, b_i < n`.*);
//@ ensures(*6. The values in the input array `values` are positive integers, i.e., `1 <= values[i] <= 10^9`.*);
//@ ensures(**);
//@ ensures(***Postconditions:***);
//@ ensures(**);
//@ ensures(*1. The method returns a long value representing the maximum score obtainable after performing operations on the tree.*);
//@ ensures(*2. The tree remains healthy after performing the operations, i.e., the sum of values on the path from the root to any leaf node is different than zero.*);
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        long sum = 0;
        int n = values.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        for (int value : values) {
            sum += value;
        }
        long x = dfs(0, -1, adj, values);
        return sum - x;
    }

    private long dfs(int node, int parent, List<List<Integer>> adj, int[] values) {
        if (adj.get(node).size() == 1 && node != 0) {
            return values[node];
        }
        long sum = 0;
        for (int child : adj.get(node)) {
            if (child != parent) {
                sum += dfs(child, node, adj, values);
            }
        }
        return Math.min(sum, values[node]);
    }
}