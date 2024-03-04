package g1601_1700.s1663_smallest_string_with_a_given_numeric_value;

// #Medium #String #Greedy #2022_04_23_Time_22_ms_(75.05%)_Space_64.3_MB_(37.20%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `n` and `k` are both positive.*);
//@ ensures(*`n` is less than or equal to `k`.*);
//@ ensures(*`k` is less than or equal to `26 * n`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string has a length equal to `n`.*);
//@ ensures(*The returned string has a numeric value equal to `k`.*);
//@ ensures(*The returned string is lexicographically smallest among all strings with length `n` and numeric value `k`.*);
    public String getSmallestString(int n, int k) {
        char[] res = new char[n];
        Arrays.fill(res, 'a');
        k -= n;
        while (k > 0) {
            res[--n] += Math.min(25, k);
            k -= Math.min(25, k);
        }
        return String.valueOf(res);
    }
}