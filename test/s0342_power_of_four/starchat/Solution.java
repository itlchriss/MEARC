package g0301_0400.s0342_power_of_four;

// #Easy #Math #Bit_Manipulation #Recursion #2022_07_10_Time_1_ms_(100.00%)_Space_41.2_MB_(55.90%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ requires(*If the integer parameter `n` is a power of four, the method should return `true`.*);
//@ requires(*If the integer parameter `n` is not a power of four, the method should return `false`.*);
//@ requires(*The method should not use loops or recursion.*);
//@ requires(*The method should have a time complexity of O(1).*);
//@ requires(*The method should have a space complexity of O(1).*);
//@ requires(*If the integer parameter `n` is equal to 16, the method should return `true`.*);
//@ requires(*If the integer parameter `n` is equal to 5, the method should return `false`.*);
//@ requires(*If the integer parameter `n` is equal to 1, the method should return `true`.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Longest Substring Without Repeating Characters*);
//@ requires(*Medium*);
//@ requires(*Given a string `s`, return _the length of the longest_ **substring** _without repeating characters_.*);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "abcabcbb "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The answer is "abc", with the length of 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "bbbbb "*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** The answer is "b", with the length of 1.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** s =  "pwwkew "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The answer is  "wke ", with the length of 3.*);
//@ requires(*Notice that the answer must be a substring,  "pwke " is a subsequence and not a substring.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `0 <= s.length <= 5 * 104`*);
//@ requires(**   `s` consists of English letters, digits, symbols and spaces.*);
//@ requires(**);
//@ requires(*Method signature: public int lengthOfLongestSubstring(String s)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The length of the string parameter `s` is less than or equal to 50000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `s` consists of only English letters, digits, symbols and spaces.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2416\. Check If a String Contains All Binary Codes of Size K*);
//@ requires(*Hard*);
//@ requires(*Given a binary string `s` and an integer `k`, return `true` _if every binary code of length_ `k` _is a substring of_ `s`. Otherwise, return `false`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "00110 ", k = 2*);
//@ requires(***Output:** true*);
//@ requires(***Explanation:** The binary codes of length 2 are  "00 ",  "01 ",  "10 ", and  "11 ". All these codes are substrings of s.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "00110 ", k = 3*);
//@ requires(***Output:** false*);
//@ requires(***Explanation:** The binary codes of length 3 are  "001 ",  "010 ", and  "110 ".  "001 " is a substring of s, but  "010 " and  "110 " are not.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 5 * 104`*);
//@ requires(**   `s[i]` is either  `'0'` or  `'1'`.*);
//@ requires(**   `1 <= k <= 20`*);
//@ requires(**);
//@ requires(*Method signature: public boolean hasAllCodes(String s, int k)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The length of the string parameter `s` is less than or equal to 50000 and is greater than or equal to 1.*);
//@ requires(*All characters in the string parameter `s` are either '0' or '1'.*);
//@ requires(*The integer parameter `k` is less than or equal to 20 and is greater than or equal to 1.*);
//@ requires(*-*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "abcabcbb", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "bbbbb", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "pwwkew", the integer result is equal to 3.*);
    public boolean isPowerOfFour(int n) {
        while (n >= 4) {
            if (n % 4 != 0) {
                return false;
            }
            n = n / 4;
        }
        return n == 1;
    }
}