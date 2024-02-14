package g0001_0100.s0054_spiral_matrix;

// #Medium #Top_Interview_Questions #Array #Matrix #Simulation #Programming_Skills_II_Day_8
// #Level_2_Day_1_Implementation/Simulation #Udemy_2D_Arrays/Matrix
// #2023_08_11_Time_0_ms_(100.00%)_Space_41_MB_(9.67%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*1. The input matrix must not be null.*);
	//@ requires(*2. The input matrix must have at least one row and one column.*);
	//@ requires(*3. The input matrix must have a valid number of rows and columns (1 <= m, n <= 10).*);
	//@ requires(*4. The elements in the input matrix must be integers.*);
	//@ requires(*5. The elements in the input matrix must be within the range of -100 to 100.*);
	//@ ensures(*1. The method should return a list of integers.*);
	//@ ensures(*2. The list should contain all elements of the input matrix in spiral order.*);
	//@ ensures(*3. The order of the elements in the list should follow the spiral pattern as shown in the examples.*);
	//@ ensures(*4. The list should be in the same order as the elements are visited in the spiral pattern.*);
	//@ ensures(*5. The list should not contain any duplicate elements.*);
	//@ ensures(*6. The list should have a length equal to the total number of elements in the input matrix.*);
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int r = 0;
        int c = 0;
        int bigR = matrix.length - 1;
        int bigC = matrix[0].length - 1;
        while (r <= bigR && c <= bigC) {
            for (int i = c; i <= bigC; i++) {
                list.add(matrix[r][i]);
            }
            r++;
            for (int i = r; i <= bigR; i++) {
                list.add(matrix[i][bigC]);
            }
            bigC--;
            for (int i = bigC; i >= c && r <= bigR; i--) {
                list.add(matrix[bigR][i]);
            }
            bigR--;
            for (int i = bigR; i >= r && c <= bigC; i--) {
                list.add(matrix[i][c]);
            }
            c++;
        }
        return list;
    }
}