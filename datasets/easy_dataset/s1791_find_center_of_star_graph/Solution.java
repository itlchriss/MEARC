package g1701_1800.s1791_find_center_of_star_graph;

// #Easy #Graph #2022_05_03_Time_0_ms_(100.00%)_Space_71_MB_(77.85%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `edges` is a 2D integer array.*);
//@ ensures(*The length of `edges` is equal to `n - 1`, where `n` is the number of nodes in the graph.*);
//@ ensures(*Each element in `edges` is an array of length 2, representing an edge between two nodes.*);
//@ ensures(*The nodes in `edges` are labeled from 1 to `n`.*);
//@ ensures(*The given `edges` represent a valid star graph.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the center node of the star graph.*);
    public int findCenter(int[][] edges) {
        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        }
        return edges[0][1];
    }
}