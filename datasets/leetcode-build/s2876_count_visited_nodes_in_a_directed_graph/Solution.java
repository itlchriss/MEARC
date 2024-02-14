package g2801_2900.s2876_count_visited_nodes_in_a_directed_graph;

// #Hard #Dynamic_Programming #Graph #Memoization
// #2023_12_22_Time_36_ms_(93.33%)_Space_76.9_MB_(25.00%)

import java.util.List;

public class Solution {
	//@ requires(*The input `edges` is not null.*);
	//@ requires(*The length of `edges` is equal to `n`.*);
	//@ requires(*The values in `edges` are between 0 and `n-1`.*);
	//@ requires(*The values in `edges` are distinct.*);
	//@ ensures(*The output `answer` is not null.*);
	//@ ensures(*The length of `answer` is equal to `n`.*);
	//@ ensures(*The values in `answer` are non-negative integers.*);
	//@ ensures(*The values in `answer` represent the number of different nodes visited starting from each node.*);
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        boolean[] visited = new boolean[n];
        int[] ans = new int[n];
        int[] level = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visit(edges, 0, i, ans, visited, level);
            }
        }
        return ans;
    }

    private int[] visit(
            List<Integer> edges, int count, int curr, int[] ans, boolean[] visited, int[] level) {
        if (ans[curr] != 0) {
            return new int[] {-1, ans[curr]};
        }
        if (visited[curr]) {
            return new int[] {level[curr], count - level[curr]};
        }
        level[curr] = count;
        visited[curr] = true;
        int[] ret = visit(edges, count + 1, edges.get(curr), ans, visited, level);
        if (ret[0] == -1 || count < ret[0]) {
            ret[1]++;
        }
        ans[curr] = ret[1];
        return ret;
    }
}