package g0001_0100.s0006_zigzag_conversion;

// #Medium #String #2024_01_04_Time_2_ms_(99.60%)_Space_44.7_MB_(38.67%)

public class Solution {
//@ ensures((\forall int i; 0 <= i && i < s.length(); s.charAt(i) == \result.charAt(i) || s.charAt(i) == ',' || s.charAt(i) == '.'));
//@ ensures((\forall int i; 0 <= i && i < \result.length(); s.charAt(i) == \result.charAt(i) || \result.charAt(i) == ',' || \result.charAt(i) == '.'));
//@ ensures((\forall int i; 0 <= i && i < s.length(); Character.isLetter(s.charAt(i)) || s.charAt(i) == ',' || s.charAt(i) == '.'));
//@ ensures(\result != null);
//@ ensures((\forall int i; 0 <= i && i < s.length(); Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i)) || s.charAt(i) == ',' || s.charAt(i) == '.'));
//@ ensures((\forall int i; 0 <= i && i < \result.length(); Character.isLetter(\result.charAt(i)) || \result.charAt(i) == ',' || \result.charAt(i) == '.'));
//@ requires(s != null && numRows > 0);
//@ ensures((\forall int i; 0 <= i && i < \result.length(); Character.isUpperCase(\result.charAt(i)) || Character.isLowerCase(\result.charAt(i)) || \result.charAt(i) == ',' || \result.charAt(i) == '.'));
//@ ensures(\result.length() == s.length());
    public String convert(String s, int numRows) {
        int sLen = s.length();
        if (numRows == 1) {
            return s;
        }
        int maxDist = numRows * 2 - 2;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int index = i;
            if (i == 0 || i == numRows - 1) {
                while (index < sLen) {
                    buf.append(s.charAt(index));
                    index += maxDist;
                }
            } else {
                while (index < sLen) {
                    buf.append(s.charAt(index));
                    index += maxDist - i * 2;
                    if (index >= sLen) {
                        break;
                    }
                    buf.append(s.charAt(index));

                }
            }
        }
        return buf.toString();
    }
}
