package g0301_0400.s0310_minimum_height_trees;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Topological_Sort
// #2022_07_07_Time_25_ms_(93.99%)_Space_72.4_MB_(71.31%)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*The input `n` is a positive integer representing the number of nodes in the tree.*);
	//@ requires(*The input `edges` is a 2D array of size `n-1` where each element `edges[i]` is an array of size 2 representing an undirected edge between two nodes `a_i` and `b_i` in the tree.*);
	//@ requires(*The input tree is a connected graph without simple cycles.*);
	//@ requires(*The input tree is guaranteed to be a tree and there will be no repeated edges.*);
	//@ ensures(*The method returns a list of integers representing the root labels of all minimum height trees (MHTs) in the tree.*);
	//@ ensures(*The order of the root labels in the returned list can be arbitrary.*);
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> answer = new ArrayList<>();
        if (n == 1) {
            answer.add(0);
            return answer;
        }
        List<Integer>[] graph = new ArrayList[n];
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> leaf = new LinkedList<>();
        int remainingNodes = n;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                leaf.add(i);
            }
        }
        while (!leaf.isEmpty()) {
            // We found answer if queue left with <=2 elements
            if (remainingNodes <= 2) {
                break;
            }
            int size = leaf.size();
            while (--size >= 0) {
                int l = leaf.poll();
                remainingNodes--;
                for (int adj : graph[l]) {
                    // edge is removed so degree will decrease for both vertex
                    degree[adj]--;
                    if (degree[adj] == 1) {
                        leaf.add(adj);
                    }
                }
            }
        }
        answer.addAll(leaf);
        return answer;
    }
}