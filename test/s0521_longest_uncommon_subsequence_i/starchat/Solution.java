package g0501_0600.s0521_longest_uncommon_subsequence_i;

// #Easy #String #2022_07_28_Time_0_ms_(100.00%)_Space_40.2_MB_(87.89%)

public class Solution {
    /*
     * The gotcha point of this question is:
     * 1. if a and b are identical, then there will be no common subsequence, return -1
     * 2. else if a and b are of equal length, then any one of them will be a subsequence of the other string
     * 3. else if a and b are of different length, then the longer one is a required subsequence because
     * the longer string cannot be a subsequence of the shorter one
     * Or in other words, when a.length() != b.length(), no subsequence of b will be equal to a,
     * so return Math.max(a.length(), b.length())
     */
//@ requires(*The length of the string parameters `a` and `b` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameters `a` and `b` consist of only English lowercase letters.*);
//@ ensures(*If the integer result is greater than or equal to 1, the string parameter `a` or `b` is not a subsequence of the other string parameter.*);
//@ ensures(*If the integer result is equal to -1, the string parameter `a` is a subsequence of the string parameter `b` and the string parameter `b` is a subsequence of the string parameter `a`.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aaa", the integer result is equal to -1.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aba", the integer result is equal to 3.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aaa", the string parameter `b` is equal to "bbb", the integer result is equal to 3.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aaa", the string parameter `b` is equal to "aaa", the integer result is equal to -1.*);
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}