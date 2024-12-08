package g2101_2200.s2124_check_if_all_as_appears_before_all_bs;

// #Easy #String #2022_06_02_Time_1_ms_(73.82%)_Space_41.7_MB_(67.70%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is greater than or equal to 1.*);
//@ ensures(*The characters in the input string `s` are either 'a' or 'b'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether every 'a' appears before every 'b' in the input string `s`.*);
    public boolean checkString(String s) {
        int aEndIndex = -1;
        int bStartIndex = -1;
        if (s.length() == 1) {
            return true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') {
                aEndIndex = i;
                break;
            }
        }
        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.charAt(i) == 'b') {
                bStartIndex = i;
                break;
            }
        }
        if (aEndIndex == -1 || bStartIndex == -1) {
            return true;
        }
        return bStartIndex > aEndIndex;
    }
}