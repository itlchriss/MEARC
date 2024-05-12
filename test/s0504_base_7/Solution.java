package g0501_0600.s0504_base_7;

// #Easy #Math #2022_07_24_Time_0_ms_(100.00%)_Space_39.4_MB_(98.67%)

public class Solution {
//@ requires(*The integer parameter `num` is greater than or equal to -10000000 and is less than or equal to 10000000.*);
//@ ensures(*The string result is the base 7 representation of the integer parameter `num`.*);
//@ ensures(*The string result may contain a negative sign if the integer parameter `num` is negative.*);
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}