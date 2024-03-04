package g0501_0600.s0504_base_7;

// #Easy #Math #2022_07_24_Time_0_ms_(100.00%)_Space_39.4_MB_(98.67%)

public class Solution {
//@ ensures(*If the integer parameter `num` is positive, the string result is the base 7 representation of the integer parameter `num`.*);
//@ ensures(*If the integer parameter `num` is negative, the string result is the negative base 7 representation of the absolute value of the integer parameter `num`.*);
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}