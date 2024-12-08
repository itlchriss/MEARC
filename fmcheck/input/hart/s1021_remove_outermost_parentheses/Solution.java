package g1001_1100.s1021_remove_outermost_parentheses;

// #Easy #String #Stack #2022_02_25_Time_4_ms_(75.39%)_Space_42.3_MB_(50.45%)

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((s.length() <= 100000) && (s.length() >= 1));
//@ ensures((s.equals("(()())(())")) ==> (\result.equals("()()()")));
//@ ensures((s.equals("()()")) ==> (\result.equals("")));
//@ ensures((s.equals("(()())(())(()(()))")) ==> (\result.equals("()()()()(())")));
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
