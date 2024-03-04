package g2901_3000.s2973_find_number_of_coins_to_place_in_tree_nodes;

// #Hard #Dynamic_Programming #Sorting #Depth_First_Search #Tree #Heap_Priority_Queue
// #2024_01_16_Time_93_ms_(72.11%)_Space_63.4_MB_(33.51%)

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    private long[] result;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `edges` is not null.*);
//@ ensures(*The input array `cost` is not null.*);
//@ ensures(*The length of `edges` is equal to `n - 1`, where `n` is the number of nodes.*);
//@ ensures(*The length of `cost` is equal to `n`.*);
//@ ensures(*The values in `edges` are valid node indices, ranging from `0` to `n - 1`.*);
//@ ensures(*The values in `cost` are valid cost values, ranging from `-10^4` to `10^4`.*);
//@ ensures(*The input tree is undirected and rooted at node `0`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `coin` is not null.*);
//@ ensures(*The length of `coin` is equal to `n`.*);
//@ ensures(*The values in `coin` represent the number of coins placed at each node.*);
//@ ensures(*For each node `i` in the tree:*);
//@ ensures(*  - If the size of the subtree of node `i` is less than `3`, `coin[i]` is equal to `1`.*);
//@ ensures(*  - Otherwise, `coin[i]` is equal to the maximum product of cost values assigned to `3` distinct nodes in the subtree of node `i`. If this product is negative, `coin[i]` is equal to `0`.*);

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }
        result = new long[n];
        dp(g, cost, 0, -1);
        return result;
    }

    private static class PQX {
        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;
    }

    private PQX dp(List<List<Integer>> g, int[] cost, int i, int p) {
        if (i >= g.size()) {
            PQX pqx = new PQX();
            pqx.max = new PriorityQueue<>((a, b) -> b - a);
            pqx.min = new PriorityQueue<>(Comparator.comparingInt(a -> a));
            return pqx;
        }
        List<Integer> next = g.get(i);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        if (cost[i] > 0) {
            pq.add(cost[i]);
        } else {
            pq2.add(cost[i]);
        }
        for (int ne : next) {
            if (ne != p) {
                PQX r = dp(g, cost, ne, i);
                while (!r.min.isEmpty()) {
                    int a = r.min.poll();
                    pq2.add(a);
                }
                while (!r.max.isEmpty()) {
                    int a = r.max.poll();
                    pq.add(a);
                }
            }
        }
        if (pq.size() + pq2.size() < 3) {
            result[i] = 1;
        } else {
            int a = !pq.isEmpty() ? pq.poll() : 0;
            int b = !pq.isEmpty() ? pq.poll() : 0;
            int c = !pq.isEmpty() ? pq.poll() : 0;
            int aa = !pq2.isEmpty() ? pq2.poll() : 0;
            int bb = !pq2.isEmpty() ? pq2.poll() : 0;
            result[i] = Math.max(0, (long) a * b * c);
            result[i] = Math.max(result[i], Math.max(0, (long) a * aa * bb));
            pq = new PriorityQueue<>((x, y) -> y - x);
            pq.add(a);
            pq.add(b);
            pq.add(c);
            pq2 = new PriorityQueue<>(Comparator.comparingInt(x -> x));
            pq2.add(aa);
            pq2.add(bb);
        }
        PQX pqx = new PQX();
        pqx.min = pq2;
        pqx.max = pq;
        return pqx;
    }
}