package g1501_1600.s1514_path_with_maximum_probability;

// #Medium #Heap_Priority_Queue #Graph #Shortest_Path
// #2022_04_09_Time_31_ms_(93.10%)_Space_51.7_MB_(98.80%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*The number of nodes `n` is greater than or equal to 2.*);
	//@ requires(*The start node `start` is a valid node in the graph (0 <= start < n).*);
	//@ requires(*The end node `end` is a valid node in the graph (0 <= end < n).*);
	//@ requires(*The start node `start` is not the same as the end node `end`.*);
	//@ requires(*The edge list `edges` is not empty.*);
	//@ requires(*The length of `edges` is equal to the length of `succProb`.*);
	//@ requires(*The length of `succProb` is greater than or equal to 0.*);
	//@ requires(*The length of `succProb` is less than or equal to 2*10^4.*);
	//@ requires(*The probability values in `succProb` are between 0 and 1 (inclusive).*);
	//@ requires(*There is at most one edge between every two nodes.*);
	//@ ensures(*If there is a path from `start` to `end`, return the maximum success probability of that path.*);
	//@ ensures(*If there is no path from `start` to `end`, return 0.*);
	//@ ensures(*The returned success probability is accurate up to a precision of 1e-5.*);
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Integer>[] nodeToNodesList = new List[n];
        List<Double>[] nodeToProbabilitiesList = new List[n];
        for (int i = 0; i < n; i++) {
            nodeToNodesList[i] = new ArrayList<>();
            nodeToProbabilitiesList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double w = succProb[i];
            nodeToNodesList[u].add(v);
            nodeToProbabilitiesList[u].add(w);
            nodeToNodesList[v].add(u);
            nodeToProbabilitiesList[v].add(w);
        }
        double[] probabilities = new double[n];
        probabilities[start] = 1.0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited[u] = false;
            for (int i = 0; i < nodeToNodesList[u].size(); i++) {
                int v = nodeToNodesList[u].get(i);
                double w = nodeToProbabilitiesList[u].get(i);
                if (probabilities[u] * w > probabilities[v]) {
                    probabilities[v] = probabilities[u] * w;
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }
        }
        return probabilities[end];
    }
}