package g0801_0900.s0840_magic_squares_in_grid;

// #Medium #Array #Math #Matrix #2022_03_24_Time_0_ms_(100.00%)_Space_40.3_MB_(69.71%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*1. The input grid must not be null.*);
	//@ requires(*2. The input grid must have at least 3 rows and 3 columns.*);
	//@ requires(*3. The input grid must contain integers.*);
	//@ requires(*4. The integers in the input grid must be between 0 and 15 (inclusive).*);
	//@ ensures(*1. The method returns an integer representing the number of 3x3 magic square subgrids found in the input grid.*);
	//@ ensures(*2. The method does not modify the input grid.*);
	//@ ensures(*3. The method handles the case when there are no 3x3 magic square subgrids in the input grid and returns 0.*);
    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                Set<Integer> set = new HashSet<>();
                int sum = grid[i][j] + grid[i][j + 1] + grid[i][j + 2];
                if (sum == grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2]
                        && sum == grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2]
                        && sum == grid[i][j] + grid[i + 1][j] + grid[i + 2][j]
                        && sum == grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1]
                        && sum == grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2]
                        && sum == grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2]
                        && sum == grid[i][j + 2] + grid[i + 1][j + 1] + grid[i + 2][j]
                        && set.add(grid[i][j])
                        && isLegit(grid[i][j])
                        && set.add(grid[i][j + 1])
                        && isLegit(grid[i][j + 1])
                        && set.add(grid[i][j + 2])
                        && isLegit(grid[i][j + 2])
                        && set.add(grid[i + 1][j])
                        && isLegit(grid[i + 1][j])
                        && set.add(grid[i + 1][j + 1])
                        && isLegit(grid[i + 1][j + 1])
                        && set.add(grid[i + 1][j + 2])
                        && isLegit(grid[i + 1][j + 2])
                        && set.add(grid[i + 2][j])
                        && isLegit(grid[i + 2][j])
                        && set.add(grid[i + 2][j + 1])
                        && isLegit(grid[i + 2][j + 1])
                        && set.add(grid[i + 2][j + 2])
                        && isLegit(grid[i + 2][j + 2])) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isLegit(int num) {
        return num <= 9 && num >= 1;
    }
}