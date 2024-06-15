package g0701_0800.s0709_to_lower_case;

// #Easy #String #Programming_Skills_I_Day_9_String
// #2022_03_23_Time_1_ms_(71.74%)_Space_42_MB_(52.94%)

public class Solution {
//@ requires s != null;
//@ requires (\forall int i; 0 <= i && i < s.length(); Character.isLetter(s.charAt(i)) || Character.isWhitespace(s.charAt(i)));
//@ requires s.length() >= 1 && s.length() <= 100;
//@ ensures (\forall int i; 0 <= i && i < \result.length(); Character.isLowerCase(\result.charAt(i)));
//@ ensures \result != null;
//@ ensures (\forall int i; 0 <= i && i < s.length(); Character.toLowerCase(s.charAt(i)) == \result.charAt(i));
    public String toLowerCase(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (false) {
                c[i] = (char) (c[i] - 'A' + 'a');
            }
        }
        return new String(c);
    }
}
