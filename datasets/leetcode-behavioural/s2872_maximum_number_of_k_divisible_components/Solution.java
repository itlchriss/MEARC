package g2801_2900.s2872_maximum_number_of_k_divisible_components;

// #Hard #Dynamic_Programming #Depth_First_Search #Tree
// #2023_12_21_Time_24_ms_(93.51%)_Space_65.3_MB_(19.48%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int ans = 0;
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The length of the input `edges` is equal to `n - 1`.*);
//@ ensures(*Each element in the input `edges` is a 2-element array.*);
//@ ensures(*The values in the input `edges` are valid node labels within the range of `0` to `n - 1`.*);
//@ ensures(*The length of the input `values` is equal to `n`.*);
//@ ensures(*Each element in the input `values` is a non-negative integer.*);
//@ ensures(*The input `k` is a positive integer.*);
//@ ensures(*The sum of the values in the input `values` is divisible by `k`.*);
//@ ensures(*The input `edges` represents a valid tree.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum number of components in any valid split.*);

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            adj.get(start).add(end);
            adj.get(end).add(start);
        }
        boolean[] isVis = new boolean[n];
        isVis[0] = true;
        get(0, -1, adj, isVis, values, k);
        return ans;
    }

    private long get(
            int curNode,
            int parent,
            List<List<Integer>> adj,
            boolean[] isVis,
            int[] values,
            long k) {
        long sum = values[curNode];
        for (int ele : adj.get(curNode)) {
            if (ele != parent && !isVis[ele]) {
                isVis[ele] = true;
                sum += get(ele, curNode, adj, isVis, values, k);
            }
        }
        if (sum % k == 0) {
            ans++;
            return 0;
        } else {
            return sum;
        }
    }
}