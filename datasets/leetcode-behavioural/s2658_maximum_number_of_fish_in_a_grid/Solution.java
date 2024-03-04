package g2601_2700.s2658_maximum_number_of_fish_in_a_grid;

// #Medium #Array #Depth_First_Search #Breadth_First_Search #Matrix #Union_Find
// #2023_09_07_Time_3_ms_(100.00%)_Space_43.6_MB_(64.05%)

public class Solution {
    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[i].length || grid[i][j] < 1) {
            return 0;
        }
        grid[i][j] -= 20;
        return 20
                + grid[i][j]
                + dfs(grid, i + 1, j)
                + dfs(grid, i - 1, j)
                + dfs(grid, i, j - 1)
                + dfs(grid, i, j + 1);
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must be a 2D matrix of size m x n.*);
//@ ensures(*The grid must be 0-indexed.*);
//@ ensures(*Each cell in the grid can either be a land cell (0) or a water cell containing fish (greater than 0).*);
//@ ensures(*The grid can have multiple water cells.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the maximum number of fish the fisher can catch.*);
//@ ensures(*If there are no water cells in the grid, the method should return 0.*);
//@ ensures(*The fisher can start at any water cell and catch all the fish at that cell.*);
//@ ensures(*The fisher can move to any adjacent water cell.*);
//@ ensures(*The fisher can perform the above operations any number of times.*);

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }
}