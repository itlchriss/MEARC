package g2301_2400.s2374_node_with_highest_edge_score;

// #Medium #Hash_Table #Graph #2022_08_19_Time_4_ms_(97.68%)_Space_85.4_MB_(85.92%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `edges` is not null.*);
//@ ensures(*The length of the input array `edges` is greater than or equal to 2.*);
//@ ensures(*The length of the input array `edges` is less than or equal to 10^5.*);
//@ ensures(*Each element in the input array `edges` is a non-negative integer.*);
//@ ensures(*Each element in the input array `edges` is less than the length of the input array `edges`.*);
//@ ensures(*Each node in the directed graph has exactly one outgoing edge.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the node with the highest edge score.*);
//@ ensures(*If multiple nodes have the same edge score, the node with the smallest index is returned.*);
    public int edgeScore(int[] edges) {
        int n = edges.length;
        int[] score = new int[n];
        int maxScore = 0;
        int node = 0;
        for (int i = 0; i < n; i++) {
            score[edges[i]] += i;
            if (score[edges[i]] >= maxScore) {
                if (score[edges[i]] == maxScore) {
                    node = Math.min(node, edges[i]);
                } else {
                    node = edges[i];
                }
                maxScore = score[edges[i]];
            }
        }
        return node;
    }
}