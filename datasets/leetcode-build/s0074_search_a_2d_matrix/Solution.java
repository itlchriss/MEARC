package g0001_0100.s0074_search_a_2d_matrix;

// #Medium #Top_100_Liked_Questions #Array #Binary_Search #Matrix #Data_Structure_I_Day_5_Array
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_8 #Level_2_Day_8_Binary_Search
// #Udemy_2D_Arrays/Matrix #Big_O_Time_O(endRow+endCol)_Space_O(1)
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.9_MB_(71.91%)

public class Solution {
	//@ requires(*The input matrix is not null.*);
	//@ requires(*The input matrix is a valid 2D matrix with dimensions m x n.*);
	//@ requires(*The integers in each row of the matrix are sorted from left to right.*);
	//@ requires(*The first integer of each row is greater than the last integer of the previous row.*);
	//@ requires(*The target integer is within the range of -10^4 to 10^*);
	//@ ensures(*The method returns a boolean value indicating whether the target integer is found in the matrix.*);
	//@ ensures(*If the target integer is found in the matrix, the method returns true.*);
	//@ ensures(*If the target integer is not found in the matrix, the method returns false.*);
    public boolean searchMatrix(int[][] matrix, int target) {
        int endRow = matrix.length;
        int endCol = matrix[0].length;
        int targetRow = 0;
        boolean result = false;
        for (int i = 0; i < endRow; i++) {
            if (matrix[i][endCol - 1] >= target) {
                targetRow = i;
                break;
            }
        }
        for (int i = 0; i < endCol; i++) {
            if (matrix[targetRow][i] == target) {
                result = true;
                break;
            }
        }
        return result;
    }
}