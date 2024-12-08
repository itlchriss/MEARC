package g2401_2500.s2500_delete_greatest_value_in_each_row;

// #Easy #Array #Sorting #Matrix #2023_02_12_Time_3_ms_(98.16%)_Space_42.6_MB_(34.09%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must not be null.*);
//@ ensures(*The input grid must have at least one row and one column.*);
//@ ensures(*The input grid must consist of positive integers.*);
//@ ensures(*The maximum value of each row in the grid must be less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output must be an integer.*);
//@ ensures(*The output must be the sum of the maximum values removed from each row.*);
    public int deleteGreatestValue(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            Arrays.sort(grid[i]);
        }
        for (int j = 0; j < grid[0].length; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            sum += max;
        }
        return sum;
    }
}