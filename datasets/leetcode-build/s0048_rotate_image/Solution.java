package g0001_0100.s0048_rotate_image;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Math #Matrix
// #Data_Structure_II_Day_3_Array #Programming_Skills_II_Day_7 #Udemy_2D_Arrays/Matrix
// #Big_O_Time_O(n^2)_Space_O(1) #2023_08_11_Time_0_ms_(100.00%)_Space_41.5_MB_(34.96%)

public class Solution {
	//@ requires(*The input matrix is a square matrix (n x n).*);
	//@ requires(*The input matrix is not null.*);
	//@ ensures(*The input matrix is rotated by 90 degrees clockwise.*);
	//@ ensures(*The rotated matrix is stored in the same input matrix.*);
	//@ ensures(*The dimensions of the rotated matrix remain the same as the input matrix.*);
	//@ ensures(*The elements in each row of the rotated matrix are in reverse order compared to the input matrix.*);
	//@ ensures(*The elements in each column of the rotated matrix are in the same order as the input matrix.*);
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int[][] pos =
                        new int[][] {
                            {i, j}, {j, n - 1 - i}, {n - 1 - i, n - 1 - j}, {n - 1 - j, i}
                        };
                int t = matrix[pos[0][0]][pos[0][1]];
                for (int k = 1; k < pos.length; k++) {
                    int temp = matrix[pos[k][0]][pos[k][1]];
                    matrix[pos[k][0]][pos[k][1]] = t;
                    t = temp;
                }
                matrix[pos[0][0]][pos[0][1]] = t;
            }
        }
    }
}