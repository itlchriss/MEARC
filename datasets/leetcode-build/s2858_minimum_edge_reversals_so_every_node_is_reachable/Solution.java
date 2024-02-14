package g2801_2900.s2858_minimum_edge_reversals_so_every_node_is_reachable;

// #Hard #Dynamic_Programming #Depth_First_Search #Breadth_First_Search #Graph
// #2023_12_19_Time_52_ms_(92.31%)_Space_119.5_MB_(75.38%)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*1. The input integer `n` must be greater than or equal to 2.*);
	//@ requires(*2. The length of the `edges` array must be equal to `n - 1`.*);
	//@ requires(*3. Each element `edges[i]` in the `edges` array must be an array of length 2.*);
	//@ requires(*4. The first element `edges[i][0]` in each `edges[i]` array must be a valid node label, i.e., it must be greater than or equal to 0 and less than `n`.*);
	//@ requires(*5. The second element `edges[i][1]` in each `edges[i]` array must be a valid node label, i.e., it must be greater than or equal to 0 and less than `n`.*);
	//@ requires(*6. The first and second elements `edges[i][0]` and `edges[i][1]` in each `edges[i]` array must be different, i.e., they must not be equal.*);
	//@ ensures(*1. The returned array `answer` must be an array of length `n`.*);
	//@ ensures(*2. Each element `answer[i]` in the `answer` array must be an integer.*);
	//@ ensures(*3. The values of `answer[i]` must represent the minimum number of edge reversals required to reach any other node starting from node `i`.*);
	//@ ensures(*4. The values of `answer[i]` must be non-negative.*);
    public int[] minEdgeReversals(int n, int[][] edges) {
        List<int[]>[] nexts = new List[n];
        for (int i = 0; i < n; i++) {
            nexts[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            nexts[u].add(new int[] {1, v});
            nexts[v].add(new int[] {-1, u});
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = -1;
        }
        res[0] = dfs(nexts, 0, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer index = queue.remove();
            int val = res[index];
            List<int[]> next = nexts[index];
            for (int[] node : next) {
                if (res[node[1]] == -1) {
                    if (node[0] == 1) {
                        res[node[1]] = val + 1;
                    } else {
                        res[node[1]] = val - 1;
                    }
                    queue.add(node[1]);
                }
            }
        }
        return res;
    }

    private int dfs(List<int[]>[] nexts, int index, int pre) {
        int res = 0;
        List<int[]> next = nexts[index];
        for (int[] node : next) {
            if (node[1] != pre) {
                if (node[0] == -1) {
                    res++;
                }
                res += dfs(nexts, node[1], index);
            }
        }
        return res;
    }
}