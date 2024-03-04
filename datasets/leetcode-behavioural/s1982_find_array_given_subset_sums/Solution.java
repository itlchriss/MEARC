package g1901_2000.s1982_find_array_given_subset_sums;

// #Hard #Array #Divide_and_Conquer #2022_05_21_Time_70_ms_(64.91%)_Space_83.3_MB_(70.18%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` represents the length of the unknown array.*);
//@ ensures(*The input array `sums` contains the values of all subset sums of the unknown array.*);
//@ ensures(*The length of `sums` is equal to `2^n`.*);
//@ ensures(*The values in `sums` are integers between -10^4 and 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array `ans` of length `n` representing the unknown array.*);
//@ ensures(*The values in `ans` can be any permutation of the correct answer.*);
//@ ensures(*The method returns at least one correct answer.*);
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int m = sums.length;
        int zeroShift = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int diff = sums[1] - sums[0];
            int p = 0;
            int k = 0;
            int zpos = m;
            for (int j = 0; j < m; ++j) {
                if (k < p && sums[k] == sums[j]) {
                    k++;
                } else {
                    if (zeroShift == sums[j]) {
                        zpos = p;
                    }
                    sums[p++] = sums[j] + diff;
                }
            }
            if (zpos >= m / 2) {
                res[i] = -diff;
            } else {
                res[i] = diff;
                zeroShift += diff;
            }
            m /= 2;
        }
        return res;
    }
}