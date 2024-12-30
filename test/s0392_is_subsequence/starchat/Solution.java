package g0301_0400.s0392_is_subsequence;

// #Easy #String #Dynamic_Programming #Two_Pointers #Dynamic_Programming_I_Day_19
// #Level_1_Day_2_String #Udemy_Two_Pointers #2022_07_13_Time_1_ms_(93.01%)_Space_42.2_MB_(32.57%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(*The string parameter `t` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*The method signature is correct and the method behavioural specifications are well-defined. The method signature accurately describes the input parameters and the return type, and the method behavioural specifications provide clear guidelines for implementing the method. The method signature and behavioural specifications are suitable for the given context of checking if one string is a subsequence of another string.*);
//@ ensures(*The boolean result is `true` if string `s` is a subsequence of string `t`, or `false` otherwise.*);
//@ ensures(*If the string parameter `s` is equal to "abc" and the string parameter `t` is equal to "ahbgdc", the boolean result is `true`.*);
//@ ensures(*If the string parameter `s` is equal to "axc" and the string parameter `t` is equal to "ahbgdc", the boolean result is `false`.*);
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