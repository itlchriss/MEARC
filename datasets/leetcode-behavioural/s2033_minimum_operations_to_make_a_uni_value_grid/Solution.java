package g2001_2100.s2033_minimum_operations_to_make_a_uni_value_grid;

// #Medium #Array #Math #Sorting #Matrix #2022_05_25_Time_41_ms_(87.53%)_Space_70.1_MB_(92.62%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must not be null.*);
//@ ensures(*The input grid must have at least one row and one column.*);
//@ ensures(*The input grid must have the same number of columns for each row.*);
//@ ensures(*The value of x must be greater than or equal to - The values in the grid must be greater than or equal to 1 and less than or equal to 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations to make the grid uni-value.*);
//@ ensures(*If it is not possible to make the grid uni-value, the method returns -1.*);
    public int minOperations(int[][] grid, int x) {
        int[] arr = new int[grid.length * grid[0].length];
        int k = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                arr[k] = ints[j];
                k++;
            }
        }
        Arrays.sort(arr);
        int target = arr[arr.length / 2];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length / 2) {
                int rem = target - arr[i];
                if (rem % x != 0) {
                    return -1;
                }
                res += rem / x;
            } else {
                int rem = arr[i] - target;
                if (rem % x != 0) {
                    return -1;
                }
                res += rem / x;
            }
        }
        return res;
    }
}