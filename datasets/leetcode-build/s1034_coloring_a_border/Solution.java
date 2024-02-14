package g1001_1100.s1034_coloring_a_border;

// #Medium #Array #Depth_First_Search #Breadth_First_Search #Matrix
// #2022_02_27_Time_1_ms_(93.85%)_Space_55.3_MB_(9.60%)

public class Solution {
	//@ requires(*The input grid is not null.*);
	//@ requires(*The input grid has at least one row and one column.*);
	//@ requires(*The input row and col are valid indices within the grid.*);
	//@ requires(*The input color is a valid color value.*);
	//@ ensures(*The border of the connected component that contains the square grid[row][col] is colored with the specified color.*);
	//@ ensures(*The rest of the grid remains unchanged.*);
	//@ ensures(*The final grid is returned.*);
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        getComp(grid, row, col, color, grid[row][col]);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }

    private int getComp(int[][] grid, int r, int c, int color, int stColor) {
        if (r < 0
                || c < 0
                || r >= grid.length
                || c >= grid[0].length
                || Math.abs(grid[r][c]) != stColor) {
            return 0;
        }
        if (grid[r][c] == -stColor) {
            return 1;
        }
        grid[r][c] = -grid[r][c];
        int count = 0;
        count += getComp(grid, r - 1, c, color, stColor);
        count += getComp(grid, r + 1, c, color, stColor);
        count += getComp(grid, r, c - 1, color, stColor);
        count += getComp(grid, r, c + 1, color, stColor);
        if (count == 4) {
            grid[r][c] = -grid[r][c];
        }
        return 1;
    }
}