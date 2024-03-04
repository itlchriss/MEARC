package g1301_1400.s1329_sort_the_matrix_diagonally;

// #Medium #Array #Sorting #Matrix #2022_03_19_Time_15_ms_(26.03%)_Space_47.7_MB_(56.76%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is not null.*);
//@ ensures(*The input matrix `mat` is a 2D array of integers.*);
//@ ensures(*The input matrix `mat` has at least one row and one column.*);
//@ ensures(*The input matrix `mat` has dimensions `m x n`, where `m` is the number of rows and `n` is the number of columns.*);
//@ ensures(*The values in the input matrix `mat` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output matrix is a 2D array of integers.*);
//@ ensures(*The output matrix has the same dimensions as the input matrix `mat`.*);
//@ ensures(*Each matrix diagonal in the output matrix is sorted in ascending order.*);
//@ ensures(*The values in each matrix diagonal are the same as the values in the corresponding matrix diagonal of the input matrix `mat`.*);
//@ ensures(*The values in each row and column of the output matrix are in the same order as the corresponding row and column of the input matrix `mat`, except for the sorted matrix diagonals.*);
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sorted = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            int iCopy = i;
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n && iCopy < m; j++, iCopy++) {
                list.add(mat[iCopy][j]);
            }
            Collections.sort(list);
            iCopy = i;
            for (int j = 0; j < n && iCopy < m; j++, iCopy++) {
                sorted[iCopy][j] = list.get(j);
            }
        }

        for (int j = n - 1; j > 0; j--) {
            int jCopy = j;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < m && jCopy < n; i++, jCopy++) {
                list.add(mat[i][jCopy]);
            }
            Collections.sort(list);
            jCopy = j;
            for (int i = 0; i < m && jCopy < n; i++, jCopy++) {
                sorted[i][jCopy] = list.get(i);
            }
        }
        return sorted;
    }
}