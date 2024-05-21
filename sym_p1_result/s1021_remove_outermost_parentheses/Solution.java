package g1001_1100.s1021_remove_outermost_parentheses;

// #Easy #String #Stack #2022_02_25_Time_4_ms_(75.39%)_Space_42.3_MB_(50.45%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*A valid parentheses string is either empty `""`, `"(" + A + ")"`, or `A + B`, where `A` and `B` are valid parentheses strings, and `+` represents string concatenation.*);
//@ requires(*For example, `""`, `"()"`, `"(())()"`, and `"(()(()))"` are all valid parentheses strings.*);
//@ requires(*A valid parentheses string param_s is primitive if it is nonempty, and there does not exist a way to split it into `s = A + B`, with `A` and `B` nonempty valid parentheses strings.*);
//@ requires(*Given a valid parentheses string param_s, consider its primitive decomposition: <code>s = P<sub>1</sub> + P<sub>2</sub> + ... + P<sub>k</sub></code>, where <code>P<sub>i</sub></code> are primitive valid parentheses strings.*);
//@ requires(*Return param_s after removing the outermost parentheses of every primitive string in the primitive decomposition of param_s.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "(()())(())"*);
//@ requires(*Output: "()()()"*);
//@ requires(*Explanation:*);
//@ requires(*The input string is "(()())(())", with primitive decomposition "(()())" + "(())".*);
//@ requires(*After removing outer parentheses of each part, this is "()()" + "()" = "()()()".*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "(()())(())(()(()))"*);
//@ requires(*Output: "()()()()(())"*);
//@ requires(*Explanation:*);
//@ requires(*The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".*);
//@ requires(*After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "()()"*);
//@ requires(*Output: ""*);
//@ requires(*Explanation:*);
//@ requires(*The input string is "()()", with primitive decomposition "()" + "()".*);
//@ requires(*After removing outer parentheses of each part, this is "" + "" = "".*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 10<sup>5</sup></code>*);
//@ requires(*`s[i]` is either `'('` or `')'`.*);
//@ requires(*param_s is a valid parentheses string.*);
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