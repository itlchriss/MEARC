package g0401_0500.s0463_island_perimeter;

// #Easy #Array #Depth_First_Search #Breadth_First_Search #Matrix
// #2022_07_19_Time_9_ms_(81.80%)_Space_62.9_MB_(23.26%)

public class Solution {
	//@ requires(*The input grid is a 2D array of integers.*);
	//@ requires(*The grid is rectangular, with width and height not exceeding 100.*);
	//@ requires(*Each element in the grid is either 0 or 1.*);
	//@ requires(*There is exactly one island in the grid.*);
	//@ ensures(*The method returns an integer representing the perimeter of the island.*);
	//@ ensures(*The perimeter is calculated by counting the number of edges surrounding the island.*);
	//@ ensures(*The island is represented by cells with a value of 1 in the grid.*);
	//@ ensures(*The perimeter does not include edges that are adjacent to water cells (cells with a value of 0).*);
	//@ ensures(*The perimeter includes edges that are adjacent to the boundary of the grid.*);
    public int islandPerimeter(int[][] grid) {
        int islands = 0;
        int neighbours = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        neighbours++;
                    }
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
                        neighbours++;
                    }
                }
            }
        }
        return 4 * islands - 2 * neighbours;
    }
}