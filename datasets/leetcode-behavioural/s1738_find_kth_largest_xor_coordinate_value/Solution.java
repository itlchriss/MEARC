package g1701_1800.s1738_find_kth_largest_xor_coordinate_value;

// #Medium #Array #Matrix #Bit_Manipulation #Heap_Priority_Queue #Prefix_Sum #Divide_and_Conquer
// #Quickselect #2022_04_29_Time_40_ms_(97.08%)_Space_208.6_MB_(58.39%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix is not null.*);
//@ ensures(*The input matrix has at least one row and one column.*);
//@ ensures(*The input matrix contains non-negative integers.*);
//@ ensures(*The value of k is a positive integer.*);
//@ ensures(*The value of k is less than or equal to the total number of coordinates in the matrix.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the kth largest XOR coordinate value.*);
//@ ensures(*The returned value is a non-negative integer.*);
//@ ensures(*The returned value is the XOR of all matrix[i][j] values for a given coordinate (a, b), where 0 <= a < m and 0 <= b < n.*);
//@ ensures(*The returned value is the XOR of all matrix[i][j] values for a given coordinate (a, b), where 0 <= i <= a < m and 0 <= j <= b < n.*);
//@ ensures(*The returned value is the XOR of all matrix[i][j] values for a given coordinate (a, b), where 0 <= i <= a < m and 0 <= j <= b < n, and the coordinates are 0-indexed.*);
//@ ensures(*The returned value is the kth largest XOR coordinate value among all the coordinates in the matrix.*);
//@ ensures(*The returned value is the kth largest XOR coordinate value among all the coordinates in the matrix, where the coordinates are 1-indexed.*);
    public int kthLargestValue(int[][] matrix, int k) {
        int t = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] prefixXor = new int[rows + 1][cols + 1];
        int[] array = new int[rows * cols];

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                prefixXor[r][c] =
                        matrix[r - 1][c - 1]
                                ^ prefixXor[r - 1][c]
                                ^ prefixXor[r][c - 1]
                                ^ prefixXor[r - 1][c - 1];
                array[t++] = prefixXor[r][c];
            }
        }

        int target = array.length - k;
        quickSelect(array, 0, array.length - 1, target);
        return array[target];
    }

    private int quickSelect(int[] array, int left, int right, int target) {
        if (left == right) {
            return left;
        }

        int pivot = array[right];
        int j = left;
        int k = right - 1;
        while (j <= k) {
            if (array[j] < pivot) {
                j++;
            } else if (array[k] > pivot) {
                k--;
            } else {
                swap(array, j++, k--);
            }
        }
        swap(array, j, right);
        if (j == target) {
            return j;
        } else if (j > target) {
            return quickSelect(array, left, j - 1, target);
        } else {
            return quickSelect(array, j + 1, right, target);
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}