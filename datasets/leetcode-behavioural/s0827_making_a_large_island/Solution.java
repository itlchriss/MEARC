package g0801_0900.s0827_making_a_large_island;

// #Hard #Array #Depth_First_Search #Breadth_First_Search #Matrix #Union_Find
// #2022_03_24_Time_147_ms_(74.37%)_Space_132.4_MB_(56.88%)

import java.util.HashMap;

public class Solution {
    private int[] p;
    private int[] s;

    private void makeSet(int x, int y, int rl) {
        int a = x * rl + y;
        p[a] = a;
        s[a] = 1;
    }

    private void comb(int x1, int y1, int x2, int y2, int rl) {
        int a = find(x1 * rl + y1);
        int b = find(x2 * rl + y2);
        if (a == b) {
            return;
        }
        if (s[a] < s[b]) {
            int t = a;
            a = b;
            b = t;
        }
        p[b] = a;
        s[a] += s[b];
    }

    private int find(int a) {
        if (p[a] == a) {
            return a;
        }
        p[a] = find(p[a]);
        return p[a];
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid is a square matrix with dimensions n x n.*);
//@ ensures(*The elements of the grid are either 0 or 1.*);
//@ ensures(*The grid has at least one island.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the size of the largest island in the grid after applying the operation.*);
//@ ensures(*The grid remains unchanged except for at most one 0 being changed to 1.*);
//@ ensures(**);
//@ ensures(*Additional behavioral requirements:*);
//@ ensures(*The method should handle the case where the grid is empty (n = 0) and return 0.*);
//@ ensures(*The method should handle the case where the grid has only one element (n = 1) and return 1 if the element is 1, or 2 if the element is 0.*);
//@ ensures(*The method should handle the case where the grid has multiple islands and return the size of the largest island after applying the operation.*);
//@ ensures(*The method should handle the case where the grid has no 0s and return the size of the largest island without changing any element.*);
//@ ensures(*The method should handle the case where the grid has multiple 0s and return the size of the largest island after changing one 0 to 1.*);
//@ ensures(*The method should handle the case where the grid has multiple islands and changing one 0 to 1 connects two islands, resulting in a larger island.*);
//@ ensures(*The method should handle the case where the grid has multiple islands and changing one 0 to 1 does not connect any islands, resulting in the same largest island size.*);

    public int largestIsland(int[][] grid) {
        int rl = grid.length;
        int cl = grid[0].length;
        p = new int[rl * cl];
        s = new int[rl * cl];
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                makeSet(i, j, rl);
                if (i > 0 && grid[i - 1][j] == 1) {
                    comb(i, j, i - 1, j, rl);
                }
                if (j > 0 && grid[i][j - 1] == 1) {
                    comb(i, j, i, j - 1, rl);
                }
            }
        }
        int m = 0;
        int t;
        HashMap<Integer, Integer> sz = new HashMap<>();
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (grid[i][j] == 0) {
                    // find root, check if same and combine size
                    t = 1;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        sz.put(find((i - 1) * rl + j), s[find((i - 1) * rl + j)]);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        sz.put(find(i * rl + j - 1), s[find(i * rl + j - 1)]);
                    }
                    if ((i < rl - 1) && grid[i + 1][j] == 1) {
                        sz.put(find((i + 1) * rl + j), s[find((i + 1) * rl + j)]);
                    }
                    if ((j < cl - 1) && grid[i][j + 1] == 1) {
                        sz.put(find(i * rl + j + 1), s[find(i * rl + j + 1)]);
                    }
                    for (int val : sz.values()) {
                        t += val;
                    }
                    m = Math.max(m, t);
                    sz.clear();
                } else {
                    m = Math.max(m, s[i * rl + j]);
                }
            }
        }
        return m;
    }
}