package g1401_1500.s1443_minimum_time_to_collect_all_apples_in_a_tree;

// #Medium #Hash_Table #Depth_First_Search #Breadth_First_Search #Tree
// #2022_03_28_Time_75_ms_(57.75%)_Space_85.1_MB_(85.45%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("java:S1172")
public class Solution {
	//@ requires(*The input `n` is a positive integer representing the number of vertices in the tree.*);
	//@ requires(*The input `edges` is a 2D array of size `n-1` where each element `edges[i]` is an array of size 2 representing an edge connecting vertices `a_i` and `b_i`.*);
	//@ requires(*The input `hasApple` is a List of size `n` where each element `hasApple[i]` is a boolean value indicating whether vertex `i` has an apple or not.*);
	//@ ensures(*The method returns an integer representing the minimum time in seconds required to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.*);
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int vertexA = edge[0];
            int vertexB = edge[1];
            graph.computeIfAbsent(vertexA, key -> new ArrayList<>()).add(vertexB);
            graph.computeIfAbsent(vertexB, key -> new ArrayList<>()).add(vertexA);
        }
        visited.add(0);
        int steps = helper(graph, hasApple, 0, visited);
        return steps > 0 ? steps - 2 : 0;
    }

    private int helper(
            Map<Integer, List<Integer>> graph,
            List<Boolean> hasApple,
            int node,
            Set<Integer> visited) {
        int steps = 0;
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (visited.contains(child)) {
                continue;
            } else {
                visited.add(child);
            }
            steps += helper(graph, hasApple, child, visited);
        }
        if (steps > 0) {
            return steps + 2;
        } else if (Boolean.TRUE.equals(hasApple.get(node))) {
            return 2;
        } else {
            return 0;
        }
    }
}