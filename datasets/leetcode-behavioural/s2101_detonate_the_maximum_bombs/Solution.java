package g2101_2200.s2101_detonate_the_maximum_bombs;

// #Medium #Array #Math #Depth_First_Search #Breadth_First_Search #Graph #Geometry
// #2022_05_31_Time_27_ms_(94.17%)_Space_49.6_MB_(48.45%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `bombs` is a 2D integer array.*);
//@ ensures(*The length of `bombs` is between 1 and 100.*);
//@ ensures(*Each element in `bombs` is an array of length 3.*);
//@ ensures(*The values of `x_i`, `y_i`, and `r_i` in each element of `bombs` are between 1 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum number of bombs that can be detonated.*);
//@ ensures(*The method detonates a single bomb.*);
//@ ensures(*When a bomb is detonated, it detonates all bombs that lie within its range.*);
//@ ensures(*The detonation of bombs continues recursively, detonating bombs within the range of the initially detonated bombs.*);
//@ ensures(*The method considers all possible combinations of detonating a single bomb and calculates the maximum number of bombs that can be detonated.*);
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = bombs[i][0] - (double) bombs[j][0];
                double dy = bombs[i][1] - (double) bombs[j][1];
                double r1 = bombs[i][2];
                double r2 = bombs[j][2];
                double dist = dx * dx + dy * dy;
                if (dist <= r1 * r1) {
                    graph[i].add(j);
                }
                if (dist <= r2 * r2) {
                    graph[j].add(i);
                }
            }
        }
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(graph, i, visited));
            if (ans == n) {
                return ans;
            }
            Arrays.fill(visited, false);
        }
        return ans;
    }

    private int dfs(List<Integer>[] graph, int i, boolean[] visited) {
        int cc = 0;
        if (visited[i]) {
            return 0;
        }
        visited[i] = true;
        for (int neigh : graph[i]) {
            cc += dfs(graph, neigh, visited);
        }
        return cc + 1;
    }
}