package g0501_0600.s0504_base_7;

// #Easy #Math #2022_07_24_Time_0_ms_(100.00%)_Space_39.4_MB_(98.67%)

public class Solution {
	//@ requires(*The input `num` is an integer.*);
	//@ requires(*The value of `num` is within the range of -10^7 to 10^7.*);
	//@ ensures(*The method returns a string representing the base 7 representation of the input `num`.*);
	//@ ensures(*If `num` is positive, the returned string starts with a digit other than zero.*);
	//@ ensures(*If `num` is negative, the returned string starts with a negative sign followed by a digit other than zero.*);
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}