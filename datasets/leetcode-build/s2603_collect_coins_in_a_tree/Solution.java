package g2601_2700.s2603_collect_coins_in_a_tree;

// #Hard #Array #Tree #Graph #Topological_Sort
// #2023_08_29_Time_26_ms_(100.00%)_Space_66.3_MB_(32.97%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
    private int[] coins;
    private List<Integer>[] graph;
    private int sum;
    private int ret;
	//@ requires(*The input array `coins` must have a length of `n`.*);
	//@ requires(*The input array `edges` must have a length of `n - 1`.*);
	//@ requires(*Each element in the input array `coins` must be either `0` or `1`.*);
	//@ requires(*Each element in the input array `edges` must be a valid index of a node in the tree.*);
	//@ requires(*Each element in the input array `edges` must be unique.*);
	//@ requires(*The input array `edges` must represent a valid tree.*);
	//@ ensures(*The method should return an integer representing the minimum number of edges needed to collect all the coins and go back to the initial vertex.*);
	//@ ensures(*The method should return `-1` if it is not possible to collect all the coins and go back to the initial vertex.*);
	//@ ensures(*The method should return `0` if there are no coins in the tree.*);
	//@ ensures(*The method should return `0` if there is only one vertex in the tree and it has a coin.*);
	//@ ensures(*The method should return `0` if there is only one vertex in the tree and it does not have a coin.*);
	//@ ensures(*The method should return the minimum number of edges needed to collect all the coins and go back to the initial vertex, considering all possible starting vertices in the tree.*);

    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        this.coins = coins;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        for (int coin : coins) {
            sum += coin;
        }
        dfs(0, -1);
        return Math.max(2 * (ret - 1), 0);
    }

    private int dfs(int node, int pre) {
        int cnt = 0;
        int s = 0;
        for (int nn : graph[node]) {
            if (nn != pre) {
                int r = dfs(nn, node);
                if (r - coins[nn] > 0) {
                    cnt++;
                }
                s += r;
            }
        }
        if (pre != -1 && sum - s - coins[node] - coins[pre] > 0) {
            cnt++;
        }
        if (cnt >= 2) {
            ret++;
        }
        return s + coins[node];
    }
}