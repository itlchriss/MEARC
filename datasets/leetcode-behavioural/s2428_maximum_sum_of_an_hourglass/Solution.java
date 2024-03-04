package g2401_2500.s2428_maximum_sum_of_an_hourglass;

// #Medium #Array #Matrix #Prefix_Sum #2022_12_07_Time_4_ms_(93.51%)_Space_44.1_MB_(86.28%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input grid must not be null.*);
//@ ensures(*2. The input grid must have at least 3 rows and 3 columns.*);
//@ ensures(*3. The input grid must have a maximum of 150 rows and 150 columns.*);
//@ ensures(*4. The values in the input grid must be non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns an integer representing the maximum sum of the elements of an hourglass.*);
//@ ensures(*2. The returned sum is the sum of 7 elements in the hourglass shape.*);
//@ ensures(*3. The hourglass must be entirely contained within the matrix.*);
//@ ensures(*4. The hourglass cannot be rotated.*);
    public int maxSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isHourGlass(i, j, m, n)) {
                    res = Math.max(res, calculate(i, j, grid));
                } else {
                    // If we cannot form an hour glass from the current row anymore, just move to
                    // the next one
                    break;
                }
            }
        }
        return res;
    }

    // Check if an hour glass can be formed from the current position
    private boolean isHourGlass(int r, int c, int m, int n) {
        return r + 2 < m && c + 2 < n;
    }

    // Once we know an hourglass can be formed, just traverse the value
    private int calculate(int r, int c, int[][] grid) {
        int sum = 0;
        // Traverse the bottom and the top row of the hour glass at the same time
        for (int i = c; i <= c + 2; i++) {
            sum += grid[r][i];
            sum += grid[r + 2][i];
        }
        // Add the middle vlaue
        sum += grid[r + 1][c + 1];
        return sum;
    }
}