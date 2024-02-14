package g0701_0800.s0797_all_paths_from_source_to_target;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Backtracking
// #Algorithm_II_Day_8_Breadth_First_Search_Depth_First_Search
// #Graph_Theory_I_Day_7_Standard_Traversal #2022_03_26_Time_2_ms_(90.53%)_Space_55.3_MB_(32.03%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> res;
	//@ requires(*1. The input graph must be a directed acyclic graph (DAG).*);
	//@ requires(*2. The graph must have at least 2 nodes.*);
	//@ requires(*3. The nodes in the graph must be labeled from 0 to n - 1.*);
	//@ requires(*4. The graph must not contain any self-loops (graph[i][j] != i).*);
	//@ requires(*5. All elements in each graph[i] list must be unique.*);
	//@ ensures(*1. The method should return a list of lists, where each inner list represents a path from node 0 to node n - 1.*);
	//@ ensures(*2. The order of the paths in the returned list can be arbitrary.*);
	//@ ensures(*3. Each path in the returned list should be represented as a list of integers.*);
	//@ ensures(*4. The first element of each path should be 0, and the last element should be n - 1.*);
	//@ ensures(*5. Each path in the returned list should be a valid path in the input graph, following the directed edges.*);
	//@ ensures(*6. The returned list should contain all possible paths from node 0 to node n - 1.*);

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        // perform DFS
        solve(graph, temp, 0);
        return res;
    }

    private void solve(int[][] graph, List<Integer> temp, int lastNode) {
        if (lastNode == graph.length - 1) {
            res.add(new ArrayList<>(temp));
        }
        for (int link : graph[lastNode]) {
            temp.add(link);
            solve(graph, temp, link);
            temp.remove(temp.size() - 1);
        }
    }
}