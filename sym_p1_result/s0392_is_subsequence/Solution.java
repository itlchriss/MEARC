package g0301_0400.s0392_is_subsequence;

// #Easy #String #Dynamic_Programming #Two_Pointers #Dynamic_Programming_I_Day_19
// #Level_1_Day_2_String #Udemy_Two_Pointers #2022_07_13_Time_1_ms_(93.01%)_Space_42.2_MB_(32.57%)

public class Solution {
//@ requires(*A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (*);
//@ requires(*i.e., `"ace"` is a subsequence of `"abcde"` while `"aec"` is not).*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "abc", t = "ahbgdc"*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "axc", t = "ahbgdc"*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*`0 <= s.length <= 100`*);
//@ requires(*<code>0 <= t.length <= 10<sup>4</sup></code>*);
//@ requires(*param_s and param_t consist only of lowercase English letters.*);
//@ requires(*Follow up: Suppose there are lots of incoming param_s, say <code>s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub></code> where <code>k >= 10<sup>9</sup></code>, and you want to check one by one to see if param_t has its subsequence.*);
//@ requires(*In this scenario, how would you change your code?*);
//@ ensures(*Given two strings param_s and param_t, the result is `true` if param_s is a subsequence of param_t, or `false` otherwise.*);
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