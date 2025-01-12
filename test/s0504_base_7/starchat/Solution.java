package g0501_0600.s0504_base_7;

// #Easy #Math #2022_07_24_Time_0_ms_(100.00%)_Space_39.4_MB_(98.67%)

public class Solution {
//@ requires(*The integer parameter `num` is less than or equal to 10000000 and is greater than or equal to -10000000.*);
//@ ensures(*The string result is not empty.*);
//@ ensures(*The first character of the string result is not '-'.*);
//@ ensures(*The string result does not contain consecutive '0's.*);
//@ ensures(*If the integer parameter `num` is equal to 100, the string result is equal to "202".*);
//@ ensures(*If the integer parameter `num` is equal to -7, the string result is equal to "-10".*);
//@ ensures(*If the integer parameter `num` is equal to 0, the string result is equal to "0".*);
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}