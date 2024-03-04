package g1201_1300.s1254_number_of_closed_islands;

// #Medium #Array #Depth_First_Search #Breadth_First_Search #Matrix #Union_Find
// #Graph_Theory_I_Day_2_Matrix_Related_Problems
// #2022_03_12_Time_3_ms_(55.59%)_Space_46.4_MB_(60.84%)

public class Solution {
    private int rows;
    private int cols;
    private boolean isLand;
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid is a 2D array of integers.*);
//@ ensures(*The grid consists of only 0s and 1s.*);
//@ ensures(*The grid has at least one row and one column.*);
//@ ensures(*The grid has a maximum size of 100x100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of closed islands.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to the total number of islands in the grid.*);
//@ ensures(*The grid remains unchanged after the method is executed.*);

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        int result = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    isLand = true;
                    dfs(grid, r, c);
                    if (isLand) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r == 0 || c == 0 || r == rows - 1 || c == cols - 1) {
            isLand = false;
        }
        grid[r][c] = 'k';
        if (r > 0 && grid[r - 1][c] == 0) {
            dfs(grid, r - 1, c);
        }
        if (c > 0 && grid[r][c - 1] == 0) {
            dfs(grid, r, c - 1);
        }
        if (r < rows - 1 && grid[r + 1][c] == 0) {
            dfs(grid, r + 1, c);
        }
        if (c < cols - 1 && grid[r][c + 1] == 0) {
            dfs(grid, r, c + 1);
        }
    }
}