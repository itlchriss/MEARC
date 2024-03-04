package g2101_2200.s2132_stamping_the_grid;

// #Hard #Array #Greedy #Matrix #Prefix_Sum #2022_06_06_Time_7_ms_(100.00%)_Space_86.8_MB_(81.21%)

public class Solution {
    private boolean canPaved(int[][] grid, int is, int js, int ie, int je) {
        for (int i = is; i <= ie; i++) {
            for (int j = js; j <= je; j++) {
                if (grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input `grid` must be a valid binary matrix with dimensions `m x n`.*);
//@ ensures(*2. The input `h` and `w` must be positive integers representing the height and width of the stamp, respectively.*);
//@ ensures(*3. The stamp dimensions `h` and `w` must be smaller than or equal to the dimensions of the grid `m` and `n`, respectively.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return a boolean value indicating whether it is possible to fit the stamps while following the given restrictions and requirements.*);
//@ ensures(*2. If it is possible to fit the stamps, the method should return `true`.*);
//@ ensures(*3. If it is not possible to fit the stamps, the method should return `false`.*);

    public boolean possibleToStamp(int[][] grid, int h, int w) {
        int rl = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            int prev = -1;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0) {
                    if (j + 1 < rl
                            && row[j + 1] == 1
                            && j - w + 1 >= 0
                            && i + 1 < grid.length
                            && grid[i + 1][j] == 1
                            && i - h + 1 >= 0
                            && canPaved(grid, i - h + 1, j - w + 1, i, j)) {
                        return false;
                    }
                    continue;
                }
                if (1 < j - prev && j - prev <= w) {
                    return false;
                }
                prev = j;
            }
            if (1 < row.length - prev && row.length - prev <= w) {
                return false;
            }
        }
        for (int i = 0; i < rl; i++) {
            int prev = -1;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 0) {
                    continue;
                }
                if (1 < j - prev && j - prev <= h) {
                    return false;
                }
                prev = j;
            }
            if (1 < grid.length - prev && grid.length - prev <= h) {
                return false;
            }
        }
        return true;
    }
}