package g1101_1200.s1177_can_make_palindrome_from_substring;

// #Medium #String #Hash_Table #Bit_Manipulation #Prefix_Sum
// #2022_03_03_Time_13_ms_(93.08%)_Space_136.3_MB_(53.08%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input array `queries` is not null.*);
//@ ensures(*The length of `s` is greater than or equal to the maximum value of `right_i` in `queries`.*);
//@ ensures(*The length of `queries` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned boolean array `answer` is not null.*);
//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
//@ ensures(*Each element in `answer` represents the result of the corresponding query in `queries`.*);
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        return this.canMakeP(s, queries);
    }

    private List<Boolean> canMakeP(String s, int[][] qs) {
        int n = s.length();
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            int m = 0;
            if (i > 0) {
                m = counts[i - 1];
            }
            char c = s.charAt(i);
            m = m ^ (1 << (c - 'a'));
            counts[i] = m;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : qs) {
            ans.add(check(q, counts));
        }
        return ans;
    }

    private boolean check(int[] q, int[] counts) {
        int l = q[0];
        int r = q[1];
        int k = q[2];
        int prev = l > 0 ? counts[l - 1] : 0;
        int kk = Integer.bitCount(prev ^ counts[r]);
        return (kk / 2) <= k;
    }
}