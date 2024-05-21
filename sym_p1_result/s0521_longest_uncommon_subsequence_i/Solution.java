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
//@ requires(*An uncommon subsequence between two strings is a string that is a subsequence of one but not the other.*);
//@ requires(*A subsequence of a string `s` is a string that can be obtained after deleting any number of characters from `s`.*);
//@ requires(*For example, `"abc"` is a subsequence of `"aebdc"` because you can delete the underlined characters in `"aebdc"` to get `"abc"`.*);
//@ requires(*Other subsequences of `"aebdc"` include `"aebdc"`, `"aeb"`, and `""` (empty string).*);
//@ requires(*Example 1:*);
//@ requires(*Input: a = "aba", b = "cdc"*);
//@ requires(*Output: 3*);
//@ requires(*Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of "aba" but not "cdc".*);
//@ requires(*Note that "cdc" is also a longest uncommon subsequence.*);
//@ requires(*Example 2:*);
//@ requires(*Input: a = "aaa", b = "bbb"*);
//@ requires(*Output: 3*);
//@ requires(*Explanation: The longest uncommon subsequences are "aaa" and "bbb".*);
//@ requires(*Example 3:*);
//@ requires(*Input: a = "aaa", b = "aaa"*);
//@ requires(*Output: -1*);
//@ requires(*Explanation: Every subsequence of string a is also a subsequence of string b. Similarly, every subsequence of string b is also a subsequence of string a.*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= a.length, b.length <= 100`*);
//@ requires(*param_a and param_b consist of lower-case English letters.*);
//@ ensures(*Given two strings param_a and param_b, the result is the length of the longest uncommon subsequence between param_a and param_b.*);
//@ ensures(*If the longest uncommon subsequence does not exist, the result is `-1`.*);
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}