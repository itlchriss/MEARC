package g2601_2700.s2639_find_the_width_of_columns_of_a_grid;

// #Easy #Array #Matrix #2023_09_05_Time_2_ms_(99.70%)_Space_44.6_MB_(34.43%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid is not null.*);
//@ ensures(*The input grid has at least one row and one column.*);
//@ ensures(*The length of each row in the grid is equal to the number of columns in the grid.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` is not null.*);
//@ ensures(*The length of the output array `ans` is equal to the number of columns in the grid.*);
//@ ensures(*Each element in the output array `ans` is a non-negative integer.*);
//@ ensures(*The value of each element in the output array `ans` is the maximum length of integers in the corresponding column of the grid.*);
    public int[] findColumnWidth(int[][] grid) {
        int[] ans = new int[grid[0].length];
        for (int j = 0; j < grid[0].length; j++) {
            int max = 1;
            for (int[] ints : grid) {
                int num = ints[j];
                boolean neg = false;
                if (num < 0) {
                    neg = true;
                    num *= -1;
                }
                int size = 0;
                while (num > 0) {
                    num /= 10;
                    size++;
                }
                if (neg) {
                    size += 1;
                }
                max = Math.max(max, size);
            }
            ans[j] = max;
        }
        return ans;
    }
}