package g1201_1300.s1252_cells_with_odd_values_in_a_matrix;

// #Easy #Array #Math #Simulation #2022_03_12_Time_1_ms_(87.47%)_Space_39.9_MB_(62.42%)

public class Solution {
	//@ requires(*The input matrix is initialized to all 0's.*);
	//@ requires(*The indices array is not null.*);
	//@ requires(*The length of the indices array is not greater than 100.*);
	//@ requires(*The values of m and n are between 1 and 50 (inclusive).*);
	//@ requires(*The values of ri and ci in the indices array are between 0 and m-1 (inclusive) and 0 and n-1 (inclusive) respectively.*);
	//@ ensures(*The matrix is modified by incrementing the cells on the specified rows and columns.*);
	//@ ensures(*The final matrix contains the number of odd-valued cells.*);
	//@ ensures(*The output is an integer representing the number of odd-valued cells in the final matrix.*);
	//@ ensures(*The time complexity of the method is O(n + m + indices.length).*);
	//@ ensures(*The space complexity of the method is O(n + m).*);
    public int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        for (int[] index : indices) {
            addOneToRow(matrix, index[0]);
            addOneToColumn(matrix, index[1]);
        }
        int oddNumberCount = 0;
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (ints[j] % 2 != 0) {
                    oddNumberCount++;
                }
            }
        }
        return oddNumberCount;
    }

    private void addOneToColumn(int[][] matrix, int columnIndex) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][columnIndex] += 1;
        }
    }

    private void addOneToRow(int[][] matrix, int rowIndex) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[rowIndex][j] += 1;
        }
    }
}