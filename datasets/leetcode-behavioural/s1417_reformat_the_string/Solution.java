package g1401_1500.s1417_reformat_the_string;

// #Easy #String #2022_07_17_Time_10_ms_(62.27%)_Space_47.7_MB_(43.56%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 1 and - `s` consists of only lowercase English letters and/or digits.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is a permutation of the input string `s`.*);
//@ ensures(*No letter is followed by another letter in the returned string.*);
//@ ensures(*No digit is followed by another digit in the returned string.*);
//@ ensures(*If it is impossible to reformat the string, an empty string is returned.*);
    public String reformat(String s) {
        List<Character> chars = new ArrayList<>();
        List<Character> digits = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                digits.add(c);
            } else {
                chars.add(c);
            }
        }
        if (Math.abs(digits.size() - chars.size()) > 1) {
            return "";
        }
        boolean isDigit = digits.size() > chars.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (isDigit) {
                sb.append(digits.remove(0));
            } else {
                sb.append(chars.remove(0));
            }
            isDigit = !isDigit;
        }
        return sb.toString();
    }
}