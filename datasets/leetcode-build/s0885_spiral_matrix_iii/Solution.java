package g0801_0900.s0885_spiral_matrix_iii;

// #Medium #Array #Matrix #Simulation #2023_04_26_Time_2_ms_(100.00%)_Space_43.8_MB_(33.14%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input values `rows` and `cols` must be integers greater than or equal to 1.*);
	//@ requires(*The input values `rStart` and `cStart` must be integers greater than or equal to 0 and less than `rows` and `cols` respectively.*);
	//@ ensures(*The output is a 2D array of integers representing the positions of the grid in the order they were visited.*);
	//@ ensures(*The output array has a length of `rows * cols`.*);
	//@ ensures(*Each element in the output array is a 1D array of length 2, representing the coordinates of a position in the grid.*);
	//@ ensures(*The coordinates of each position in the output array are within the boundaries of the grid.*);
	//@ ensures(*The positions in the output array are visited in a clockwise spiral shape, starting from the cell `(rStart, cStart)` and moving east.*);
    public int[][] spiralMatrixIII(int rows, int cols, int y, int x) {
        int localX = x;
        int localY = y;
        int[] i = {0};
        int[] moves = {0};
        int[][] result = new int[rows * cols][2];
        result[0][0] = y;
        result[0][1] = x;
        i[0]++;
        while (i[0] < result.length) {
            moves[0]++;
            // Move right
            localX = getXRight(rows, cols, localX, localY, i, moves, result);
            if (i[0] >= result.length) {
                break;
            }
            // Move down
            localY = getYDown(rows, cols, localX, localY, i, moves, result);
            if (i[0] >= result.length) {
                break;
            }
            moves[0]++;
            // Move left
            localX = getXLeft(rows, cols, localX, localY, i, moves, result);
            if (i[0] >= result.length) {
                break;
            }
            // Move up
            localY = getYUp(rows, cols, localX, localY, i, moves, result);
        }
        return result;
    }

    private int getYUp(
            int rows, int cols, int localX, int localY, int[] i, int[] moves, int[][] result) {
        if (localX < 0 || localX >= cols) {
            localY -= moves[0];
        } else {
            for (int j = 0; j < moves[0]; j++) {
                localY--;
                if (localY >= 0 && localY < rows) {
                    result[i[0]][0] = localY;
                    result[i[0]][1] = localX;
                    i[0]++;
                }
            }
        }
        return localY;
    }

    private int getXLeft(
            int rows, int cols, int localX, int localY, int[] i, int[] moves, int[][] result) {
        if (localY < 0 || localY >= rows) {
            localX -= moves[0];
        } else {
            for (int j = 0; j < moves[0]; j++) {
                localX--;
                if (localX >= 0 && localX < cols) {
                    result[i[0]][0] = localY;
                    result[i[0]][1] = localX;
                    i[0]++;
                }
            }
        }
        return localX;
    }

    private int getYDown(
            int rows, int cols, int localX, int localY, int[] i, int[] moves, int[][] result) {
        if (localX < 0 || localX >= cols) {
            localY += moves[0];
        } else {
            for (int j = 0; j < moves[0]; j++) {
                localY++;
                if (localY >= 0 && localY < rows) {
                    result[i[0]][0] = localY;
                    result[i[0]][1] = localX;
                    i[0]++;
                }
            }
        }
        return localY;
    }

    private int getXRight(
            int rows, int cols, int localX, int localY, int[] i, int[] moves, int[][] result) {
        if (localY < 0 || localY >= rows) {
            localX += moves[0];
        } else {
            for (int j = 0; j < moves[0]; j++) {
                localX++;
                if (localX >= 0 && localX < cols) {
                    result[i[0]][0] = localY;
                    result[i[0]][1] = localX;
                    i[0]++;
                }
            }
        }
        return localX;
    }
}