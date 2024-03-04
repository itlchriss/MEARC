package g0801_0900.s0807_max_increase_to_keep_city_skyline;

// #Medium #Array #Greedy #Matrix #2022_03_23_Time_2_ms_(23.49%)_Space_43.9_MB_(64.30%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must be a 2D integer matrix.*);
//@ ensures(*The size of the grid must be n x n, where n is greater than or equal to 2 and less than or equal to 50.*);
//@ ensures(*The height of each building in the grid must be a non-negative integer less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output must be an integer representing the maximum total sum that the height of the buildings can be increased by without changing the city's skyline from any cardinal direction.*);
//@ ensures(*The grid after increasing the height of buildings should not affect the skyline from any cardinal direction.*);
//@ ensures(*The height of any number of buildings can be increased by any amount, including 0.*);
//@ ensures(*The height of a 0-height building can also be increased.*);
    public int maxIncreaseKeepingSkyline(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int[] tallestR = new int[rows];
        int[] tallestC = new int[cols];
        int max;

        for (int i = 0; i < rows; i++) {
            max = 0;
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            tallestR[i] = max;
        }

        for (int i = 0; i < cols; i++) {
            max = 0;
            for (int[] ints : grid) {
                if (ints[i] > max) {
                    max = ints[i];
                }
            }
            tallestC[i] = max;
        }

        int increase = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tallestR[i] < tallestC[j]) {
                    increase += tallestR[i] - grid[i][j];
                    grid[i][j] += tallestR[i] - grid[i][j];
                } else {
                    increase += tallestC[j] - grid[i][j];
                    grid[i][j] += tallestC[j] - grid[i][j];
                }
            }
        }

        return increase;
    }
}