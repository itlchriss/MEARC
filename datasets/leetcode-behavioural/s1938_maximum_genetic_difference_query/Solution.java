package g1901_2000.s1938_maximum_genetic_difference_query;

// #Hard #Array #Bit_Manipulation #Trie #2022_05_16_Time_174_ms_(100.00%)_Space_134.4_MB_(85.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the `parents` array is at least - The length of the `parents` array is at most 10^- The values in the `parents` array are integers.*);
//@ ensures(*The values in the `parents` array range from 0 to the length of the `parents` array minus - The root node is denoted by -1 in the `parents` array.*);
//@ ensures(*The length of the `queries` array is at least - The length of the `queries` array is at most 3 * 10^- The values in the `queries` array are arrays of length - The first element in each query array is an integer.*);
//@ ensures(*The first element in each query array ranges from 0 to the length of the `parents` array minus - The second element in each query array is an integer.*);
//@ ensures(*The second element in each query array ranges from 0 to 2 * 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array of integers.*);
//@ ensures(*The length of the returned array is equal to the length of the `queries` array.*);
//@ ensures(*Each element in the returned array is an integer.*);
//@ ensures(*Each element in the returned array is the maximum genetic difference between the second element in the corresponding query array and any node on the path between the first element in the corresponding query array and the root node.*);
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        int[][] fd = new int[n][];
        for (int i = 0; i < n; i++) {
            fill(parents, n, fd, i);
        }
        int[] ret = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int cur = queries[q][0];
            int value = queries[q][1];
            for (int p = 30; p >= 0; p--) {
                int msk = 1 << p;
                if ((value & msk) != (cur & msk)) {
                    ret[q] |= msk;
                } else if (fd[cur][p] >= 0) {
                    ret[q] |= msk;
                    cur = fd[cur][p];
                }
            }
        }
        return ret;
    }

    private void fill(int[] parents, int n, int[][] fd, int i) {
        if (fd[i] == null) {
            fd[i] = new int[31];
            int a = parents[i];
            if (a >= 0) {
                fill(parents, n, fd, a);
            }
            for (int p = 30; p >= 0; p--) {
                if (a == -1) {
                    fd[i][p] = -1;
                } else {
                    if ((i & (1 << p)) == (a & (1 << p))) {
                        fd[i][p] = fd[a][p];
                    } else {
                        fd[i][p] = a;
                        a = fd[a][p];
                    }
                }
            }
        }
    }
}