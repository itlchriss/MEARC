package g0001_0100.s0006_zigzag_conversion;

// #Medium #String #2024_01_04_Time_2_ms_(99.60%)_Space_44.7_MB_(38.67%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of English letters (lower-case and upper-case), ',' and '.'.*);
//@ requires(*The integer parameter `numRows` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1323\. Maximum 69 Number*);
//@ requires(*Easy*);
//@ requires(*Given a positive integer `num` consisting only of digits 6 and 9.*);
//@ requires(**);
//@ requires(*Return _the maximum number you can get by changing **at most** one digit (6 becomes 9, and 9 becomes 6)._*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** num = 9669*);
//@ requires(***Output:** 9969*);
//@ requires(***Explanation:** *);
//@ requires(**);
//@ requires(**);
//@ requires(**);
//@ requires(**);
//@ requires(*The maximum number is 9969.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** num = 9996*);
//@ requires(***Output:** 9999*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** num = 9999*);
//@ requires(***Output:** 9999*);
//@ requires(***Explanation:** It is better not to apply any change.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= num <= 10^4`*);
//@ requires(**   `num` consists of only digits 6 and 9.*);
//@ requires(**);
//@ requires(*Method signature: public int maximum69Number(int num)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The integer parameter `num` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The integer parameter `num` consists of only digits 6 and 9.*);
//@ ensures(*If the integer parameter `numRows` is equal to 3 and the string parameter `s` is equal to "PAYPALISHIRING", the string result is equal to "PAHNAPLSIIGYIR".*);
//@ ensures(*If the integer parameter `numRows` is equal to 4 and the string parameter `s` is equal to "PAYPALISHIRING", the string result is equal to "PINALSIGYAHRPI".*);
//@ ensures(*If the integer parameter `numRows` is equal to 1 and the string parameter `s` is equal to "A", the string result is equal to "A".*);
//@ ensures(*Changing the first digit results in 6669.*);
//@ ensures(*Changing the second digit results in 9969.*);
//@ ensures(*Changing the third digit results in 9699.*);
//@ ensures(*Changing the fourth digit results in 9666.*);
//@ ensures(***Explanation:** Changing the last digit 6 to 9 results in the maximum number.*);
//@ ensures(*If the integer parameter `num` is equal to 9669, the integer result is equal to 9969.*);
//@ ensures(*If the integer parameter `num` is equal to 9996, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 9999, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 9696, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 6969, the integer result is equal to 9969.*);
//@ ensures(*If the integer parameter `num` is equal to 6666, the integer result is equal to 9969.*);
//@ ensures(*If the integer parameter `num` is equal to 6999, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 6669, the integer result is equal to 9969.*);
//@ ensures(*If the integer parameter `num` is equal to 6996, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 9966, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 9699, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 9969, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 9666, the integer result is equal to 9969.*);
//@ ensures(*If the integer parameter `num` is equal to 6696, the integer result is equal to 9999.*);
//@ ensures(*If the integer parameter `num` is equal to 6966, the integer result*);
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