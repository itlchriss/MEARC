package g1701_1800.s1782_count_pairs_of_nodes;

// #Hard #Binary_Search #Two_Pointers #Graph
// #2022_04_30_Time_128_ms_(86.96%)_Space_175.4_MB_(39.13%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` represents the number of nodes in the graph.*);
//@ ensures(*The input 2D integer array `edges` represents the edges in the graph, where `edges[i] = [u_i, v_i]` indicates that there is an undirected edge between nodes `u_i` and `v_i`.*);
//@ ensures(*The input integer array `queries` represents the queries to be answered.*);
//@ ensures(*The length of `queries` is equal to the length of the expected output array `answers`.*);
//@ ensures(*The constraints on the input values are satisfied:*);
//@ ensures(*  - `2 <= n <= 2 * 10^4`*);
//@ ensures(*  - `1 <= edges.length <= 10^5`*);
//@ ensures(*  - `1 <= u_i, v_i <= n`*);
//@ ensures(*  - `u_i != v_i`*);
//@ ensures(*  - `1 <= queries.length <= 20`*);
//@ ensures(*  - `0 <= queries[j] < edges.length`*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer array `answers` of length equal to the length of `queries`.*);
//@ ensures(*Each element `answers[j]` in the output array is the answer to the `j-th` query.*);
//@ ensures(*The answer to the `j-th` query is the number of pairs of nodes `(a, b)` that satisfy both of the following conditions:*);
//@ ensures(*  - `a < b`*);
//@ ensures(*  - `incident(a, b) > queries[j]`*);
//@ ensures(*The `incident(a, b)` function is defined as the number of edges that are connected to either node `a` or `b`.*);
//@ ensures(*The calculations for `incident(a, b)` are performed based on the given graph and edges.*);
//@ ensures(*The answer to each query is calculated correctly based on the given conditions and the calculated `incident(a, b)` values.*);
//@ ensures(*The output array `answers` contains the correct answers for all the queries.*);
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        Map<Integer, Integer> edgeCount = new HashMap<>();
        int[] degree = new int[n];
        for (int[] e : edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;
            degree[u]++;
            degree[v]++;
            int eId = Math.min(u, v) * n + Math.max(u, v);
            edgeCount.put(eId, edgeCount.getOrDefault(eId, 0) + 1);
        }
        Map<Integer, Integer> degreeCount = new HashMap<>();
        int maxDegree = 0;
        for (int d : degree) {
            degreeCount.put(d, degreeCount.getOrDefault(d, 0) + 1);
            maxDegree = Math.max(maxDegree, d);
        }
        int[] count = new int[2 * maxDegree + 1];
        for (Map.Entry<Integer, Integer> d1 : degreeCount.entrySet()) {
            for (Map.Entry<Integer, Integer> d2 : degreeCount.entrySet()) {
                count[d1.getKey() + d2.getKey()] +=
                        (d1 == d2)
                                ? d1.getValue() * (d1.getValue() - 1)
                                : d1.getValue() * d2.getValue();
            }
        }
        for (int i = 0; i < count.length; i++) {
            count[i] /= 2;
        }
        for (Map.Entry<Integer, Integer> e : edgeCount.entrySet()) {
            int u = e.getKey() / n;
            int v = e.getKey() % n;
            count[degree[u] + degree[v]]--;
            count[degree[u] + degree[v] - e.getValue()]++;
        }
        for (int i = count.length - 2; i >= 0; i--) {
            count[i] += count[i + 1];
        }
        int[] res = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            res[q] = ((queries[q] + 1) >= count.length) ? 0 : count[queries[q] + 1];
        }
        return res;
    }
}