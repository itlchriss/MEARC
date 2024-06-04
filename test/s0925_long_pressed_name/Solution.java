package g0901_1000.s0925_long_pressed_name;

// #Easy #String #Two_Pointers #2022_03_29_Time_1_ms_(84.87%)_Space_40.1_MB_(93.12%)

public class Solution {
//@ requires(*The length of the string parameter `name` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `typed` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameters `name` and `typed` consist of only lowercase English letters.*);
//@ ensures(*If the string parameter `typed` contains all characters of the string parameter `name`, the boolean result is true.*);
//@ ensures(*If the string parameter `typed` does not contain all characters of the string parameter `name`, the boolean result is false.*);
//@ ensures(*If a character in the string parameter `name` is long pressed in the string parameter `typed`, the boolean result is true.*);
//@ ensures(*If a character in the string parameter `name` is not long pressed in the string parameter `typed`, the boolean result is false.*);
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