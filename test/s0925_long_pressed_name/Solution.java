package g0901_1000.s0925_long_pressed_name;

// #Easy #String #Two_Pointers #2022_03_29_Time_1_ms_(84.87%)_Space_40.1_MB_(93.12%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The lengths of `name` and `typed` are both greater than or equal to 1.*);
//@ ensures(*`name` and `typed` consist of only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if it is possible that the characters in `typed` were typed from `name` with some characters (possibly none) being long pressed.*);
//@ ensures(*The method returns `false` if it is not possible that the characters in `typed` were typed from `name` with some characters (possibly none) being long pressed.*);
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        char prev = '$';
        if (typed.length() < name.length()) {
            return false;
        }
        while (i < name.length() && j < typed.length()) {
            while (j < typed.length() && typed.charAt(j) != name.charAt(i)) {
                if (typed.charAt(j) != prev) {
                    return false;
                }
                if (j == typed.length() - 1) {
                    return false;
                }
                j++;
            }
            prev = name.charAt(i);
            i++;
            j++;
        }
        if (i < name.length()) {
            return false;
        }
        for (; j < typed.length(); j++) {
            if (typed.charAt(j) != prev) {
                return false;
            }
        }
        return true;
    }
}