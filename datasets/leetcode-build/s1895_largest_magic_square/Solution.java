package g1801_1900.s1895_largest_magic_square;

// #Medium #Array #Matrix #Prefix_Sum #2022_05_03_Time_7_ms_(80.72%)_Space_45.1_MB_(62.65%)

public class Solution {
	//@ requires(*The input grid must not be null.*);
	//@ requires(*The input grid must have at least one row and one column.*);
	//@ requires(*The input grid must have a valid size, where the number of rows is equal to `m` and the number of columns is equal to `n`.*);
	//@ requires(*The values in the input grid must be integers.*);
	//@ requires(*The values in the input grid must be within the range of 1 to 10^*);
	//@ ensures(*The method should return an integer representing the size of the largest magic square found within the grid.*);
	//@ ensures(*The returned size should be greater than or equal to The returned size should be less than or equal to the minimum of `m` and `n`.*);
	//@ ensures(*The returned size should be the side length of the largest magic square found within the grid.*);
	//@ ensures(*The largest magic square found within the grid should have equal row sums, column sums, and diagonal sums.*);
	//@ ensures(*The row sums, column sums, and diagonal sums of the largest magic square found within the grid should all be equal to each other.*);
	//@ ensures(*The largest magic square found within the grid should be a valid magic square, where every row sum, column sum, and diagonal sum is equal.*);
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rows = new int[m][n + 1];
        int[][] cols = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // cumulative sum for each row
                rows[i][j + 1] = rows[i][j] + grid[i][j];
                // cumulative sum for each column
                cols[i + 1][j] = cols[i][j] + grid[i][j];
            }
        }
        // start with the biggest side possible
        for (int side = Math.min(m, n); side > 1; side--) {
            // check every square
            for (int i = 0; i <= m - side; i++) {
                for (int j = 0; j <= n - side; j++) {
                    // checks if a square with top left [i, j] and side length is magic
                    if (magic(grid, rows, cols, i, j, side)) {
                        return side;
                    }
                }
            }
        }
        return 1;
    }

    private boolean magic(int[][] grid, int[][] rows, int[][] cols, int r, int c, int side) {
        int sum = rows[r][c + side] - rows[r][c];
        int d1 = 0;
        int d2 = 0;
        for (int k = 0; k < side; k++) {
            d1 += grid[r + k][c + k];
            d2 += grid[r + side - 1 - k][c + k];
            // check each row and column
            if (rows[r + k][c + side] - rows[r + k][c] != sum
                    || cols[r + side][c + k] - cols[r][c + k] != sum) {
                return false;
            }
        }
        // checks both diagonals
        return d1 == sum && d2 == sum;
    }
}