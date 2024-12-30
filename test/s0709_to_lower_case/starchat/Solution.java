package g0701_0800.s0709_to_lower_case;

// #Easy #String #Programming_Skills_I_Day_9_String
// #2022_03_23_Time_1_ms_(71.74%)_Space_42_MB_(52.94%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of printable ASCII characters.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(*Given a string `s` and an integer `k`, return _the number of non-empty substrings of_ `s` _that have_ `k` _differing characters_.*);
//@ requires(**);
//@ requires(**   For example, if `s = "abac"` and `k = 2`, there are 4 substrings that have 2 differing characters: `"aba"`, `"aca"`, `"bab"`, and `"aca"`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "abac", k = 2*);
//@ requires(***Output:** 4*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "aaaa", k = 3*);
//@ requires(***Output:** 0*);
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
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2416\. Count Substrings That Differ by at Most Two Characters*);
//@ requires(*Medium*);
//@ requires(*Given a string `s` and an integer `k`, return _the number of non-empty substrings of_ `s` _that have_ `k` _differing characters_.*);
//@ requires(**);
//@ requires(**   For example, if `s = "abac"` and `k = 2`, there are 6 substrings that have 2 differing characters: `"aba"`, `"aca"`, `"bab"`, `"aca"`, `"bac"`, and `"abc"`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "abac", k = 2*);
//@ requires(***Output:** 6*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "aaaa", k = 3*);
//@ requires(***Output:** 0*);
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
//@ ensures(*The string result consists of lowercase letters.*);
//@ ensures(*If the string parameter `s` is equal to "Hello", the string result is equal to "hello".*);
//@ ensures(*If the string parameter `s` is equal to "LOVELY", the string result is equal to "lovely".*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "abac" and the integer parameter `k` is equal to 2, the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is equal to "aaaa" and the integer parameter `k` is equal to 3, the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "abac" and the integer parameter `k` is equal to 2, the integer result is equal to 6.*);
//@ ensures(*If the string parameter `s` is equal to "aaaa" and the integer parameter `k` is equal to 3, the integer result is equal to 0.*);
    public String toLowerCase(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (c[i] <= 'Z' && c[i] >= 'A') {
                c[i] = (char) (c[i] - 'A' + 'a');
            }
        }
        return new String(c);
    }
}