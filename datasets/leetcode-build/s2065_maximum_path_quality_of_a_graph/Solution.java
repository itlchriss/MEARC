package g2001_2100.s2065_maximum_path_quality_of_a_graph;

// #Hard #Array #Graph #Backtracking #2022_05_29_Time_94_ms_(95.34%)_Space_67.9_MB_(59.84%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int maxQuality;

    static class Node {
        int i;
        int time;
	//@ requires(*The input array `values` is not null.*);
	//@ requires(*The input array `edges` is not null.*);
	//@ requires(*The input integer `maxTime` is not negative.*);
	//@ requires(*The length of the input array `values` is equal to `n`.*);
	//@ requires(*The length of the input array `edges` is not greater than 2000.*);
	//@ requires(*The length of each subarray in the input array `edges` is equal to 3.*);
	//@ requires(*The values in the input array `edges` are valid node indices (between 0 and `n-1`).*);
	//@ requires(*The values in the input array `edges` are unique.*);
	//@ requires(*The values in the input array `values` are non-negative.*);
	//@ requires(*The values in the input array `values` are not greater than 10^8.*);
	//@ requires(*The values in the input array `edges` are valid edge weights (between 10 and 100).*);
	//@ requires(*The graph represented by the input array `edges` may not be connected.*);
	//@ ensures(*The method returns an integer representing the maximum quality of a valid path.*);
	//@ ensures(*The returned value is non-negative.*);
	//@ ensures(*The returned value is not greater than the sum of all values in the input array `values`.*);
	//@ ensures(*The returned value is the sum of the values of the unique nodes visited in the path.*);
	//@ ensures(*The path starts at node 0 and ends at node 0.*);
	//@ ensures(*The path takes at most `maxTime` seconds to complete.*);
	//@ ensures(*The path may visit the same node multiple times.*);
	//@ ensures(*The path may include cycles.*);
	//@ ensures(*The path may include any number of nodes.*);
	//@ ensures(*The path may include any number of edges.*);
	//@ ensures(*The path may include any combination of edges and nodes.*);
	//@ ensures(*The path may include any number of unique nodes.*);
	//@ ensures(*The path may include any number of non-unique nodes.*);
	//@ ensures(*The path may include any number of edges connected to each node.*);
	//@ ensures(*The path may include any combination of edges connected to each node.*);
	//@ ensures(*The path may include any number of edges with different weights.*);
	//@ ensures(*The path may include any combination of edges with different weights.*);
	//@ ensures(*The path may include any number of edges with the same weight.*);
	//@ ensures(*The path may include any combination of edges with the same weight.*);
	//@ ensures(*The path may include any number of edges with the same starting and ending nodes.*);
	//@ ensures(*The path may include any combination of edges with the same starting and ending nodes.*);
	//@ ensures(*The path may include any number of edges with the same starting and ending nodes and different weights.*);
	//@ ensures(*The path may include any combination of edges with the same starting and ending nodes and different weights.*);
	//@ ensures(*The path may include any number of edges with the same starting and ending nodes and the same weight.*);
	//@ ensures(*The path may include any combination of edges with the same starting and ending nodes and the same weight.*);

        public Node(int i, int time) {
            this.i = i;
            this.time = time;
        }
    }

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            Node node1 = new Node(u, time);
            Node node2 = new Node(v, time);
            graph.get(u).add(node2);
            graph.get(v).add(node1);
        }
        maxQuality = 0;
        dfs(graph, 0, 0, maxTime, values[0], values);

        return maxQuality;
    }

    private void dfs(
            List<List<Node>> graph,
            int start,
            int curTime,
            int maxTime,
            int curValue,
            int[] values) {
        if (curTime > maxTime) {
            return;
        }
        if (curTime == maxTime && start != 0) {
            return;
        }

        if (start == 0) {
            maxQuality = Math.max(maxQuality, curValue);
        }
        int tmp = values[start];
        if (tmp != 0) {
            values[start] = 0;
        }
        for (Node node : graph.get(start)) {
            int v = node.i;
            int time = node.time;
            int value = values[v];
            if (value != 0) {
                values[v] = 0;
            }
            dfs(graph, v, curTime + time, maxTime, curValue + value, values);
            if (value != 0) {
                values[v] = value;
            }
        }
        if (tmp != 0) {
            values[start] = tmp;
        }
    }
}