package g0501_0600.s0520_detect_capital;

// #Easy #String #2022_07_25_Time_2_ms_(65.95%)_Space_42_MB_(74.10%)

public class Solution {
//@ requires(*The length of the string parameter `word` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `word` consists of only English lowercase and uppercase letters.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Longest Substring Without Repeating Characters and Unique Digits*);
//@ requires(*Hard*);
//@ requires(*Given a string `s`, return _the length of the longest substring without repeating characters and unique digits_.*);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "abcabcbb "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The substring without repeating characters and unique digits is  "abc ", which its length is 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "bbbbb "*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** The substring without repeating characters and unique digits is  "", which its length is 0.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 104`*);
//@ requires(**   `s` consists of English letters, digits, and symbols.*);
//@ requires(**);
//@ requires(*Method signature: public int longestSubstring(String s)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only English letters, digits, and symbols.*);
//@ requires(*If the string parameter `s` is equal*);
//@ ensures(*The boolean result is `true` if the usage of capitals in the string parameter `word` is right and `false` otherwise.*);
//@ ensures(*If the string parameter `word` is equal to "USA", the boolean result is `true`.*);
//@ ensures(*If the string parameter `word` is equal to "leetcode", the boolean result is `false`.*);
//@ ensures(*If the string parameter `word` is equal to "Google", the boolean result is `true`.*);
//@ ensures(*If the string parameter `word` is equal to "FlaG", the boolean result is `false`.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "abcabcbb", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "bbbbb", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `s` is equal to "12345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "aaaaaaaaaa", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "abc123abc123", the integer result is equal to 6.*);
//@ ensures(*If the string parameter `s` is equal to "123456789012345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "abc123abc123abc123abc123", the integer result is equal to 6.*);
//@ ensures(*If the string parameter `s` is equal to "12345678901234567890123456789012345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "abc123abc123abc123abc123abc123abc123abc123abc123", the integer result is equal to 6.*);
//@ ensures(*If the string parameter `s` is equal to "1234567890123456789012345678901234567890123456789012345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123abc123", the integer result is equal to 6.*);
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int upper = 0;
        int lower = 0;
        int n = word.length();
        boolean firstUpper = Character.isUpperCase(word.charAt(0));
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                upper++;
            } else if (Character.isLowerCase(word.charAt(i))) {
                lower++;
            }
        }
        if (firstUpper && upper > 1) {
            firstUpper = false;
        }
        return upper == n || lower == n || firstUpper;
    }
}