package g2901_3000.s2920_maximum_points_after_collecting_coins_from_all_nodes;

// #Hard #Array #Dynamic_Programming #Depth_First_Search #Tree #Bit_Manipulation
// #2023_12_29_Time_113_ms_(77.94%)_Space_111.3_MB_(91.50%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
    private List<Integer>[] adjList;
    private int[] coins;
    private int k;
    private int[][] dp;

    private void init(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        adjList = new List[n];
        for (int v = 0; v < n; ++v) {
            adjList[v] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }
        this.coins = coins;
        this.k = k;
        dp = new int[n][14];
        for (int v = 0; v < n; ++v) {
            for (int numOfWay2Parents = 0; numOfWay2Parents < 14; ++numOfWay2Parents) {
                dp[v][numOfWay2Parents] = -1;
            }
        }
    }

    private int rec(int v, int p, int numOfWay2Parents) {
        if (numOfWay2Parents >= 14) {
            return 0;
        }
        if (dp[v][numOfWay2Parents] == -1) {
            int coinsV = coins[v] / (1 << numOfWay2Parents);
            int s0 = coinsV - k;
            int s1 = coinsV / 2;
            for (int child : adjList[v]) {
                if (child != p) {
                    s0 += rec(child, v, numOfWay2Parents);
                    s1 += rec(child, v, numOfWay2Parents + 1);
                }
            }
            dp[v][numOfWay2Parents] = Math.max(s0, s1);
        }
        return dp[v][numOfWay2Parents];
    }
	//@ requires(*The input array `edges` must have a length of `n - 1`, where `n` is the number of nodes in the tree.*);
	//@ requires(*The input array `coins` must have a length of `n`.*);
	//@ requires(*The input integer `k` must be between 0 and 10^- The tree must be undirected and rooted at node - The nodes in the tree must be labeled from 0 to `n - 1`.*);
	//@ requires(*The input array `coins` must contain non-negative integers.*);
	//@ ensures(*The method should return an integer representing the maximum points that can be obtained after collecting the coins from all the tree nodes.*);
	//@ ensures(*Behavioural requirements:*);
	//@ ensures(*The method should collect all the coins from each node in the tree.*);
	//@ ensures(*The method should collect the coins in a way that ensures the coins at a node can only be collected if the coins of its ancestors have been already collected.*);
	//@ ensures(*If the coins at a node are collected using the first way, the method should subtract `k` from the number of coins and add the result to the total points.*);
	//@ ensures(*If the coins at a node are collected using the first way and the result is negative, the method should subtract the absolute value of the result from the total points.*);
	//@ ensures(*If the coins at a node are collected using the second way, the method should divide the number of coins by 2 and add the floor value to the total points.*);
	//@ ensures(*If the coins at a node are collected using the second way, the method should recursively apply the second way to all the nodes in the subtree of the current node.*);
	//@ ensures(*The method should return the maximum points that can be obtained after collecting the coins from all the tree nodes.*);

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        init(edges, coins, k);
        return rec(0, -1, 0);
    }
}