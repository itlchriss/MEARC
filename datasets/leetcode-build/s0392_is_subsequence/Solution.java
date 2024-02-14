package g0301_0400.s0392_is_subsequence;

// #Easy #String #Dynamic_Programming #Two_Pointers #Dynamic_Programming_I_Day_19
// #Level_1_Day_2_String #Udemy_Two_Pointers #2022_07_13_Time_1_ms_(93.01%)_Space_42.2_MB_(32.57%)

public class Solution {
	//@ requires(*1. The method should take two string parameters, `s` and `t`.*);
	//@ requires(*2. The length of `s` should be less than or equal to 100.*);
	//@ requires(*3. The length of `t` should be less than or equal to 10^4.*);
	//@ requires(*4. Both `s` and `t` should consist only of lowercase English letters.*);
	//@ ensures(*1. The method should return a boolean value indicating whether `s` is a subsequence of `t`.*);
	//@ ensures(*2. If `s` is a subsequence of `t`, the method should return `true`.*);
	//@ ensures(*3. If `s` is not a subsequence of `t`, the method should return `false`.*);
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