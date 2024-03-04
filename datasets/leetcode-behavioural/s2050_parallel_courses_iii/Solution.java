package g2001_2100.s2050_parallel_courses_iii;

// #Hard #Dynamic_Programming #Graph #Topological_Sort
// #2022_05_26_Time_33_ms_(86.04%)_Space_127.1_MB_(28.87%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` represents the number of courses, and it must be a positive integer.*);
//@ ensures(*The input 2D integer array `relations` represents the prerequisite relationships between courses. Each element `relations[j]` must be an array of length 2, where `relations[j][0]` and `relations[j][1]` represent the previous course and the next course respectively. The values of `relations[j][0]` and `relations[j][1]` must be integers between 1 and `n`, inclusive.*);
//@ ensures(*The input integer array `time` represents the time required to complete each course. The length of `time` must be equal to `n`, and each element `time[i]` must be a positive integer between 1 and 10,000, inclusive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of months needed to complete all the courses.*);
//@ ensures(*The returned value must be a positive integer.*);
//@ ensures(*The method does not modify the input arrays `relations` and `time`.*);
    public int minimumTime(int n, int[][] relations, int[] time) {
        int v = time.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        int[] indegree = new int[v];
        int[] requiredTime = new int[v];
        for (int[] relation : relations) {
            List<Integer> vertices = adj.get(relation[0] - 1);
            vertices.add(relation[1] - 1);
            indegree[relation[1] - 1]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                requiredTime[i] = time[i];
            }
        }

        while (!q.isEmpty()) {
            int vertex = q.poll();
            List<Integer> edges = adj.get(vertex);
            for (Integer e : edges) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    q.add(e);
                }
                int totalTime = time[e] + requiredTime[vertex];
                if (requiredTime[e] < totalTime) {
                    requiredTime[e] = totalTime;
                }
            }
        }
        int maxMonth = 0;
        for (int i = 0; i < n; i++) {
            maxMonth = Math.max(maxMonth, requiredTime[i]);
        }
        return maxMonth;
    }
}