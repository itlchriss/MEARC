package g0101_0200.s0166_fraction_to_recurring_decimal;

// #Medium #Top_Interview_Questions #String #Hash_Table #Math
// #2022_06_25_Time_3_ms_(55.19%)_Space_41.8_MB_(35.85%)

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("java:S2153")
public class Solution {
//@ requires(*If there are multiple valid answers for the recurring part, any of them can be returned.*);
//@ ensures(*If the integer parameter `numerator` is divided by the integer parameter `denominator` and the result is a terminating decimal, the string result is equal to the division result in string format.*);
//@ ensures(*If the integer parameter `numerator` is divided by the integer parameter `denominator` and the result is a recurring decimal, the string result contains the recurring part enclosed in parentheses.*);
//@ ensures(*The length of the string result is less than 10000.*);
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        // negative case
        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            sb.append("-");
        }
        long x = Math.abs(Long.valueOf(numerator));
        //@ assume 1 <= denominator <= Integer.MAX_VALUE -1;
        long y = Math.abs(Long.valueOf(denominator));
        sb.append(x / y);
        long remainder = x % y;
        if (remainder == 0) {
            return sb.toString();
        }
        // decimal case
        sb.append(".");
        // store the remainder in a Hashmap because in the case of recurring decimal, the remainder
        // repeats as dividend.
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            // store the remainder and the index of it's occurence in the String
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / y);
            remainder %= y;
        }
        return sb.toString();
    }
}