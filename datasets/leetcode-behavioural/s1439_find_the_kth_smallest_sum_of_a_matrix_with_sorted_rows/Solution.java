package g1401_1500.s1439_find_the_kth_smallest_sum_of_a_matrix_with_sorted_rows;

// #Hard #Array #Binary_Search #Matrix #Heap_Priority_Queue
// #2022_03_28_Time_40_ms_(75.79%)_Space_54.2_MB_(53.31%)

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `mat` is not null.*);
//@ ensures(*The number of rows `m` in `mat` is greater than - The number of columns `n` in `mat` is greater than - The number of rows `m` in `mat` is equal to the length of `mat`.*);
//@ ensures(*The number of columns `n` in `mat` is equal to the length of `mat[i]` for all `i` from 0 to `m-1`.*);
//@ ensures(*The elements in each row of `mat` are sorted in non-decreasing order.*);
//@ ensures(*The value of `k` is greater than - The value of `k` is less than or equal to the minimum of 200 and `n^m`, where `n^m` represents `n` raised to the power of `m`.*);
//@ ensures(*The elements in `mat` are positive integers.*);
//@ ensures(*The elements in `mat` are less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the `k`th smallest array sum among all possible arrays.*);
    public int kthSmallest(int[][] mat, int k) {
        TreeSet<int[]> treeSet =
                new TreeSet<>(
                        (o1, o2) -> {
                            if (o1[0] != o2[0]) {
                                return o1[0] - o2[0];
                            } else {
                                for (int i = 1; i < o1.length; i++) {
                                    if (o1[i] != o2[i]) {
                                        return o1[i] - o2[i];
                                    }
                                }
                                return 0;
                            }
                        });
        int m = mat.length;
        int n = mat[0].length;
        int sum = 0;
        int[] entry = new int[m + 1];
        for (int[] ints : mat) {
            sum += ints[0];
        }
        entry[0] = sum;
        treeSet.add(entry);
        int count = 0;
        while (count < k) {
            int[] curr = treeSet.pollFirst();
            count++;
            if (count == k) {
                return Objects.requireNonNull(curr)[0];
            }
            for (int i = 0; i < m; i++) {
                int[] next = Arrays.copyOf(Objects.requireNonNull(curr), curr.length);
                if (curr[i + 1] + 1 < n) {
                    next[0] -= mat[i][curr[i + 1]];
                    next[0] += mat[i][curr[i + 1] + 1];
                    next[i + 1]++;
                    treeSet.add(next);
                }
            }
        }
        return -1;
    }
}