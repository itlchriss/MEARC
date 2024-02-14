package g1801_1900.s1881_maximum_value_after_insertion;

// #Medium #String #Greedy #2022_05_11_Time_12_ms_(85.08%)_Space_42.8_MB_(98.31%)

public class Solution {
	//@ requires(*The input string `n` must not be null.*);
	//@ requires(*The input string `n` must have a length greater than or equal to 1.*);
	//@ requires(*The input integer `x` must be between 1 and 9 (inclusive).*);
	//@ requires(*The digits in `n` must be in the range [1, 9].*);
	//@ requires(*If `n` is a negative number, it must begin with a '-' character.*);
	//@ ensures(*The output string must represent the maximum value of `n` after inserting `x`.*);
	//@ ensures(*The output string must have the same sign as the input string `n`.*);
	//@ ensures(*The output string must have a length greater than or equal to the length of the input string `n`.*);
	//@ ensures(*The output string must have the digit `x` inserted at any valid position in the decimal representation of `n`, except to the left of the negative sign if `n` is negative.*);
    public String maxValue(String n, int x) {
        int i = 0;
        int sign = n.charAt(0) == '-' ? -1 : 1;
        for (; i < n.length(); i++) {
            if (n.charAt(i) != '-' && (sign * (n.charAt(i) - '0') < sign * x)) {
                break;
            }
        }
        return n.substring(0, i) + x + n.substring(i);
    }
}