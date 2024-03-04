package g2801_2900.s2896_apply_operations_to_make_two_strings_equal;

// #Medium #String #Dynamic_Programming #2023_12_20_Time_1_ms_(100.00%)_Space_43_MB_(50.87%)

import java.util.ArrayList;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `s1` and `s2` must be the same.*);
//@ ensures(*The length of `s1` and `s2` must be greater than or equal to 1.*);
//@ ensures(*The value of `x` must be greater than or equal to 1.*);
//@ ensures(*`s1` and `s2` must consist only of the characters '0' and '1'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum cost needed to make `s1` and `s2` equal, or -1 if it is impossible.*);
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        ArrayList<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffs.add(i);
            }
        }
        int m = diffs.size();
        if ((m & 1) == 1) {
            return -1;
        } else if (m == 0) {
            return 0;
        }
        int[] dp = new int[m];
        dp[0] = 0;
        dp[1] = Math.min(x, diffs.get(1) - diffs.get(0));
        for (int i = 2; i < m; i++) {
            if ((i & 1) == 1) {
                dp[i] = Math.min(dp[i - 1] + x, dp[i - 2] + diffs.get(i) - diffs.get(i - 1));
            } else {
                dp[i] = Math.min(dp[i - 1], dp[i - 2] + diffs.get(i) - diffs.get(i - 1));
            }
        }
        return dp[m - 1];
    }
}