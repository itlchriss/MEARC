package g0901_1000.s0925_long_pressed_name;

// #Easy #String #Two_Pointers #2022_03_29_Time_1_ms_(84.87%)_Space_40.1_MB_(93.12%)

public class Solution {
// requires/*@ requires name != null && typed != null; @ requires name.length() > 0 && typed.length() > 0; @ requires name.length() <= 1000 && typed.length() <= 1000; @ requires (\forall int i; 0 <= i && i < name.length(); Character.isLowerCase(name.charAt(i))); @ requires (\forall int i; 0 <= i && i < typed.length(); Character.isLowerCase(typed.charAt(i))); @ @ ensures \result == true || \result == false; @*/
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
