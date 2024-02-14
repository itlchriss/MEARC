package g1901_2000.s1905_count_sub_islands;

// #Medium #Array #Depth_First_Search #Breadth_First_Search #Matrix #Union_Find
// #Graph_Theory_I_Day_3_Matrix_Related_Problems
// #2022_05_11_Time_24_ms_(85.54%)_Space_142.6_MB_(18.19%)

public class Solution {
    private int ans = 0;
	//@ requires(*The input matrices `grid1` and `grid2` must have the same dimensions.*);
	//@ requires(*The dimensions of `grid1` and `grid2` must be within the range of 1 to 500.*);
	//@ requires(*Each element in `grid1` and `grid2` must be either 0 or 1.*);
	//@ ensures(*The method should return an integer representing the number of islands in `grid2` that are considered sub-islands.*);
	//@ ensures(*The islands in `grid2` that are considered sub-islands should be identified by coloring the corresponding cells red.*);

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1) {
                    ans = 1;
                    dfs(grid1, grid2, i, j);
                    count += ans;
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid1, int[][] grid2, int i, int j) {
        if (i < 0 || j < 0 || i >= grid1.length || j >= grid1[0].length || grid2[i][j] == 0) {
            return;
        }
        if (grid1[i][j] == 0) {
            ans = 0;
        }
        grid2[i][j] = 0;
        dfs(grid1, grid2, i - 1, j);
        dfs(grid1, grid2, i + 1, j);
        dfs(grid1, grid2, i, j + 1);
        dfs(grid1, grid2, i, j - 1);
    }
}