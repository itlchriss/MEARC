package g2501_2600.s2556_disconnect_path_in_a_binary_matrix_by_at_most_one_flip;

// #Medium #Array #Dynamic_Programming #Depth_First_Search #Breadth_First_Search #Matrix
// #2023_08_19_Time_0_ms_(100.00%)_Space_54.4_MB_(97.73%)

public class Solution {
    private int n;
    private int m;
	//@ requires(*The input matrix `grid` is not null.*);
	//@ requires(*The input matrix `grid` has at least one row and one column.*);
	//@ requires(*The input matrix `grid` is a binary matrix, meaning it only contains the values 0 and 1.*);
	//@ requires(*The input matrix `grid` is a square matrix, meaning it has the same number of rows and columns.*);
	//@ requires(*The value of the cell at position (0, 0) is 1.*);
	//@ requires(*The value of the cell at position (m - 1, n - 1) is 1.*);
	//@ ensures(*The method returns a boolean value indicating whether it is possible to make the matrix disconnect by flipping at most one cell.*);
	//@ ensures(*If it is possible to make the matrix disconnect, the method returns true.*);
	//@ ensures(*If it is not possible to make the matrix disconnect, the method returns false.*);

    public boolean isPossibleToCutPath(int[][] g) {
        n = g.length;
        m = g[0].length;
        if (!dfs(0, 0, g)) {
            return true;
        }
        g[0][0] = 1;
        return !dfs(0, 0, g);
    }

    private boolean dfs(int r, int c, int[][] g) {
        if (r == n - 1 && c == m - 1) {
            return true;
        }
        if (r == n || c == m || g[r][c] == 0) {
            return false;
        }
        g[r][c] = 0;
        return dfs(r, c + 1, g) || dfs(r + 1, c, g);
    }
}