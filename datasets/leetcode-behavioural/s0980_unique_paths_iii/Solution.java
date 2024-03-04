package g0901_1000.s0980_unique_paths_iii;

// #Hard #Array #Matrix #Bit_Manipulation #Backtracking
// #2022_03_31_Time_0_ms_(100.00%)_Space_39.3_MB_(98.32%)

public class Solution {
    private final int[] row = {0, 0, 1, -1};
    private final int[] col = {1, -1, 0, 0};

    private int isSafe(int[][] grid, int rows, int cols, int i, int j) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == -1) {
            return 0;
        }
        if (grid[i][j] == 2) {
            for (int l = 0; l < rows; l++) {
                for (int m = 0; m < cols; m++) {
                    if (grid[l][m] == 0) {
                        /* Return 0 if all zeros in the path are not covered */
                        return 0;
                    }
                }
            }
            /* Return 1, as we covered all zeros in the path */
            return 1;
        }
        /* mark as visited */
        grid[i][j] = -1;
        int result = 0;
        for (int k = 0; k < 4; k++) {
            /* travel in all four directions (up,down,right,left) */
            result = result + isSafe(grid, rows, cols, (i + row[k]), (j + col[k]));
        }
        /* Mark unvisited again to backtrack */
        grid[i][j] = 0;
        return result;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must be a 2D integer array.*);
//@ ensures(*The grid must have at least one row and one column.*);
//@ ensures(*The grid must have exactly one starting square with a value of - The grid must have exactly one ending square with a value of - The grid may contain empty squares with a value of - The grid may contain obstacles with a value of -*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.*);
//@ ensures(*If there is no path that walks over every empty square exactly once, the method should return 0.*);

    public int uniquePathsIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;
        for (int k = 0; k < rows; k++) {
            for (int m = 0; m < cols; m++) {
                if (grid[k][m] == 1) {
                    /* find indexes where 1 is located and start covering paths */
                    result = isSafe(grid, rows, cols, k, m);
                    break;
                }
            }
        }
        return result;
    }
}