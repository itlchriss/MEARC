package g1901_2000.s1971_find_if_path_exists_in_graph;

// #Easy #Depth_First_Search #Breadth_First_Search #Graph
// #2022_05_21_Time_4_ms_(99.57%)_Space_137.2_MB_(81.65%)

public class Solution {
	//@ requires(*1. The value of `n` must be greater than or equal to 1.*);
	//@ requires(*2. The length of the `edges` array must be greater than or equal to 0.*);
	//@ requires(*3. Each element in the `edges` array must be an array of length 2.*);
	//@ requires(*4. The values of `u_i` and `v_i` in each element of the `edges` array must be between 0 and `n-1`, inclusive.*);
	//@ requires(*5. The value of `source` must be between 0 and `n-1`, inclusive.*);
	//@ requires(*6. The value of `destination` must be between 0 and `n-1`, inclusive.*);
	//@ ensures(*1. The method should return `true` if there is a valid path from `source` to `destination`, and `false` otherwise.*);
    public boolean validPath(int n, int[][] edges, int start, int end) {
        boolean[] visited = new boolean[n];
        visited[start] = true;
        boolean newVisit = true;
        while (!visited[end] && newVisit) {
            newVisit = false;
            for (int i = edges.length - 1; i >= 0; i--) {
                if (visited[edges[i][0]]) {
                    if (!visited[edges[i][1]]) {
                        visited[edges[i][1]] = newVisit = true;
                    }
                } else if (visited[edges[i][1]]) {
                    visited[edges[i][0]] = newVisit = true;
                }
            }
        }

        return visited[end];
    }
}