package g2601_2700.s2608_shortest_cycle_in_a_graph;

// #Hard #Breadth_First_Search #Graph #2023_08_30_Time_11_ms_(100.00%)_Space_44_MB_(76.40%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
    private int output = 1001;
	//@ requires(*The input `n` is an integer representing the number of vertices in the graph.*);
	//@ requires(*The input `edges` is a 2D integer array representing the edges in the graph.*);
	//@ requires(*Each element in `edges` is an array of size 2, representing an edge between two vertices.*);
	//@ requires(*The vertices in `edges` are labeled from 0 to `n - 1`.*);
	//@ requires(*Every vertex pair is connected by at most one edge.*);
	//@ requires(*No vertex has an edge to itself.*);
	//@ ensures(*The method returns an integer representing the length of the shortest cycle in the graph.*);
	//@ ensures(*If no cycle exists, the method returns -1.*);

    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] nexts = new ArrayList[n];
        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            nexts[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            for (int i = 0; i < 2; i++) {
                nexts[edge[i]].add(edge[1 - i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (ranks[i] == 0) {
                findShortestCycle(nexts, i, -1, -1001, ranks);
            }
        }
        return output == 1001 ? -1 : output;
    }

    private void findShortestCycle(List<Integer>[] nexts, int c, int p, int r, int[] ranks) {
        ranks[c] = r;
        for (int n : nexts[c]) {
            if (n != p) {
                if (ranks[n] > r + 1) {
                    findShortestCycle(nexts, n, c, r + 1, ranks);
                } else if (ranks[c] > ranks[n]) {
                    output = Math.min(output, ranks[c] - ranks[n] + 1);
                }
            }
        }
    }
}