package g1701_1800.s1727_largest_submatrix_with_rearrangements;

// #Medium #Array #Sorting #Greedy #Matrix #2022_04_28_Time_9_ms_(90.48%)_Space_67.7_MB_(91.27%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix is not null.*);
//@ ensures(*The input matrix has at least one row and one column.*);
//@ ensures(*The input matrix contains only 0s and 1s.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the area of the largest submatrix of 1s.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the total number of elements in the input matrix.*);
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int count = 0;
        for (int[] ints : matrix) {
            Arrays.sort(ints);
            for (int j = 1; j <= n; j++) {
                count = Math.max(count, j * ints[n - j]);
            }
        }
        return count;
    }
}