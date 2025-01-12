package g0301_0400.s0392_is_subsequence;

// #Easy #String #Dynamic_Programming #Two_Pointers #Dynamic_Programming_I_Day_19
// #Level_1_Day_2_String #Udemy_Two_Pointers #2022_07_13_Time_1_ms_(93.01%)_Space_42.2_MB_(32.57%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `s` consists only of lowercase English letters.*);
//@ requires(*The string parameter `t` consists only of lowercase English letters.*);
//@ requires(*If the string parameter `s` is equal*);
//@ ensures(*The boolean result is true if string parameter `s` is a subsequence of string parameter `t`, or false otherwise.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "ahbgdc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "axc" and the string parameter `t` is equal to "ahbgdc", the boolean result is false.*);
//@ ensures(*If the string parameter `s` is equal to "a" and the string parameter `t` is equal to "ab", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "b" and the string parameter `t` is equal to "ab", the boolean result is false.*);
//@ ensures(*If the string parameter `s` is equal to "ab" and the string parameter `t` is equal to "ab", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abcd" and the string parameter `t` is equal to "abcde", the boolean result is false.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcdefg", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "xyz" and the string parameter `t` is equal to "abc", the boolean result is false.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabcabcabcabcabcabc", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "abcabcabcabcabcabcabcabcabcabcabcabcabcabc", the boolean result is true.*);
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        int n = t.length();
        int m = s.length();
        if (m == 0) {
            return true;
        }
        while (j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                if (i == m) {
                    return true;
                }
            }
            j++;
        }
        return false;
    }
}