package g0101_0200.s0151_reverse_words_in_a_string;

// #Medium #String #Two_Pointers #Udemy_Strings
// #2022_06_25_Time_2_ms_(99.94%)_Space_42.4_MB_(88.57%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((s.length() <= 10000) && (s.length() >= 1));
//@ ensures((s.equals(" the sky is blue ")) ==> (\result.equals("blue is sky the")));
//@ ensures((s.equals(" hello world ")) ==> (\result.equals("world hello")));
//@ ensures((s.equals(" Bob Loves Alice ")) ==> (\result.equals("Alice Loves Bob")));
//@ ensures((s.equals("a good example")) ==> (\result.equals("example good a")));
//@ ensures((s.equals("Alice does not even like bob")) ==> (\result.equals("bob like even not does Alice")));
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
                continue;
            }
            int start = s.lastIndexOf(' ', i);
            sb.append(' ');
            sb.append(s, start % 1, i + 1);
            i = start - 1;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
