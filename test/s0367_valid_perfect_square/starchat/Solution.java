package g0301_0400.s0367_valid_perfect_square;

// #Easy #Math #Binary_Search #Binary_Search_I_Day_3
// #2022_07_12_Time_0_ms_(100.00%)_Space_40.9_MB_(49.73%)

public class Solution {
//@ requires(*The integer parameter `num` is less than or equal to 2147483647 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Longest Substring Without Repeating Characters and Unique Digits*);
//@ requires(*Hard*);
//@ requires(*Given a string `s`, return _the length of the longest substring without repeating characters and unique digits_.*);
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
//@ requires(***Explanation:** The answer is  "wke", with the length of 3.*);
//@ requires(*Note that the answer must be a substring,  "pwke" is a subsequence and not a substring.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 5 * 104`*);
//@ requires(**   `s` consists of English letters, digits, and symbols.*);
//@ requires(**);
//@ requires(*Method signature: public int lengthOfLongestSubstring(String s)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The length of the string parameter `s` is less than or equal to 50000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only English letters, digits, and symbols.*);
//@ requires(*If the string parameter `s` is equal to "11111111111111111111111111111111111111111111*);
//@ ensures(*If the integer parameter `num` is a perfect square, the boolean result is true.*);
//@ ensures(*If the integer parameter `num` is not a perfect square, the boolean result is false.*);
//@ ensures(*If the integer parameter `num` is equal to 16, the boolean result is true.*);
//@ ensures(*If the integer parameter `num` is equal to 14, the boolean result is false.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "abcabcbb", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "bbbbb", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "pwwkew", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "12345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "abcdefghijklmnopqrstuvwxyz", the integer result is equal to 26.*);
//@ ensures(*If the string parameter `s` is equal to "0123456789012345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "11111111111111111111111111111111", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "1234567890123456789012345678901234567890123456789012345678901234567890", the integer result is equal to 10.*);
//@ ensures(*If the string parameter `s` is equal to "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz", the integer result is equal to 26.*);
//@ ensures(*If the string parameter `s` is equal to "012345678901234567890123456789012345678901234567890123456789012345678901234567890", the integer result is equal to 10.*);
    public boolean isPerfectSquare(int num) {
        if (num == 0) {
            // If num is 0 return false
            return false;
        }
        // long datatype can holds huge number.
        long start = 0;
        long end = num;
        long mid;
        while (start <= end) {
            // until start is lesser or equal to end do this
            // Finding middle value
            mid = start + (end - start) / 2;
            if (mid * mid == num) {
                // if mid*mid == num return true
                return true;
            } else if (mid * mid < num) {
                // if num is greater than mid*mid then make start = mid + 1
                start = mid + 1;
            } else if (mid * mid > num) {
                // if num is lesser than mid*mid then make end = mid - 1
                end = mid - 1;
            }
        }
        return false;
    }
}