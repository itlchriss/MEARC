package g2701_2800.s2747_count_zero_request_servers;

// #Medium #Array #Hash_Table #Sorting #Sliding_Window
// #2023_09_24_Time_43_ms_(76.92%)_Space_85.7_MB_(63.85%)

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` must be greater than or equal to 1.*);
//@ ensures(*The length of the `logs` array must be greater than or equal to 1.*);
//@ ensures(*The length of the `queries` array must be greater than or equal to 1.*);
//@ ensures(*Each element in the `logs` array must be a 2-element array.*);
//@ ensures(*The first element of each element in the `logs` array must be an integer between 1 and `n`, inclusive.*);
//@ ensures(*The second element of each element in the `logs` array must be an integer between 1 and 10^6, inclusive.*);
//@ ensures(*The integer `x` must be between 1 and 10^5, inclusive.*);
//@ ensures(*Each element in the `queries` array must be an integer greater than `x` and less than or equal to 10^6.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `arr` must be a 0-indexed integer array.*);
//@ ensures(*The length of the returned array `arr` must be equal to the length of the `queries` array.*);
//@ ensures(*Each element in the returned array `arr` must be an integer between 0 and `n`, inclusive.*);
    public int[] countServers(int n, int[][] logs, int x, int[] qs) {
        int m = qs.length;
        var valIdx = new int[m][2];
        for (int i = 0; i < m; i++) {
            valIdx[i] = new int[] {qs[i], i};
        }
        Arrays.sort(valIdx, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        int l = 0;
        int r = 0;
        var res = new int[m];
        var servCount = new HashMap<Integer, Integer>();
        for (var q : valIdx) {
            int rVal = q[0];
            int lVal = q[0] - x;
            int i = q[1];
            while (r < logs.length && logs[r][1] <= rVal) {
                servCount.merge(logs[r++][0], 1, Integer::sum);
            }
            while (l < r && logs[l][1] < lVal) {
                servCount.compute(logs[l][0], (k, v) -> v - 1);
                servCount.remove(logs[l][0], 0);
                l++;
            }
            res[i] = n - servCount.size();
        }
        return res;
    }
}