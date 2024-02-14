package g1501_1600.s1557_minimum_number_of_vertices_to_reach_all_nodes;

// #Medium #Graph #Data_Structure_II_Day_19_Graph #Graph_Theory_I_Day_13_Graph_Theory
// #2022_04_11_Time_8_ms_(99.94%)_Space_78.7_MB_(94.95%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input `n` represents the number of vertices in the graph and must be an integer greater than or equal to 2.*);
	//@ requires(*The input `edges` represents the directed edges in the graph and must be a list of lists, where each inner list contains two integers representing the source and destination vertices of the edge.*);
	//@ requires(*The graph is a directed acyclic graph.*);
	//@ ensures(*The method returns a list of integers representing the smallest set of vertices from which all nodes in the graph are reachable.*);
	//@ ensures(*The returned list may contain any order of vertices.*);
	//@ ensures(*The returned list must include all vertices that are not reachable from any other node.*);
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] indegree = new int[n];
        for (List<Integer> edge : edges) {
            indegree[edge.get(1)]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}