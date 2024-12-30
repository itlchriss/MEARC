package g0001_0100.s0006_zigzag_conversion;

// #Medium #String #2024_01_04_Time_2_ms_(99.60%)_Space_44.7_MB_(38.67%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of English letters, commas, and periods.*);
//@ requires(*The integer parameter `numRows` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ ensures(*If the integer parameter `numRows` is equal to 1, the string result is equal to the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "PAYPALISHIRING" and the integer parameter `numRows` is equal to 3, the string result is equal to "PAHNAPLSIIGYIR".*);
//@ ensures(*If the string parameter `s` is equal to "PAYPALISHIRING" and the integer parameter `numRows` is equal to 4, the string result is equal to "PINALSIGYAHRPI".*);
//@ ensures(*If the string parameter `s` is equal to "A" and the integer parameter `numRows` is equal to 1, the string result is equal to "A".*);
    public String convert(String s, int numRows) {
        int sLen = s.length();
        if (numRows == 1) {
            return s;
        }
        //@ assume 0 <= numRows <= (Integer.MAX_VALUE - 3)/2;
        //@ ghost int k = numRows;
        //@ ghost int sl = s.length();
        // assume Integer.MIN_VALUE + 3 <= k * 2 <= Integer.MAX_VALUE - 3;
        //@ set k = numRows; 
        int maxDist = numRows * 2 - 2;
        StringBuilder buf = new StringBuilder();
        //@ loop_invariant 0 <= i <= k;
        for (int i = 0; i < numRows; i++) {
            int index = i;
            if (i == 0 || i == numRows - 1) {
                while (index < sLen) {
                    buf.append(s.charAt(index));
                    // assume Integer.MIN_VALUE + 1 <= index + maxDist <= Integer.MAX_VALUE - 1;
                    index += maxDist;
                }
            } else {                
                while (index < sLen) {
                    buf.append(s.charAt(index));
                    // assume Integer.MIN_VALUE + 1 <= index + maxDist - i * 2 <= Integer.MAX_VALUE - 1;
                    index += maxDist - i * 2;
                    if (index >= sLen) {
                        break;
                    }
                    buf.append(s.charAt(index));
                    // assume Integer.MIN_VALUE + 1 <= index + i * 2 <= Integer.MAX_VALUE - 1;
                    index += i * 2;
                }
            }
        }
        return buf.toString();
    }
}