package g1801_1900.s1849_splitting_a_string_into_descending_consecutive_values;

// #Medium #String #Backtracking #2022_05_08_Time_1_ms_(87.61%)_Space_41.9_MB_(68.80%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` should not be null.*);
//@ ensures(*The length of the input string `s` should be between 1 and - The input string `s` should only consist of digits.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a boolean value indicating whether it is possible to split the string `s` as described in the requirements.*);
//@ ensures(*If it is possible to split the string `s` as described, the method should return `true`.*);
//@ ensures(*If it is not possible to split the string `s` as described, the method should return `false`.*);
    public boolean splitString(String s) {
        return solve(0, -1, s, 0);
    }

    private boolean solve(int i, long prev, String s, int k) {
        if (i == s.length()) {
            return k >= 2;
        }
        long cur = 0;
        for (int j = i; j < s.length(); j++) {
            cur = cur * 10 + s.charAt(j) - '0';
            if ((prev == -1 || prev - cur == 1) && solve(j + 1, cur, s, k + 1)) {
                return true;
            }
        }
        return false;
    }
}