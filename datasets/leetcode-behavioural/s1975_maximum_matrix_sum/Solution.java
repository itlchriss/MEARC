package g1901_2000.s1975_maximum_matrix_sum;

// #Medium #Array #Greedy #Matrix #2022_05_21_Time_4_ms_(100.00%)_Space_79.8_MB_(36.28%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix must be a square matrix, i.e., the number of rows must be equal to the number of columns.*);
//@ ensures(*2. The size of the matrix must be at least 2x2.*);
//@ ensures(*3. The elements of the matrix must be integers.*);
//@ ensures(*4. The range of the elements in the matrix is between -10^5 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return a long value representing the maximum sum of the matrix's elements.*);
//@ ensures(*2. The matrix's elements should be modified in such a way that the maximum sum is achieved.*);
//@ ensures(*3. The modification of the matrix's elements should be done by multiplying any two adjacent elements by -1.*);
//@ ensures(*4. The adjacent elements are defined as elements that share a border, i.e., they are either in the same row or in the same column.*);
    public long maxMatrixSum(int[][] matrix) {
        int numNegatives = 0;
        long totalSum = 0;
        int minNeg = Integer.MIN_VALUE;
        int minPos = Integer.MAX_VALUE;
        for (int[] ints : matrix) {
            for (int e = 0; e < matrix[0].length; e++) {
                int value = ints[e];
                if (value < 0) {
                    numNegatives++;
                    totalSum = totalSum - value;
                    minNeg = Math.max(value, minNeg);
                } else {
                    totalSum = totalSum + value;
                    minPos = Math.min(value, minPos);
                }
            }
        }

        int min = Math.min(minPos, -minNeg);

        return totalSum - numNegatives % 2 * (min + min);
    }
}