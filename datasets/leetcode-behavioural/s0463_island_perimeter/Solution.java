package g0401_0500.s0463_island_perimeter;

// #Easy #Array #Depth_First_Search #Breadth_First_Search #Matrix
// #2022_07_19_Time_9_ms_(81.80%)_Space_62.9_MB_(23.26%)

public class Solution {
//@ ensures(*The integer 2D array parameter `grid` must not be null.*);
//@ ensures(*The integer result is the perimeter of the island represented by the integer 2D array parameter `grid`.*);
//@ ensures(*The perimeter is calculated by counting the number of edges of land cells that are adjacent to water cells or the boundary of the grid.*);
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