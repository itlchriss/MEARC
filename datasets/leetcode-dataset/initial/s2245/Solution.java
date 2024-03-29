package g2201_2300.s2245_maximum_trailing_zeros_in_a_cornered_path;

// #Medium #Array #Matrix #Prefix_Sum #2022_06_11_Time_96_ms_(88.49%)_Space_132_MB_(22.37%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*A **cornered path** is defined as a set of adjacent cells with **at most** one turn. More specifically, the path should exclusively move either **horizontally** or **vertically** up to the turn (if there is one), without returning to a previously visited cell. After the turn, the path will then move exclusively in the **alternate** direction: move vertically if it moved horizontally, and vice versa, also without returning to a previously visited cell. The grid on the right is not a cornered path as it requires a return to a previously visited cell.
Return _the **maximum** number of **trailing zeros** in the product of a cornered path found in_ `grid`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        int[][] row2 = new int[m + 1][n + 1];
        int[][] row5 = new int[m + 1][n + 1];
        int[][] col2 = new int[m + 1][n + 1];
        int[][] col5 = new int[m + 1][n + 1];
        int[][] factor2 = new int[m][n];
        int[][] factor5 = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int factorTwo = findFactor(grid[r][c], 2);
                int factorFive = findFactor(grid[r][c], 5);
                row2[r + 1][c + 1] = row2[r + 1][c] + factorTwo;
                row5[r + 1][c + 1] = row5[r + 1][c] + factorFive;
                col2[r + 1][c + 1] = col2[r][c + 1] + factorTwo;
                col5[r + 1][c + 1] = col5[r][c + 1] + factorFive;
                factor2[r][c] = factorTwo;
                factor5[r][c] = factorFive;
            }
        }
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int cur2 = factor2[r][c];
                int cur5 = factor5[r][c];
                int up2 = col2[r + 1][c + 1];
                int up5 = col5[r + 1][c + 1];
                int down2 = col2[m][c + 1] - col2[r][c + 1];
                int down5 = col5[m][c + 1] - col5[r][c + 1];
                int left2 = row2[r + 1][c + 1];
                int left5 = row5[r + 1][c + 1];
                int right2 = row2[r + 1][n] - row2[r + 1][c];
                int right5 = row5[r + 1][n] - row5[r + 1][c];
                max = Math.max(max, Math.min(left2 + up2 - cur2, left5 + up5 - cur5));
                max = Math.max(max, Math.min(right2 + up2 - cur2, right5 + up5 - cur5));
                max = Math.max(max, Math.min(left2 + down2 - cur2, left5 + down5 - cur5));
                max = Math.max(max, Math.min(right2 + down2 - cur2, right5 + down5 - cur5));
            }
        }
        return max;
    }

    private int findFactor(int a, int b) {
        int factors = 0;
        while (a % b == 0) {
            a /= b;
            factors++;
        }
        return factors;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
