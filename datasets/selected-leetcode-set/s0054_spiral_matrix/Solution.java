package g0001_0100.s0054_spiral_matrix;

// #Medium #Top_Interview_Questions #Array #Matrix #Simulation #Programming_Skills_II_Day_8
// #Level_2_Day_1_Implementation/Simulation #Udemy_2D_Arrays/Matrix
// #2023_08_11_Time_0_ms_(100.00%)_Space_41_MB_(9.67%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*The integer matrix parameter `matrix` must not be null.*);
//@ ensures(*The integer matrix parameter `matrix` must have a length `m` and each row in the matrix must have a length `n`.*);
//@ ensures(*The integer result is a list of all elements of the matrix in spiral order.*);
//@ ensures(*The elements in the integer result list are ordered in a spiral manner starting from the top-left corner and moving clockwise.*);
//@ ensures(*The length of the integer result list is equal to the total number of elements in the matrix.*);
//@ ensures(*All elements in the integer result list are unique.*);
//@ ensures(*The integer values in the matrix are within the range of -100 to 100.*);
//@ ensures(*The integer values in the matrix are included in the integer result list in the order they appear in the spiral traversal.*);
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int r = 0;
        int c = 0;
        int bigR = matrix.length - 1;
        //@ assume matrix != null && matrix[0] != null;
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