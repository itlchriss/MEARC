package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
//@ requires(*The integer parameters `x` and `y` are less than or equal to 231 - 1 and are greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(*Given a string `s` and an integer `k`, return _the number of non-empty substrings of_ `s` _that have_ `k` _differing characters and have the same length_.*);
//@ requires(**);
//@ requires(*Two strings `s1` and `s2` differ in one character at index `i` if `s1[i]!= s2[i]`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba ", k = 1*);
//@ requires(***Output:** 4*);
//@ requires(***Explanation:** The substrings with one differing character are  "aba ",  "aba ",  "aba ",  "aba ".*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "abcde ", k = 2*);
//@ requires(***Output:** 10*);
//@ requires(***Explanation:** The substrings with two differing characters are  "abcde ",  "abcde ",  "abcde ",  "abcde ",  "abcde ",  "abcde ",  "abcde ",  "abcde ",  "abcde ",  "abcde ".*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 100`*);
//@ requires(**   `s` consists of only lowercase English letters.*);
//@ requires(**   `0 <= k <= s.length`*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s, int k)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(*The integer parameter `k` is less than or equal to the length of the string parameter `s` and is greater than or equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the number of bits in the larger of the two input integers.*);
//@ ensures(*If the integer parameters `x` and `y` are equal to 1 and 4 respectively, the integer result is equal to 2.*);
//@ ensures(*If the integer parameters `x` and `y` are equal to 3 and 1 respectively, the integer result is equal to 1.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the number of substrings of the string parameter `s` that have `k` differing characters and have the same length.*);
//@ ensures(*If the string parameter `s` is equal to "aba" and the integer parameter `k` is equal to 1, the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is equal to "abcde" and the integer parameter `k` is equal to 2, the integer result is equal to 10.*);
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}