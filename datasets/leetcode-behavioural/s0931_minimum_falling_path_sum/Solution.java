package g0901_1000.s0931_minimum_falling_path_sum;

// #Medium #Array #Dynamic_Programming #Matrix #Dynamic_Programming_I_Day_13
// #2022_03_30_Time_4_ms_(72.19%)_Space_48.3_MB_(12.49%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix must not be null.*);
//@ ensures(*2. The input matrix must have at least one row and one column.*);
//@ ensures(*3. The input matrix must be a square matrix (number of rows = number of columns).*);
//@ ensures(*4. The values in the input matrix must be within the range of -100 to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns an integer representing the minimum sum of any falling path through the matrix.*);
//@ ensures(*2. The input matrix remains unchanged after the method is executed.*);
    public int minFallingPathSum(int[][] matrix) {
        int l = matrix[0].length;
        int[] arr = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            int[] cur = new int[l];
            for (int j = 0; j < l; j++) {
                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;
                int curCell = arr[j];
                if (j > 0) {
                    left = arr[j - 1];
                }
                if (j < l - 1) {
                    right = arr[j + 1];
                }
                cur[j] = matrix[i][j] + Math.min(left, Math.min(right, curCell));
            }
            arr = cur;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
}