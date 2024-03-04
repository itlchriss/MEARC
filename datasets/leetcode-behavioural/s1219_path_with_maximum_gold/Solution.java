package g1201_1300.s1219_path_with_maximum_gold;

// #Medium #Array #Matrix #Backtracking #2022_03_11_Time_48_ms_(57.00%)_Space_41.9_MB_(39.12%)

public class Solution {
    private int maxGold = 0;
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input grid must not be null.*);
//@ ensures(*2. The input grid must have at least one row and one column.*);
//@ ensures(*3. The input grid must have a maximum size of 15x15.*);
//@ ensures(*4. The values in the input grid must be non-negative integers.*);
//@ ensures(*5. The input grid must have at most 25 cells containing gold.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return an integer representing the maximum amount of gold that can be collected.*);
//@ ensures(*2. The method should not modify the input grid.*);
//@ ensures(*3. The method should consider all possible paths to collect the maximum amount of gold.*);
//@ ensures(*4. The method should only visit cells with non-zero gold values.*);
//@ ensures(*5. The method should not visit the same cell more than once.*);
//@ ensures(*6. The method should be able to start and stop collecting gold from any position in the grid that has some gold.*);

    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    int g = grid[i][j];
                    grid[i][j] = 0;
                    gold(grid, i, j, g);
                    grid[i][j] = g;
                }
            }
        }
        return maxGold;
    }

    private void gold(int[][] grid, int row, int col, int gold) {
        if (gold > maxGold) {
            maxGold = gold;
        }
        if (row > 0 && grid[row - 1][col] != 0) {
            int currGold = grid[row - 1][col];
            grid[row - 1][col] = 0;
            gold(grid, row - 1, col, gold + currGold);
            grid[row - 1][col] = currGold;
        }
        if (col > 0 && grid[row][col - 1] != 0) {
            int currGold = grid[row][col - 1];
            grid[row][col - 1] = 0;
            gold(grid, row, col - 1, gold + currGold);
            grid[row][col - 1] = currGold;
        }
        if (row < grid.length - 1 && grid[row + 1][col] != 0) {
            // flag=false;
            int currGold = grid[row + 1][col];
            grid[row + 1][col] = 0;
            gold(grid, row + 1, col, gold + currGold);
            grid[row + 1][col] = currGold;
        }
        if (col < grid[0].length - 1 && grid[row][col + 1] != 0) {
            int currGold = grid[row][col + 1];
            grid[row][col + 1] = 0;
            gold(grid, row, col + 1, gold + currGold);
            grid[row][col + 1] = currGold;
        }
    }
}