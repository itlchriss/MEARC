package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
//@ requires(*The integer parameter `num` is less than or equal to 231 - 1 and is greater than or equal to -231.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Longest Substring Without Repeating Characters*);
//@ requires(*Medium*);
//@ requires(*Given a string `s`, return _the length of the longest substring without repeating characters_.*);
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
//@ requires(*Software specification: 2416\. Longest Substring with At Least K Repeating Characters*);
//@ requires(*Hard*);
//@ requires(*Given a string `s` and an integer `k`, return _the length of the longest substring of_ `s` _that contains at least_ `k` _repeating characters_.*);
//@ requires(**);
//@ requires(*If there is no such substring, return `0`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaabbcc ", k = 2*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** The longest substring is  "aaabb ", as 'a' is repeated 2 times and 'b' is repeated 2 times.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "ababbc ", k = 2*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** The longest substring is  "ababb ", as 'a' is repeated 2 times and 'b' is repeated 2 times.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 104`*);
//@ requires(**   `s` consists of only lowercase English letters.*);
//@ requires(**   `1 <= k <= 105`*);
//@ requires(**);
//@ requires(*Method signature: public int longestSubstring(String s, int k)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ ensures(*The string result should consist of only lowercase characters.*);
//@ ensures(*The string result should not have any leading zeros except for the zero itself.*);
//@ ensures(*If the integer parameter `num` is equal to 26, the string result is equal to "1a".*);
//@ ensures(*If the integer parameter `num` is equal to -1, the string result is equal to "ffffffff".*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "abcabcbb", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "bbbbb", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "pwwkew", the integer result is equal to 3.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "aaabbcc" and the integer parameter `k` is equal to 2, the integer result is equal to 5.*);
//@ ensures(*If the string parameter `s` is equal to "ababbc" and the integer parameter `k` is equal to 2, the integer result is equal to 5.*);
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int x;
        while (num != 0) {
            x = num & 0xf;
            if (x < 10) {
                sb.append(x);
            } else {
                sb.append((char) (x + 87));
            }
            num = num >>> 4;
        }
        return sb.reverse().toString();
    }
}