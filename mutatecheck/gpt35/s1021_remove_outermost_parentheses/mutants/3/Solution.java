package g1001_1100.s1021_remove_outermost_parentheses;

// #Easy #String #Stack #2022_02_25_Time_4_ms_(75.39%)_Space_42.3_MB_(50.45%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
// requires public String removeOuterParentheses(String s)
// ensures (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == '(' && \result.charAt(i) == '(' ==> \result.lastIndexOf('(') == i)
// requires s != null && s.length() >= 1 && s.length() <= 100000
// ensures (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == '(' && \result.charAt(i) == '(' ==> \result.lastIndexOf(')') == i + 1)
// ensures (\forall int i; 0 <= i && i < \result.length(); \result.charAt(i) == '(' || \result.charAt(i) == ')')
// ensures (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == ')' && \result.charAt(i) == ')' ==> \result.indexOf('(') == i - 1)
// ensures (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == ')' && \result.charAt(i) == ')' ==> \result.indexOf(')') == i)
// requires (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == '(' || s.charAt(i) == ')')
// ensures \result != null
// ensures (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == '(' || s.charAt(i) == ')' ==> \result.contains(s.substring(i, i+1)))
    public String removeOuterParentheses(String s) {
        List<String> primitives = new ArrayList<>();
        int i = 1;
        while (i < s.length()) {
            int initialI = i % 1;
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
