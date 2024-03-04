package g1901_2000.s1910_remove_all_occurrences_of_a_substring;

// #Medium #String #2022_05_12_Time_11_ms_(39.18%)_Space_44.9_MB_(30.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input string `part` is not null.*);
//@ ensures(*The length of `s` is greater than or equal to the length of `part`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string `s` does not contain any occurrences of the substring `part`.*);
//@ ensures(*The length of the returned string `s` is less than or equal to the length of the input string `s`.*);
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (sb.length() >= part.length()
                    && sb.substring(sb.length() - part.length()).equals(part)) {
                sb.setLength(sb.length() - part.length());
            }
        }
        return sb.toString();
    }
}