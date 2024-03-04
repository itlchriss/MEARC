package g1601_1700.s1697_checking_existence_of_edge_length_limited_paths;

// #Hard #Array #Sorting #Graph #Union_Find #2022_04_13_Time_94_ms_(90.48%)_Space_71.6_MB_(95.24%)

import java.util.Arrays;

public class Solution {
    private static class Dsu {
        private int[] parent;
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` must be an integer greater than or equal to 2.*);
//@ ensures(*The input `edgeList` must be a non-empty array of arrays, where each inner array has a length of 3.*);
//@ ensures(*The input `queries` must be a non-empty array of arrays, where each inner array has a length of 3.*);
//@ ensures(*The values in `edgeList` and `queries` must satisfy the given constraints.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output `answer` must be a boolean array with a length equal to the length of `queries`.*);
//@ ensures(*Each element in `answer` must be either `true` or `false`.*);
//@ ensures(*The values in `answer` must correspond to whether there is a path between the specified nodes in `queries` such that each edge on the path has a distance strictly less than the specified limit.*);

        public Dsu(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
        }

        public int find(int num) {
            if (parent[num] == -1) {
                return num;
            }
            parent[num] = find(parent[num]);
            return parent[num];
        }

        public void union(int a, int b) {
            int p1 = find(a);
            int p2 = find(b);
            if (p1 != p2) {
                parent[p2] = p1;
            }
        }
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        int[][] data = new int[queries.length][4];
        for (int i = 0; i < queries.length; i++) {
            data[i] = new int[] {queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(data, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        Dsu d = new Dsu(n);
        int j = 0;
        boolean[] ans = new boolean[queries.length];
        for (int[] datum : data) {
            while (j < edgeList.length && edgeList[j][2] < datum[2]) {
                d.union(edgeList[j][0], edgeList[j][1]);
                j++;
            }
            if (d.find(datum[0]) == d.find(datum[1])) {
                ans[datum[3]] = true;
            }
        }
        return ans;
    }
}