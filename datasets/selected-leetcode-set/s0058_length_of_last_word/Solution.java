package g0001_0100.s0058_length_of_last_word;

// #Easy #String #Programming_Skills_II_Day_6 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.3_MB_(97.60%)

public class Solution {
//@ ensures(*The string parameter `s` consists of only English letters and spaces.*);
//@ ensures(*The length of the last word in the string `s` is returned as an integer result.*);
//@ ensures(*The last word is a maximal substring consisting of non-space characters only.*);
//@ ensures(*The integer result is greater than or equal to 1.*);
//@ ensures(*The integer result is less than or equal to 10000.*);
//@ ensures(*There will be at least one word in the string `s`.*);
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