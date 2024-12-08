package g2001_2100.s2022_convert_1d_array_into_2d_array;

// #Easy #Array #Matrix #Simulation #2022_05_25_Time_8_ms_(40.41%)_Space_119.4_MB_(53.92%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `original` array must not be null.*);
//@ ensures(*The length of the `original` array must be greater than or equal to `n`.*);
//@ ensures(*The values in the `original` array must be within the range of 1 to 10^- The values of `m` and `n` must be within the range of 1 to 4 * 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned 2D array must have `m` rows and `n` columns.*);
//@ ensures(*The elements from indices 0 to n - 1 (inclusive) of `original` should form the first row of the constructed 2D array.*);
//@ ensures(*The elements from indices n to 2 * n - 1 (inclusive) of `original` should form the second row of the constructed 2D array, and so on.*);
//@ ensures(*If it is impossible to construct a valid 2D array with the given `original`, `m`, and `n`, an empty 2D array should be returned.*);
    public int[][] construct2DArray(int[] original, int m, int n) {
        int size = original.length;
        if (m * n != size) {
            return new int[][] {};
        }
        int[][] ans = new int[m][n];
        int k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[k++];
            }
        }
        return ans;
    }
}