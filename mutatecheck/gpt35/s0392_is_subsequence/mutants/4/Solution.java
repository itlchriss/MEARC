package g0301_0400.s0392_is_subsequence;

// #Easy #String #Dynamic_Programming #Two_Pointers #Dynamic_Programming_I_Day_19
// #Level_1_Day_2_String #Udemy_Two_Pointers #2022_07_13_Time_1_ms_(93.01%)_Space_42.2_MB_(32.57%)

public class Solution {
//public boolean isSubsequence(String s, String t) { int sIndex = 0; int tIndex = 0; while (sIndex < s.length() && tIndex < t.length()) { if (s.charAt(sIndex) == t.charAt(tIndex)) { sIndex++; } tIndex++; } return sIndex == s.length();
// requires/*@ requires s != null && t != null; @ requires s.length() <= 100; @ requires t.length() <= 10000; @ requires s.matches("[a-z]*"); @ requires t.matches("[a-z]*"); @ ensures \result == true || \result == false; @*/
//}

//@ requires s != null && t != null; 
//@ requires s.length() <= 100; 
//@ requires t.length() <= 10000; 
//@ requires s.matches("[a-z]*"); 
//@ requires t.matches("[a-z]*"); 
//@ ensures \result == true || \result == false;
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        int n = t.length();
        int m = s.length();
        if (m == 0) {
            return true;
        }
        while (j != n) {
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
