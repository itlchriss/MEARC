package g1201_1300.s1292_maximum_side_length_of_a_square_with_sum_less_than_or_equal_to_threshold;

// #Medium #Array #Binary_Search #Matrix #Prefix_Sum #Binary_Search_II_Day_15
// #2022_03_10_Time_23_ms_(32.97%)_Space_78_MB_(14.49%)

public class Solution {
	//@ requires(*1. The input matrix `mat` is not null.*);
	//@ requires(*2. The input matrix `mat` has at least one row and one column.*);
	//@ requires(*3. The input matrix `mat` has dimensions `m x n`, where `m` is the number of rows and `n` is the number of columns.*);
	//@ requires(*4. The elements of the input matrix `mat` are non-negative integers.*);
	//@ requires(*5. The input threshold is a non-negative integer.*);
	//@ ensures(*1. The output is an integer representing the maximum side length of a square with a sum less than or equal to the threshold.*);
	//@ ensures(*2. If there is no square with a sum less than or equal to the threshold, the output is 0.*);
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    prefix[i][j] = mat[i][j];
                } else if (i == 0) {
                    prefix[i][j] = mat[i][j] + prefix[0][j - 1];
                } else if (j == 0) {
                    prefix[i][j] = mat[i][j] + prefix[i - 1][0];
                } else {
                    prefix[i][j] =
                            mat[i][j] + prefix[i][j - 1] + prefix[i - 1][j] - prefix[i - 1][j - 1];
                }
            }
        }
        int low = 1;
        int high = Math.min(m, n);
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (min(mid, prefix) > threshold) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

    int min(int length, int[][] prefix) {
        int min = 0;
        for (int i = length - 1; i < prefix.length; i++) {
            for (int j = length - 1; j < prefix[0].length; j++) {
                if (i == length - 1 && j == length - 1) {
                    min = prefix[i][j];
                } else if (i - length < 0) {
                    min = Math.min(min, prefix[i][j] - prefix[i][j - length]);
                } else if (j - length < 0) {
                    min = Math.min(min, prefix[i][j] - prefix[i - length][j]);
                } else {
                    min =
                            Math.min(
                                    min,
                                    prefix[i][j]
                                            - prefix[i][j - length]
                                            - prefix[i - length][j]
                                            + prefix[i - length][j - length]);
                }
            }
        }
        return min;
    }
}