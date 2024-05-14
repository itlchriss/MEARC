package g0301_0400.s0392_is_subsequence;

// #Easy #String #Dynamic_Programming #Two_Pointers #Dynamic_Programming_I_Day_19
// #Level_1_Day_2_String #Udemy_Two_Pointers #2022_07_13_Time_1_ms_(93.01%)_Space_42.2_MB_(32.57%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `s` and `t` consist only of lowercase English letters.*);
//@ ensures(*If the boolean result is equal to true, the string parameter `s` is a subsequence of the string parameter `t`.*);
//@ ensures(*If the boolean result is equal to false, the string parameter `s` is not a subsequence of the string parameter `t`.*);
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