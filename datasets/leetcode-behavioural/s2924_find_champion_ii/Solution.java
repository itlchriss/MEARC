package g2901_3000.s2924_find_champion_ii;

// #Medium #Graph #2023_12_29_Time_1_ms_(100.00%)_Space_46_MB_(5.87%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer representing the number of teams.*);
//@ ensures(*The input `edges` is a 2D integer array representing the directed edges in the graph.*);
//@ ensures(*The length of `edges` is equal to `m`.*);
//@ ensures(*Each element in `edges` is a 2-element array `[u, v]` where `u` and `v` are integers representing team numbers.*);
//@ ensures(*The team numbers `u` and `v` are in the range from 0 to `n-1`.*);
//@ ensures(*The input is generated such that if team `a` is stronger than team `b`, team `b` is not stronger than team `a`.*);
//@ ensures(*The input is generated such that if team `a` is stronger than team `b` and team `b` is stronger than team `c`, then team `a` is stronger than team `c`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the team that will be the champion of the tournament if there is a unique champion.*);
//@ ensures(*If there is no unique champion, the method returns -1.*);
    public int findChampion(int n, int[][] edges) {
        int[] arr = new int[n];
        for (int[] adj : edges) {
            arr[adj[1]]++;
        }
        int cnt = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                cnt++;
                ans = i;
            }
        }
        if (cnt == 1) {
            return ans;
        } else {
            return -1;
        }
    }
}