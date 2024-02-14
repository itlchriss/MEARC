package g0601_0700.s0684_redundant_connection;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #2022_03_22_Time_0_ms_(100.00%)_Space_42.7_MB_(76.10%)

public class Solution {
    private int[] par;
	//@ requires(*The input `edges` is a non-empty array.*);
	//@ requires(*The length of `edges` is equal to `n`.*);
	//@ requires(*Each element in `edges` is an array of length - The values in each element of `edges` are integers.*);
	//@ requires(*The integers in each element of `edges` are between 1 and `n`.*);
	//@ requires(*The integers in each element of `edges` are different.*);
	//@ requires(*The graph represented by `edges` is connected.*);
	//@ ensures(*The output is an array of length - The values in the output array are integers.*);
	//@ ensures(*The integers in the output array are between 1 and `n`.*);
	//@ ensures(*The integers in the output array are different.*);
	//@ ensures(*Removing the edge represented by the output array will result in a tree with `n` nodes.*);

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