package g2401_2500.s2497_maximum_star_sum_of_a_graph;

// #Medium #Array #Sorting #Greedy #Heap_Priority_Queue #Graph
// #2023_02_12_Time_36_ms_(97.50%)_Space_90.1_MB_(69.28%)

import java.util.PriorityQueue;

@SuppressWarnings("unchecked")
public class Solution {
    private PriorityQueue<Integer>[] graphNodeIdToNodeValues;
	//@ requires(*The input array `nodeValues` must not be null.*);
	//@ requires(*The length of the input array `nodeValues` must be equal to `n`.*);
	//@ requires(*The input array `edges` must not be null.*);
	//@ requires(*The length of the input array `edges` must be equal to the number of edges in the graph.*);
	//@ requires(*The input integer `maxNumberOfEdges` must be non-negative.*);
	//@ ensures(*The method returns an integer representing the maximum star sum of a star graph containing at most `k` edges.*);
	//@ ensures(*The returned maximum star sum is the sum of the values of all the nodes present in the star graph.*);
	//@ ensures(*The returned maximum star sum is the maximum possible sum among all star graphs with at most `k` edges.*);
	//@ ensures(*If there are no edges in the graph (i.e., `edges` is an empty array), the method returns the value of the center node (i.e., `vals[0]`).*);
	//@ ensures(*If `k` is equal to 0, the method returns the value of the center node (i.e., `vals[0]`).*);
	//@ ensures(*If `k` is equal to the number of edges in the graph, the method returns the sum of the values of all nodes in the graph (i.e., the sum of all elements in `nodeValues`).*);
	//@ ensures(*The method does not modify the input arrays `nodeValues` and `edges`.*);

    public int maxStarSum(int[] nodeValues, int[][] edges, int maxNumberOfEdges) {
        final int totalNodes = nodeValues.length;
        graphNodeIdToNodeValues = new PriorityQueue[totalNodes];
        for (int i = 0; i < totalNodes; ++i) {
            graphNodeIdToNodeValues[i] = new PriorityQueue<>();
        }
        for (int[] edge : edges) {
            addEdgeEndingWithValueOfNode(nodeValues, edge[0], edge[1], maxNumberOfEdges);
            addEdgeEndingWithValueOfNode(nodeValues, edge[1], edge[0], maxNumberOfEdges);
        }
        return calculateMaxStarSum(nodeValues, totalNodes);
    }

    private void addEdgeEndingWithValueOfNode(
            int[] nodeValues, int fromNode, int toNode, int maxNumberOfEdges) {
        if (nodeValues[toNode] > 0 && graphNodeIdToNodeValues[fromNode].size() < maxNumberOfEdges) {
            graphNodeIdToNodeValues[fromNode].add(nodeValues[toNode]);
        } else if (!graphNodeIdToNodeValues[fromNode].isEmpty()
                && graphNodeIdToNodeValues[fromNode].peek() < nodeValues[toNode]) {
            graphNodeIdToNodeValues[fromNode].poll();
            graphNodeIdToNodeValues[fromNode].add(nodeValues[toNode]);
        }
    }

    private int calculateMaxStarSum(int[] nodeValues, int totalNodes) {
        int maxStarSum = Integer.MIN_VALUE;
        for (int i = 0; i < totalNodes; ++i) {
            int sum = nodeValues[i];
            for (int value : graphNodeIdToNodeValues[i]) {
                sum += value;
            }
            maxStarSum = Math.max(maxStarSum, sum);
        }
        return maxStarSum;
    }
}