package g0601_0700.s0684_redundant_connection;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #2022_03_22_Time_0_ms_(100.00%)_Space_42.7_MB_(76.10%)

public class Solution {
    private int[] par;
//@ ensures(*The integer array parameter `edges` must not be null.*);
//@ ensures(*The integer array parameter `edges` represents a graph that started as a tree with `n` nodes labeled from `1` to `n`, with one additional edge added.*);
//@ ensures(*The integer array parameter `edges` contains edges between nodes `a_i` and `b_i`.*);
//@ ensures(*The integer array result is an edge that can be removed so that the resulting graph is a tree of `n` nodes.*);
//@ ensures(*If there are multiple answers, the result is the answer that occurs last in the input.*);

    public int[] findRedundantConnection(int[][] edges) {
        int[] ans = new int[2];
        int n = edges.length;
        par = new int[n + 1];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
        for (int[] edge : edges) {
            int lx = find(edge[0]);
            int ly = find(edge[1]);
            if (lx != ly) {
                par[lx] = ly;
            } else {
                ans[0] = edge[0];
                ans[1] = edge[1];
            }
        }
        return ans;
    }

    private int find(int x) {
        if (par[x] == x) {
            return x;
        }
        return find(par[x]);
    }
}