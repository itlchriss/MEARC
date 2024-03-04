package g2701_2800.s2718_sum_of_matrix_after_queries;

// #Medium #Array #Hash_Table #2023_09_18_Time_3_ms_(100.00%)_Space_61.2_MB_(84.49%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` must be greater than or equal to - The length of the `queries` array must be greater than or equal to - Each query in the `queries` array must have a length of - The value of `type` in each query must be either 0 or - The value of `index` in each query must be between 0 and `n-1`.*);
//@ ensures(*The value of `val` in each query must be between 0 and 100,*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a long value representing the sum of integers in the matrix after all queries are applied.*);
    public long matrixSumQueries(int n, int[][] queries) {
        boolean[] queriedRow = new boolean[n];
        boolean[] queriedCol = new boolean[n];
        long sum = 0;
        int remainingRows = n;
        int remainingCols = n;
        for (int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            int index = queries[i][1];
            int value = queries[i][2];
            if ((type == 0 && !queriedRow[index]) || (type == 1 && !queriedCol[index])) {
                sum += (long) value * (type == 0 ? remainingCols : remainingRows);
                if (type == 0) {
                    remainingRows--;
                    queriedRow[index] = true;
                } else {
                    remainingCols--;
                    queriedCol[index] = true;
                }
            }
        }
        return sum;
    }
}