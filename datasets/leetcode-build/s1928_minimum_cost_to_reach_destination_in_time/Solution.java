package g1901_2000.s1928_minimum_cost_to_reach_destination_in_time;

// #Hard #Dynamic_Programming #Graph #2022_05_15_Time_52_ms_(87.97%)_Space_43.1_MB_(82.82%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input `maxTime` is a positive integer.*);
	//@ requires(*The input `edges` is a 2D integer array where each element `edges[i]` is an array of length - The input `passingFees` is a 0-indexed integer array of length `n`.*);
	//@ requires(*The length of `passingFees` is equal to `n`, where `n` is the number of cities.*);
	//@ requires(*The input `edges` represents the roads between cities, where each road is represented as an array `[x, y, time]`.*);
	//@ requires(*The input `edges` does not contain any road that connects a city to itself.*);
	//@ requires(*The input `edges` may contain multiple roads of differing travel times connecting the same two cities.*);
	//@ requires(*The input `maxTime` represents the maximum time in minutes to reach city `n - 1`.*);
	//@ requires(*The input `passingFees[j]` represents the amount of dollars to pay when passing through city `j`.*);
	//@ requires(*The source city is city `0` and the destination city is city `n - 1`.*);
	//@ ensures(*The method returns an integer representing the minimum cost to complete the journey.*);
	//@ ensures(*If it is not possible to complete the journey within `maxTime` minutes, the method returns `-1`.*);
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        final PriorityQueue<Tuple> pq =
                new PriorityQueue<>((a, b) -> a.cost == b.cost ? a.time - b.time : a.cost - b.cost);
        final int n = passingFees.length;
        final int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        final Graph graph = new Graph();
        for (final int[] edge : edges) {
            graph.addEdge(edge[0], edge[1], edge[2]);
        }
        pq.offer(new Tuple(0, passingFees[0], 0));
        while (!pq.isEmpty()) {
            final Tuple curr = pq.poll();
            if (curr.time <= maxTime && curr.time < minTime[curr.node]) {
                minTime[curr.node] = curr.time;
                if (curr.node == n - 1) {
                    return curr.cost;
                }
                for (final Edge edge : graph.getEdges(curr.node)) {
                    final int time = curr.time + edge.weight;
                    if (time > maxTime || time >= minTime[edge.dst]) {
                        continue;
                    }
                    pq.offer(new Tuple(edge.dst, curr.cost + passingFees[edge.dst], time));
                }
            }
        }
        return -1;
    }

    private static class Graph {
        private final Map<Integer, List<Edge>> edges = new HashMap<>();

        private void addEdge(final int src, final int dst, final int weight) {
            this.edges.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(dst, weight));
            this.edges.computeIfAbsent(dst, k -> new ArrayList<>()).add(new Edge(src, weight));
        }

        private List<Edge> getEdges(final int node) {
            return this.edges.getOrDefault(node, new ArrayList<>());
        }
    }

    private static final class Edge {
        private final int dst;
        private final int weight;

        private Edge(final int dst, final int weight) {
            this.dst = dst;
            this.weight = weight;
        }
    }

    private static class Tuple {
        private final int node;
        private final int cost;
        private final int time;

        private Tuple(final int node, final int cost, final int time) {
            this.node = node;
            this.cost = cost;
            this.time = time;
        }
    }
}