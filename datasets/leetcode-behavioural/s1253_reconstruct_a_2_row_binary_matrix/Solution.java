package g1201_1300.s1253_reconstruct_a_2_row_binary_matrix;

// #Medium #Array #Greedy #Matrix #2022_03_12_Time_18_ms_(57.27%)_Space_111.1_MB_(42.74%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input parameters `upper`, `lower`, and `colsum` are valid and satisfy the given constraints.*);
//@ ensures(*The length of `colsum` is equal to the number of columns in the matrix.*);
//@ ensures(*The sum of elements in `colsum` is equal to the sum of elements in the upper and lower rows.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned matrix is a 2-D integer array.*);
//@ ensures(*The sum of elements in the upper row of the returned matrix is equal to `upper`.*);
//@ ensures(*The sum of elements in the lower row of the returned matrix is equal to `lower`.*);
//@ ensures(*The sum of elements in each column of the returned matrix is equal to the corresponding element in `colsum`.*);
//@ ensures(*If there are multiple valid solutions, any of them can be returned.*);
//@ ensures(*If no valid solution exists, an empty 2-D array is returned.*);
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> res = new ArrayList<>();
        int n = colsum.length;
        int[] upperRow = new int[n];
        int[] lowerRow = new int[n];
        int currentUpperSum = 0;
        int currentLowerSum = 0;
        for (int i = 0; i < n; i++) {
            if (colsum[i] >= 1) {
                upperRow[i] = 1;
                lowerRow[i] = 1;
                currentUpperSum++;
                currentLowerSum++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 1 && currentUpperSum > upper) {
                currentUpperSum--;
                upperRow[i] = 0;
            }
        }
        for (int i = 0; i < upperRow.length; i++) {
            if (colsum[i] == 1 && upperRow[i] == 1) {
                currentLowerSum--;
                lowerRow[i] = 0;
            }
        }
        if (currentUpperSum != upper || currentLowerSum != lower) {
            return res;
        }
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            res.get(0).add(upperRow[i]);
            res.get(1).add(lowerRow[i]);
        }
        return res;
    }
}