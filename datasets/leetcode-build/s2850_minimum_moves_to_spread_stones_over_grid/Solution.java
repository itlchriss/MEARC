package g2801_2900.s2850_minimum_moves_to_spread_stones_over_grid;

// #Medium #Array #Dynamic_Programming #Breadth_First_Search #Matrix
// #2023_12_15_Time_1_ms_(100.00%)_Space_40.5_MB_(89.16%)

public class Solution {
	//@ requires(*The input grid is a 2D integer matrix of size 3x- The grid contains exactly 9 stones.*);
	//@ requires(*Each cell in the grid can have multiple stones.*);
	//@ requires(*The sum of all elements in the grid is equal to*);
	//@ ensures(*The method returns an integer representing the minimum number of moves required to place one stone in each cell of the grid.*);
    public int minimumMoves(int[][] grid) {
        int a = grid[0][0] - 1;
        int b = grid[0][1] - 1;
        int c = grid[0][2] - 1;
        int d = grid[1][0] - 1;
        int f = grid[1][2] - 1;
        int g = grid[2][0] - 1;
        int h = grid[2][1] - 1;
        int i = grid[2][2] - 1;
        int minCost = Integer.MAX_VALUE;
        for (int x = Math.min(a, 0); x <= Math.max(a, 0); x++) {
            for (int y = Math.min(c, 0); y <= Math.max(c, 0); y++) {
                for (int z = Math.min(i, 0); z <= Math.max(i, 0); z++) {
                    for (int t = Math.min(g, 0); t <= Math.max(g, 0); t++) {
                        int cost =
                                Math.abs(x)
                                        + Math.abs(y)
                                        + Math.abs(z)
                                        + Math.abs(t)
                                        + Math.abs(x - a)
                                        + Math.abs(y - c)
                                        + Math.abs(z - i)
                                        + Math.abs(t - g)
                                        + Math.abs(x - y + b + c)
                                        + Math.abs(y - z + i + f)
                                        + Math.abs(z - t + g + h)
                                        + Math.abs(t - x + a + d);
                        if (cost < minCost) {
                            minCost = cost;
                        }
                    }
                }
            }
        }
        return minCost;
    }
}