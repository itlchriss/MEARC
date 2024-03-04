package g1501_1600.s1576_replace_all_s_to_avoid_consecutive_repeating_characters;

// #Easy #String #2022_04_11_Time_2_ms_(82.74%)_Space_43.1_MB_(58.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 100 (inclusive).*);
//@ ensures(*The input string `s` consists of lowercase English letters and the '?' character.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The output string is a modified version of the input string `s` where all '?' characters have been replaced with lowercase letters.*);
//@ ensures(*The output string does not contain any consecutive repeating characters.*);
//@ ensures(*The output string has the same length as the input string `s`.*);
//@ ensures(*If there is more than one valid solution, any of them can be returned as the output.*);
    public String modifyString(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '?') {
                char replaceChar = 'a';
                char leftChar = i == 0 ? s.charAt(i) : sb.charAt(i - 1);
                char rightChar = s.charAt(Math.min(i + 1, len - 1));
                while (replaceChar == leftChar || replaceChar == rightChar) {
                    replaceChar += 1;
                }
                sb.append(replaceChar);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}