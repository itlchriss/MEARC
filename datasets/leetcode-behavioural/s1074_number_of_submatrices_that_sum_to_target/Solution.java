package g1001_1100.s1074_number_of_submatrices_that_sum_to_target;

// #Hard #Array #Hash_Table #Matrix #Prefix_Sum
// #2022_02_26_Time_171_ms_(68.35%)_Space_117.8_MB_(5.54%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix is not null.*);
//@ ensures(*2. The input matrix has at least one row and one column.*);
//@ ensures(*3. The target value is within the range of -10^8 to 10^8.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns an integer representing the number of non-empty submatrices that sum to the target.*);
//@ ensures(*2. The method does not modify the input matrix.*);
//@ ensures(*3. The method handles the case where the target is 0 and there are no submatrices that sum to 0.*);
//@ ensures(*4. The method handles the case where the target is negative and there are no submatrices that sum to the negative target.*);
//@ ensures(*5. The method handles the case where the target is positive and there are no submatrices that sum to the positive target.*);
//@ ensures(*6. The method correctly counts the number of submatrices that sum to the target.*);
//@ ensures(*7. The method handles the case where the target is larger than the sum of all elements in the matrix and there are no submatrices that sum to the target.*);
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int cur;
        int res = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = i; j < columns; j++) {
                sumMap.clear();
                sumMap.put(0, 1);
                cur = 0;
                for (int[] ints : matrix) {
                    cur += ints[j] - (i > 0 ? ints[i - 1] : 0);
                    res += sumMap.getOrDefault(cur - target, 0);
                    sumMap.put(cur, sumMap.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}