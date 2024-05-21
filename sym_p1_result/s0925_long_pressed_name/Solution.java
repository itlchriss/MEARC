package g0901_1000.s0925_long_pressed_name;

// #Easy #String #Two_Pointers #2022_03_29_Time_1_ms_(84.87%)_Space_40.1_MB_(93.12%)

public class Solution {
//@ requires(*Your friend is typing his param_name into a keyboard.*);
//@ requires(*Sometimes, when typing a character `c`, the key might get long pressed, and the character will be typed 1 or more times.*);
//@ requires(*You examine the param_typed characters of the keyboard.*);
//@ requires(*Return `True` if it is possible that it was your friends name, with some characters (possibly none) being long pressed.*);
//@ requires(*Example 1:*);
//@ requires(*Input: name = "alex", typed = "aaleex"*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 'a' and 'e' in 'alex' were long pressed.*);
//@ requires(*Example 2:*);
//@ requires(*Input: name = "saeed", typed = "ssaaedd"*);
//@ requires(*Output: false*);
//@ requires(*Explanation: 'e' must have been pressed twice, but it was not in the typed output.*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= name.length, typed.length <= 1000`*);
//@ requires(*param_name and param_typed consist of only lowercase English letters.*);
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