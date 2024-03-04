package g2001_2100.s2092_find_all_people_with_secret;

// #Hard #Sorting #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #2022_05_27_Time_95_ms_(84.86%)_Space_99.7_MB_(87.33%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` must be greater than or equal to - The input 2D integer array `meetings` must have a length greater than or equal to - Each subarray in `meetings` must have a length of - The values of `x_i` and `y_i` in each subarray of `meetings` must be between 0 and `n - 1`, inclusive.*);
//@ ensures(*The values of `x_i` and `y_i` in each subarray of `meetings` must be different.*);
//@ ensures(*The value of `time_i` in each subarray of `meetings` must be between 1 and 10^5, inclusive.*);
//@ ensures(*The input integer `firstPerson` must be between 1 and `n - 1`, inclusive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of integers representing the people who have the secret after all the meetings have taken place.*);
//@ ensures(*The list may be returned in any order.*);
//@ ensures(*The length of the returned list is equal to the number of people who have the secret.*);
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, ((a, b) -> a[2] - b[2]));
        UF uf = new UF(n);
        // base
        uf.union(0, firstPerson);
        // for every time we have a pool of people that talk to each other
        // if someone knows a secret proir to this meeting - all pool will too
        // if not - reset unions from this pool
        int i = 0;
        while (i < meetings.length) {
            int curTime = meetings[i][2];
            Set<Integer> pool = new HashSet<>();
            while (i < meetings.length && curTime == meetings[i][2]) {
                int[] currentMeeting = meetings[i];
                uf.union(currentMeeting[0], currentMeeting[1]);
                pool.add(currentMeeting[0]);
                pool.add(currentMeeting[1]);
                i++;
            }
            // meeting that took place now should't affect future
            // meetings if people don't know the secret
            for (int j : pool) {
                if (!uf.connected(0, j)) {
                    uf.reset(j);
                }
            }
        }
        // if the person is conneted to 0 - they know a secret
        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (uf.connected(j, 0)) {
                ans.add(j);
            }
        }
        return ans;
    }

    // regular union find
    private static class UF {
        private int[] parent;
        private int[] rank;

        public UF(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }

        public int find(int p) {
            while (parent[p] != p) {
                p = parent[parent[p]];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void reset(int p) {
            parent[p] = p;
            rank[p] = 0;
        }
    }
}