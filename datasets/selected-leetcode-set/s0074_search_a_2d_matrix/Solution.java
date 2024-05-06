package g0001_0100.s0074_search_a_2d_matrix;

// #Medium #Top_100_Liked_Questions #Array #Binary_Search #Matrix #Data_Structure_I_Day_5_Array
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_8 #Level_2_Day_8_Binary_Search
// #Udemy_2D_Arrays/Matrix #Big_O_Time_O(endRow+endCol)_Space_O(1)
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.9_MB_(71.91%)

public class Solution {
//@ ensures(*The integer matrix parameter `matrix` must not be null.*);
//@ ensures(*The integer matrix parameter `matrix` must have a length `m` and each row must have a length `n`.*);
//@ ensures(*The integer parameter `target` is greater than or equal to -10000 and is less than or equal to 10000.*);
//@ ensures(*The boolean result is equal to the true literal if the integer parameter `target` is found in the integer matrix parameter `matrix`.*);
//@ ensures(*The boolean result is equal to the false literal if the integer parameter `target` is not found in the integer matrix parameter `matrix`.*);
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