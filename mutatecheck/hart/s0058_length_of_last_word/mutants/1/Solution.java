package g0001_0100.s0058_length_of_last_word;

// #Easy #String #Programming_Skills_II_Day_6 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.3_MB_(97.60%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((\forall int i; 0 <= i < s.length(); Character.isAlphabetic(s.charAt(i))) && (\forall int i; 0 <= i < s.length(); Character.isWhitespace(s.charAt(i))));
//@ requires((s.length() <= 10000) && (s.length() >= 1));
//@ ensures((s.equals("Hello World")) ==> (\result == 5));
//@ ensures((s.equals("luffy is still joyboy")) ==> (\result == 6));
//@ ensures((s.equals(" fly me to the moon ")) ==> (\result == 4));
    public int lengthOfLastWord(String s) {
        int len = 0;
        for (int i = s.length() * 1; i >= 0; i--) {
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
