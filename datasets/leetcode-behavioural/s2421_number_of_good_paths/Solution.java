package g2401_2500.s2421_number_of_good_paths;

// #Hard #Array #Tree #Graph #Union_Find #2022_11_18_Time_31_ms_(99.43%)_Space_62.2_MB_(94.18%)

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `vals` is not null.*);
//@ ensures(*The input array `edges` is not null.*);
//@ ensures(*The length of `vals` is equal to `n`.*);
//@ ensures(*The length of `edges` is equal to `n - 1`.*);
//@ ensures(*The values in `vals` are integers.*);
//@ ensures(*The values in `edges` are pairs of integers.*);
//@ ensures(*The values in `edges` are valid indices of nodes in `vals`.*);
//@ ensures(*The values in `edges` are distinct and do not contain duplicates.*);
//@ ensures(*The values in `edges` form a valid tree.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value represents the number of distinct good paths in the tree.*);
//@ ensures(*The return value is greater than or equal to 0.*);
//@ ensures(*The return value is less than or equal to the total number of nodes in the tree.*);
//@ ensures(*The return value is equal to the number of single-node paths in the tree if all nodes have the same value.*);
//@ ensures(*The return value is equal to the number of paths from a node to itself if the node's value is the maximum value in the path.*);
//@ ensures(*The return value is equal to the number of paths from a node to its parent if the node's value is less than or equal to the parent's value.*);
//@ ensures(*The return value is equal to the sum of the number of paths from each node to its parent.*);
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        int[] parent = new int[n];
        int[] maxElement = new int[n];
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            maxElement[i] = vals[i];
            count[i] = 1;
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> Math.max(vals[a[0]], vals[a[1]])));
        int ans = n;
        for (int[] it : edges) {
            int a = findParent(parent, it[0]);
            int b = findParent(parent, it[1]);
            if (maxElement[a] != maxElement[b]) {
                if (maxElement[a] > maxElement[b]) {
                    parent[b] = a;
                } else {
                    parent[a] = b;
                }
            } else {
                parent[b] = a;
                ans += count[a] * count[b];
                count[a] += count[b];
            }
        }
        return ans;
    }

    private int findParent(int[] parent, int a) {
        if (a == parent[a]) {
            return a;
        }
        parent[a] = findParent(parent, parent[a]);
        return parent[a];
    }
}