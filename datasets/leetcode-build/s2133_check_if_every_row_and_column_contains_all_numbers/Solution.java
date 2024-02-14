package g2101_2200.s2133_check_if_every_row_and_column_contains_all_numbers;

// #Easy #Array #Hash_Table #Matrix #2022_06_04_Time_32_ms_(64.12%)_Space_83_MB_(46.34%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input matrix must not be null.*);
	//@ requires(*The input matrix must be a square matrix (n x n).*);
	//@ requires(*The values in the input matrix must be integers.*);
	//@ requires(*The values in the input matrix must be within the range of 1 to n (inclusive).*);
	//@ ensures(*The method should return true if the matrix is valid.*);
	//@ ensures(*The method should return false if the matrix is not valid.*);
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        Set<Integer> set = new HashSet<>();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                set.add(anInt);
            }
            if (set.size() != n) {
                return false;
            }
            set.clear();
        }
        for (int i = 0; i < matrix[0].length; i++) {
            for (int[] ints : matrix) {
                set.add(ints[i]);
            }
            if (set.size() != n) {
                return false;
            }
            set.clear();
        }
        return true;
    }
}