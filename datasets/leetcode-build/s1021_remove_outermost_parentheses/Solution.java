package g1001_1100.s1021_remove_outermost_parentheses;

// #Easy #String #Stack #2022_02_25_Time_4_ms_(75.39%)_Space_42.3_MB_(50.45%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input string `s` is a valid parentheses string.*);
	//@ requires(*The length of `s` is between 1 and 10^5.*);
	//@ requires(*Each character in `s` is either '(' or ')'.*);
	//@ ensures(*The output is a string that represents `s` after removing the outermost parentheses of every primitive string in the primitive decomposition of `s`.*);
	//@ ensures(*The output string is a valid parentheses string.*);
    public String removeOuterParentheses(String s) {
        List<String> primitives = new ArrayList<>();
        int i = 1;
        while (i < s.length()) {
            int initialI = i - 1;
            int left = 1;
            while (i < s.length() && left > 0) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    left--;
                }
                i++;
            }
            primitives.add(s.substring(initialI, i));
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (String primitive : primitives) {
            sb.append(primitive, 1, primitive.length() - 1);
        }
        return sb.toString();
    }
}