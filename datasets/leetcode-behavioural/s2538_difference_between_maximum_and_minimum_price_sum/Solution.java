package g2501_2600.s2538_difference_between_maximum_and_minimum_price_sum;

// #Hard #Array #Dynamic_Programming #Depth_First_Search #Tree
// #2023_04_22_Time_43_ms_(95.19%)_Space_86.2_MB_(57.69%)

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Solution {
    private ArrayList<Integer>[] tree;
    private int[] price;
    private long res;
    private boolean[] visited;
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The length of the input `edges` is equal to `n - 1`.*);
//@ ensures(*Each element in the input `edges` is a 2-element array.*);
//@ ensures(*Each element in the input `edges` is an array of integers.*);
//@ ensures(*Each element in the input `edges` represents an edge between two nodes in the tree.*);
//@ ensures(*The input `price` is an array of integers.*);
//@ ensures(*The length of the input `price` is equal to `n`.*);
//@ ensures(*Each element in the input `price` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a long integer.*);
//@ ensures(*The output represents the maximum possible cost among all possible root choices.*);
//@ ensures(*The maximum possible cost is the difference between the maximum and minimum price sum amongst all paths starting at the root.*);
//@ ensures(*The root can be any node in the tree.*);
//@ ensures(*The maximum and minimum price sums are calculated by summing the prices of all nodes lying on the path from the root to a leaf node.*);
//@ ensures(*The maximum and minimum price sums are calculated for all possible paths starting at the root.*);
//@ ensures(*The difference between the maximum and minimum price sums is the maximum possible cost.*);

    public long maxOutput(int n, int[][] edges, int[] price) {
        if (n == 1) {
            return 0;
        }
        this.price = price;
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        visited = new boolean[n];
        visited[0] = true;
        dfs(0);
        return res;
    }

    // return long[]{longest path with leaf, longest path without leaf}
    private long[] dfs(int node) {
        if (tree[node].size() == 1 && node != 0) {
            return new long[] {price[node], 0};
        }
        int i0 = -1;
        int i1 = -1;
        long l0 = 0;
        long l1 = 0;
        long s0 = 0;
        long s1 = 0;
        for (int child : tree[node]) {
            if (visited[child]) {
                continue;
            }
            visited[child] = true;
            long[] sub = dfs(child);
            if (sub[0] >= l0) {
                s0 = l0;
                l0 = sub[0];
                i0 = child;
            } else if (sub[0] > s0) {
                s0 = sub[0];
            }
            if (sub[1] >= l1) {
                s1 = l1;
                l1 = sub[1];
                i1 = child;
            } else if (sub[1] > s1) {
                s1 = sub[1];
            }
        }
        if (s0 == 0) {
            // only one child. case: example 2
            res = Math.max(res, Math.max(l0, l1 + price[node]));
        } else {
            long path = i0 != i1 ? price[node] + l0 + l1 : price[node] + Math.max(l0 + s1, s0 + l1);
            res = Math.max(res, path);
        }
        return new long[] {l0 + price[node], l1 + price[node]};
    }
}