package g1301_1400.s1337_the_k_weakest_rows_in_a_matrix;

// #Easy #Array #Sorting #Binary_Search #Matrix #Heap_Priority_Queue #Binary_Search_I_Day_9
// #2022_03_19_Time_1_ms_(99.77%)_Space_49.2_MB_(13.72%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is not null.*);
//@ ensures(*The input matrix `mat` has at least one row.*);
//@ ensures(*The input matrix `mat` has at least one column.*);
//@ ensures(*The input matrix `mat` contains only 0s and 1s.*);
//@ ensures(*The input integer `k` is greater than - The input integer `k` is less than or equal to the number of rows in `mat`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `result` is not null.*);
//@ ensures(*The length of the output array `result` is equal to `k`.*);
//@ ensures(*The elements in the output array `result` are valid indices of rows in `mat`.*);
//@ ensures(*The elements in the output array `result` are in ascending order, indicating the weakest rows in `mat`.*);
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int index = binarySearch(mat, i, mat[i].length - 1);
            result[i] = index;
        }
        int minValue = 101;
        int[] resultK = new int[k];
        int index = -1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[j] < minValue) {
                    minValue = result[j];
                    index = j;
                }
            }
            result[index] = 110;
            resultK[i] = index;
            index = -1;
            minValue = 101;
        }
        return resultK;
    }

    private int binarySearch(int[][] mat, int row, int end) {
        int start = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mat[row][mid] == 1) {
                start = mid + 1;
            } else if (mat[row][mid] == 0) {
                end = mid - 1;
            }
        }
        return start;
    }
}