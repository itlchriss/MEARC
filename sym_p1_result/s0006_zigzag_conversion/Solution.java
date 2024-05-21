package g0001_0100.s0006_zigzag_conversion;

// #Medium #String #2024_01_04_Time_2_ms_(99.60%)_Space_44.7_MB_(38.67%)

public class Solution {
//@ requires(*The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)*);
//@ requires(*P A H N A P L S I I G Y I R*);
//@ requires(*And then read line by line: `"PAHNAPLSIIGYIR"`*);
//@ requires(*Write the code that will take a string and make this conversion given a number of rows:*);
//@ requires(*string convert(string s, int numRows);*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "PAYPALISHIRING", numRows = 3*);
//@ requires(*Output: "PAHNAPLSIIGYIR"*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "PAYPALISHIRING", numRows = 4*);
//@ requires(*Output: "PINALSIGYAHRPI"*);
//@ requires(*Explanation: P I N A L S I G Y A H R P I*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "A", numRows = 1*);
//@ requires(*Output: "A"*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= s.length <= 1000`*);
//@ requires(*param_s consists of English letters (lower-case and upper-case), `','` and `'.'*);
//@ requires(*`.*);
//@ requires(*`1 <= numRows <= 1000`*);
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