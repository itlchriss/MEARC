package g0201_0300.s0240_search_a_2d_matrix_ii;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search #Matrix
// #Divide_and_Conquer #Data_Structure_II_Day_4_Array #Binary_Search_II_Day_8
// #Big_O_Time_O(n+m)_Space_O(1) #2022_07_04_Time_7_ms_(86.73%)_Space_58.4_MB_(9.95%)

public class Solution {
	//@ requires(*The input matrix is not null.*);
	//@ requires(*The input matrix is a valid 2D matrix with dimensions m x n.*);
	//@ requires(*The integers in each row of the matrix are sorted in ascending order.*);
	//@ requires(*The integers in each column of the matrix are sorted in ascending order.*);
	//@ requires(*The target value is within the range of the integers in the matrix.*);
	//@ ensures(*The method returns a boolean value indicating whether the target value was found in the matrix.*);
	//@ ensures(*If the target value is found in the matrix, the method returns true.*);
	//@ ensures(*If the target value is not found in the matrix, the method returns false.*);
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = 0;
        int c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}