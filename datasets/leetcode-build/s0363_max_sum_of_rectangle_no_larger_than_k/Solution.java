package g0301_0400.s0363_max_sum_of_rectangle_no_larger_than_k;

// #Hard #Array #Dynamic_Programming #Binary_Search #Matrix #Ordered_Set
// #2022_07_11_Time_70_ms_(99.21%)_Space_49.5_MB_(69.64%)

public class Solution {
	//@ requires(*1. The input matrix must not be null.*);
	//@ requires(*2. The input matrix must have at least one row and one column.*);
	//@ requires(*3. The number of rows in the matrix must be equal to the length of the matrix array.*);
	//@ requires(*4. The number of columns in each row of the matrix must be equal to the length of the corresponding array in the matrix array.*);
	//@ requires(*5. The value of k must be within the range of -10^5 to 10^5.*);
	//@ ensures(*1. The method returns an integer value representing the maximum sum of a rectangle in the matrix that is no larger than k.*);
	//@ ensures(*2. The returned value is guaranteed to be less than or equal to k.*);
	//@ ensures(*3. There exists at least one rectangle in the matrix whose sum is no larger than k.*);
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            int[] sum = new int[row];
            for (int j = i; j < col; j++) {
                for (int r = 0; r < row; r++) {
                    sum[r] += matrix[r][j];
                }
                int curr = 0;
                int max = sum[0];
                for (int n : sum) {
                    curr = Math.max(n, curr + n);
                    max = Math.max(curr, max);
                    if (max == k) {
                        return max;
                    }
                }
                if (max < k) {
                    res = Math.max(max, res);
                } else {
                    for (int a = 0; a < row; a++) {
                        int currSum = 0;
                        for (int b = a; b < row; b++) {
                            currSum += sum[b];
                            if (currSum <= k) {
                                res = Math.max(currSum, res);
                            }
                        }
                    }
                    if (res == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }
}