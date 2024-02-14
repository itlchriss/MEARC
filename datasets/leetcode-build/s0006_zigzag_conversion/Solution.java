package g0001_0100.s0006_zigzag_conversion;

// #Medium #String #2024_01_04_Time_2_ms_(99.60%)_Space_44.7_MB_(38.67%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input string `s` consists of English letters (lower-case and upper-case), `','` and `'.'`.*);
	//@ requires(*The input integer `numRows` is greater than or equal to 1.*);
	//@ requires(*The input integer `numRows` is less than or equal to 1000.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The output string is not empty.*);
	//@ ensures(*The output string consists of the characters from the input string `s` arranged in a zigzag pattern.*);
	//@ ensures(*The output string is read line by line.*);
	//@ ensures(*The output string has the same length as the input string `s`.*);
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
                    index += i * 2;
                }
            }
        }
        return buf.toString();
    }
}