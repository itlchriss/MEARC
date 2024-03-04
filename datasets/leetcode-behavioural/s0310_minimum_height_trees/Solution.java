package g0301_0400.s0310_minimum_height_trees;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Topological_Sort
// #2022_07_07_Time_25_ms_(93.99%)_Space_72.4_MB_(71.31%)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The integer 2D array parameter `edges` must have a length of `n - 1`.*);
//@ ensures(*The integer 2D array parameter `edges` must contain distinct pairs of integers within the range of 0 to `n`.*);
//@ ensures(*The integer collection result contains the root labels of all minimum height trees.*);
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