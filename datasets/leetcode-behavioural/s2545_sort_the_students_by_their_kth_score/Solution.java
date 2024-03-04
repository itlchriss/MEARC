package g2501_2600.s2545_sort_the_students_by_their_kth_score;

// #Medium #Array #Sorting #Matrix #2023_05_11_Time_1_ms_(99.50%)_Space_53.2_MB_(6.86%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `score` is not null.*);
//@ ensures(*The input matrix `score` has at least one row and one column.*);
//@ ensures(*The input matrix `score` contains distinct integers only.*);
//@ ensures(*The input integer `k` is a valid index within the range of the number of columns in `score`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output matrix is a sorted version of the input matrix `score`.*);
//@ ensures(*The rows of the output matrix are sorted based on the values in the `k`th column of the input matrix, from highest to lowest.*);
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (o1, o2) -> o2[k] - o1[k]);
        return score;
    }
}