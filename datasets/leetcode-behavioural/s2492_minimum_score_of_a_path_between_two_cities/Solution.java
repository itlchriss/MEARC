package g2401_2500.s2492_minimum_score_of_a_path_between_two_cities;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #2023_01_27_Time_13_ms_(92.82%)_Space_101.5_MB_(78.71%)

import java.util.Arrays;

public class Solution {
    private int[] dsu;
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` must be a positive integer.*);
//@ ensures(*The input 2D array `roads` must have a length greater than or equal to 1.*);
//@ ensures(*Each element `roads[i]` in the `roads` array must be an array of length 3.*);
//@ ensures(*The first element `roads[i][0]` in each `roads[i]` array must be a positive integer between 1 and `n`, inclusive.*);
//@ ensures(*The second element `roads[i][1]` in each `roads[i]` array must be a positive integer between 1 and `n`, inclusive.*);
//@ ensures(*The first and second elements `roads[i][0]` and `roads[i][1]` in each `roads[i]` array must be distinct.*);
//@ ensures(*The third element `roads[i][2]` in each `roads[i]` array must be a positive integer between 1 and 10^4, inclusive.*);
//@ ensures(*The cities graph may not be connected, but there is at least one path between city 1 and city `n`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum possible score of a path between cities 1 and `n`.*);
//@ ensures(*The returned score is the minimum distance of a road in the path.*);
//@ ensures(*The path can contain the same road multiple times.*);
//@ ensures(*The path can visit cities 1 and `n` multiple times.*);

    public int minScore(int n, int[][] roads) {
        dsu = new int[n + 1];
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dsu[i] = i;
        }
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int[] r : roads) {
            int a = find(r[0]);
            int b = find(r[1]);
            dsu[a] = dsu[b];
            ans[a] = ans[b] = Math.min(r[2], Math.min(ans[a], ans[b]));
        }
        return ans[find(1)];
    }

    private int find(int i) {
        return dsu[i] == i ? i : find(dsu[i]);
    }
}