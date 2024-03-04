package g2501_2600.s2509_cycle_length_queries_in_a_tree;

// #Hard #Tree #Binary_Tree #2023_03_20_Time_12_ms_(99.26%)_Space_89.2_MB_(27.94%)

@SuppressWarnings("java:S1172")
public class Solution {
//@ ensures(*Behavioural requirements (preconditions and postconditions) for the Java method `public int[] cycleLengthQueries(int n, int[][] queries)`:*);
//@ ensures(**);
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer between 2 and 30 (inclusive).*);
//@ ensures(*The input `queries` is a 2D integer array of length `m`, where `m` is an integer between 1 and 10^5 (inclusive).*);
//@ ensures(*Each element `queries[i]` in the `queries` array is an array of length 2.*);
//@ ensures(*The values `queries[i][0]` and `queries[i][1]` are integers between 1 and 2^n - 1 (inclusive).*);
//@ ensures(*The values `queries[i][0]` and `queries[i][1]` are not equal.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array `answer` of length `m`.*);
//@ ensures(*Each element `answer[i]` in the `answer` array is an integer representing the length of the cycle in the graph after adding the edge between nodes `queries[i][0]` and `queries[i][1]`.*);
//@ ensures(*After processing each query, the added edge between nodes `queries[i][0]` and `queries[i][1]` is removed from the graph.*);
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            int count = 1;
            while (a != b) {
                if (a > b) {
                    a = a >> 1;
                } else {
                    b = b >> 1;
                }
                count++;
            }
            res[i] = count;
        }
        return res;
    }
}