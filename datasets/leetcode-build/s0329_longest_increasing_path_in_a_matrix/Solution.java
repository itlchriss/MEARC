package g0301_0400.s0329_longest_increasing_path_in_a_matrix;

// #Hard #Top_Interview_Questions #Dynamic_Programming #Depth_First_Search #Breadth_First_Search
// #Graph #Memoization #Topological_Sort #2022_07_09_Time_8_ms_(97.60%)_Space_54.7_MB_(19.13%)

public class Solution {
	//@ requires(*1. The input matrix must not be null.*);
	//@ requires(*2. The input matrix must have at least one cell.*);
	//@ requires(*3. The input matrix must have dimensions m x n, where m is the number of rows and n is the number of columns.*);
	//@ requires(*4. The values in the input matrix must be integers.*);
	//@ requires(*5. The values in the input matrix must be within the range of 0 to 2^31 - 1.*);
	//@ ensures(*1. The method returns an integer representing the length of the longest increasing path in the matrix.*);
	//@ ensures(*2. The returned length is greater than or equal to 1.*);
	//@ ensures(*3. The returned length is less than or equal to the total number of cells in the matrix.*);
	//@ ensures(*4. The longest increasing path is a sequence of cells in the matrix where each cell has a value greater than the previous cell in the sequence.*);
	//@ ensures(*5. The longest increasing path does not include any cells that are outside the boundary of the matrix.*);
	//@ ensures(*6. The longest increasing path does not include any cells that are reached by moving diagonally.*);
	//@ ensures(*7. The longest increasing path may include cells that are reached by moving left, right, up, or down from the current cell.*);
	//@ ensures(*8. The longest increasing path may include cells that are reached by moving in any combination of left, right, up, or down directions.*);
    public int longestIncreasingPath(int[][] matrix) {
        int maxIncreasingSequenceCount = 0;
        int n = matrix.length - 1;
        int m = matrix[0].length - 1;
        int[][] memory = new int[n + 1][m + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                maxIncreasingSequenceCount =
                        Math.max(maxIncreasingSequenceCount, move(i, j, n, m, matrix, memory));
            }
        }
        return maxIncreasingSequenceCount;
    }

    private int move(int row, int col, int n, int m, int[][] matrix, int[][] memory) {
        if (memory[row][col] == 0) {
            int count = 1;
            // move down
            if (row < n && matrix[row + 1][col] > matrix[row][col]) {
                count = Math.max(count, move(row + 1, col, n, m, matrix, memory) + 1);
            }
            // move right
            if (col < m && matrix[row][col + 1] > matrix[row][col]) {
                count = Math.max(count, move(row, col + 1, n, m, matrix, memory) + 1);
            }
            // move up
            if (row > 0 && matrix[row - 1][col] > matrix[row][col]) {
                count = Math.max(count, move(row - 1, col, n, m, matrix, memory) + 1);
            }
            // move left
            if (col > 0 && matrix[row][col - 1] > matrix[row][col]) {
                count = Math.max(count, move(row, col - 1, n, m, matrix, memory) + 1);
            }
            memory[row][col] = count;
        }
        return memory[row][col];
    }
}