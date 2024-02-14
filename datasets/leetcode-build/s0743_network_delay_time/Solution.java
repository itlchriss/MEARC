package g0701_0800.s0743_network_delay_time;

// #Medium #Depth_First_Search #Breadth_First_Search #Heap_Priority_Queue #Graph #Shortest_Path
// #2022_03_25_Time_3_ms_(99.87%)_Space_61.2_MB_(75.30%)

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	//@ requires(*The input `times` is a non-empty array of length `times.length`.*);
	//@ requires(*Each element in `times` is an array of length 3.*);
	//@ requires(*The input `n` is an integer greater than or equal to 1.*);
	//@ requires(*The input `k` is an integer between 1 and `n`, inclusive.*);
	//@ requires(*The values of `u_i` and `v_i` in each element of `times` are integers between 1 and `n`, inclusive.*);
	//@ requires(*The value of `w_i` in each element of `times` is an integer between 0 and 100, inclusive.*);
	//@ requires(*All pairs `(u_i, v_i)` in `times` are unique.*);
	//@ ensures(*The method returns an integer representing the time it takes for all `n` nodes to receive the signal.*);
	//@ ensures(*If it is impossible for all `n` nodes to receive the signal, the method returns -1.*);
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n + 1][n + 1];
        for (int[] g : graph) {
            Arrays.fill(g, -1);
        }
        for (int[] t : times) {
            graph[t[0]][t[1]] = t[2];
        }
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        Queue<Integer> spfa = new LinkedList<>();
        spfa.add(k);
        visited[k] = true;
        while (!spfa.isEmpty()) {
            int curr = spfa.poll();
            visited[curr] = false;
            for (int i = 1; i <= n; i++) {
                if (graph[curr][i] != -1 && dist[i] > dist[curr] + graph[curr][i]) {
                    dist[i] = dist[curr] + graph[curr][i];
                    if (!visited[i]) {
                        spfa.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(dist[i], result);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}