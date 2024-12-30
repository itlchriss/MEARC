package g0001_0100.s0058_length_of_last_word;

// #Easy #String #Programming_Skills_II_Day_6 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.3_MB_(97.60%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only English letters and spaces.*);
//@ requires(*The string parameter `s` will have at least one word.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "Hello World", the integer result is equal to 5.*);
//@ ensures(*If the string parameter `s` is equal to " fly me to the moon ", the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is equal to "luffy is still joyboy", the integer result is equal to 6.*);
    public int lengthOfLastWord(String s) {
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ' ' && len > 0) {
                break;
            } else if (ch != ' ') {
                len++;
            }
        }
        return len;
    }
}