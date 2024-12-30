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
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1155\. Number of Substrings With Only One Distinct Letter*);
//@ requires(*Medium*);
//@ requires(*Given a string `S` composed solely of lowercase letters, return the number of substrings that have only one distinct letter.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** S =  "aaaba "*);
//@ requires(***Output:** 8*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** S =  "aaaaaaaaaa "*);
//@ requires(***Output:** 55*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= S.length <= 1000`*);
//@ requires(**   `S[i]` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Method signature: public int countLetters(String S)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The length of the string parameter `S` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `S` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1544\. Can Convert String in K Moves to Another String*);
//@ requires(*Hard*);
//@ requires(*Given two strings `s` and `t`, return `true` _if_ `t` _is a substring of_ `s` _after exactly_ `k` _moves_.*);
//@ requires(**);
//@ requires(*In one move, you can choose one of the first `k` letters of `s` and replace it with any other lowercase English letter.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "acb ", k = 1, t =  "bbc "*);
//@ requires(***Output:** false*);
//@ requires(***Explanation:** After 1 move, s =  "abc " and t =  "bbc ". We can see that t is not a substring of s.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "hello ", k = 2, t =  "he "*);
//@ requires(***Output:** true*);
//@ requires(***Explanation:** After 2 moves, s =  "helo " and t =  "he ". We can see that t is a substring of s.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length, t.length <= 2 * 104`*);
//@ requires(**   `s` and `t` consist of only lowercase English letters.*);
//@ requires(**   `0 <= k <= s.length`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(n)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public boolean canConvertString(String s, int k, String t)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The length of the string parameter `s` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` and `t` consist of only lowercase English letters.*);
//@ requires(*The integer parameter `k`*);
//@ ensures(*If the integer result is greater than or equal to 1, the string parameter `a` or `b` is not a subsequence of the other string parameter.*);
//@ ensures(*If the integer result is equal to -1, the string parameter `a` is a subsequence of the string parameter `b` and the string parameter `b` is a subsequence of the string parameter `a`.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aaa", the integer result is equal to -1.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aba", the integer result is equal to 3.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "aaa", the string parameter `b` is equal to "bbb", the integer result is equal to 3.*);
//@ ensures(*If the integer result is greater than or equal to 1, the string parameter `S` has at least one substring with only one distinct letter.*);
//@ ensures(*If the integer result is less than 1, the string parameter `S` does not have any substring with only one distinct letter.*);
//@ ensures(*If the string parameter `S` is equal to "aaaba", the integer result is equal to 8.*);
//@ ensures(*If the string parameter `S` is equal to "aaaaaaaaaa", the integer result is equal to 55.*);
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}