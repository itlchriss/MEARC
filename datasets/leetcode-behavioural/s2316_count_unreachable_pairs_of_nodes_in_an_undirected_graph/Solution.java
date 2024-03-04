package g2301_2400.s2316_count_unreachable_pairs_of_nodes_in_an_undirected_graph;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #2022_06_26_Time_32_ms_(100.00%)_Space_108.9_MB_(100.00%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` must be greater than or equal to 1.*);
//@ ensures(*The length of the 2D integer array `edges` must be greater than or equal to 0.*);
//@ ensures(*Each subarray `edges[i]` must have a length of 2.*);
//@ ensures(*The values of `a_i` and `b_i` in each subarray `edges[i]` must be between 0 and `n-1`, inclusive.*);
//@ ensures(*The values of `a_i` and `b_i` in each subarray `edges[i]` must be different.*);
//@ ensures(*There should be no repeated edges in the `edges` array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a long value representing the number of pairs of different nodes that are unreachable from each other.*);
    public long countPairs(int n, int[][] edges) {
        DSU d = new DSU(n);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] e : edges) {
            d.union(e[0], e[1]);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = d.findParent(i);
            int cnt = map.containsKey(p) ? map.get(p) : 0;
            ans += i - cnt;
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        return ans;
    }

    private static class DSU {
        int[] rank;
        int[] parent;

        DSU(int n) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            parent[node] = findParent(parent[node]);
            return findParent(parent[node]);
        }

        boolean union(int x, int y) {
            int px = findParent(x);
            int py = findParent(y);
            if (px == py) {
                return false;
            }
            if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[px] = py;
                rank[py]++;
            }
            return true;
        }
    }
}