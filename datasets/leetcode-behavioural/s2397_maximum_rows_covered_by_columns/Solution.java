package g2301_2400.s2397_maximum_rows_covered_by_columns;

// #Medium #Array #Matrix #Bit_Manipulation #Backtracking #Enumeration
// #2022_09_14_Time_1_ms_(100.00%)_Space_40.7_MB_(93.95%)

public class Solution {
    private int ans = 0;
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix must be a 2D array of integers.*);
//@ ensures(*The number of columns to select, `numSelect`, must be a positive integer.*);
//@ ensures(*The number of rows in the matrix, `m`, must be greater than or equal to the number of columns to select, `numSelect`.*);
//@ ensures(*The number of columns in the matrix, `n`, must be greater than or equal to the number of columns to select, `numSelect`.*);
//@ ensures(*Each element in the matrix must be either 0 or 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum number of rows that can be covered by selecting `numSelect` columns.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to the number of rows in the matrix, `m`.*);
//@ ensures(*The returned value is less than or equal to the number of columns to select, `numSelect`.*);
//@ ensures(*The returned value is the maximum possible number of rows that can be covered by selecting `numSelect` columns.*);

    public int maximumRows(int[][] matrix, int numSelect) {
        dfs(matrix, /*colIndex=*/ 0, numSelect, /*mask=*/ 0);
        return ans;
    }

    private void dfs(int[][] matrix, int colIndex, int leftColsCount, int mask) {
        if (leftColsCount == 0) {
            ans = Math.max(ans, getAllZerosRowCount(matrix, mask));
            return;
        }
        if (colIndex == matrix[0].length) {
            return;
        }
        // choose this column
        dfs(matrix, colIndex + 1, leftColsCount - 1, mask | 1 << colIndex);
        // not choose this column
        dfs(matrix, colIndex + 1, leftColsCount, mask);
    }

    private int getAllZerosRowCount(int[][] matrix, int mask) {
        int count = 0;
        for (int[] row : matrix) {
            boolean isAllZeros = true;
            for (int i = 0; i < row.length; ++i) {
                if (row[i] == 1 && (mask >> i & 1) == 0) {
                    isAllZeros = false;
                    break;
                }
            }
            if (isAllZeros) {
                ++count;
            }
        }
        return count;
    }
}