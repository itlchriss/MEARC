package g0601_0700.s0685_redundant_connection_ii;

// #Hard #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #2022_03_22_Time_1_ms_(96.65%)_Space_44.6_MB_(25.00%)

public class Solution {
    private int[] par;
	//@ requires(*The input `edges` is a 2D array of size `n`x2, where `n` is the number of nodes in the graph.*);
	//@ requires(*Each element in `edges` represents a directed edge connecting two nodes.*);
	//@ requires(*The graph started as a rooted tree with `n` nodes.*);
	//@ requires(*One additional directed edge was added to the graph.*);
	//@ requires(*The added edge has two different vertices chosen from `1` to `n`.*);
	//@ requires(*The added edge was not an edge that already existed.*);
	//@ ensures(*The returned array is of size 2, representing the edge that can be removed.*);
	//@ ensures(*The resulting graph after removing the edge is a rooted tree with `n` nodes.*);
	//@ ensures(*If there are multiple answers, the returned edge is the one that occurs last in the given 2D array.*);

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] haspar = new int[n + 1];
        for (int[] edge : edges) {
            int v = edge[1];
            haspar[v]++;
        }
        par = new int[n + 1];
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (haspar[v] == 1) {
                int lu = find(u);
                int lv = find(v);
                if (lu != lv) {
                    par[lu] = lv;
                } else {
                    return edge;
                }
            }
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (haspar[v] > 1) {
                int lu = find(u);
                int lv = find(v);
                if (lu != lv) {
                    par[lu] = lv;
                } else {
                    return edge;
                }
            }
        }
        return new int[2];
    }

    private int find(int x) {
        if (par[x] == x) {
            return x;
        }
        return find(par[x]);
    }
}