package g2101_2200.s2119_a_number_after_a_double_reversal;

// #Easy #Math #2022_06_01_Time_0_ms_(100.00%)_Space_40.4_MB_(67.63%)

public class Solution {
	//@ requires(*The input `num` is a non-negative integer.*);
	//@ requires(*The input `num` is less than or equal to 10^*);
	//@ ensures(*The method returns a boolean value indicating whether the reversed number `reversed2` is equal to the original number `num`.*);
    public boolean isSameAfterReversals(int num) {
        if (num == 0) {
            return true;
        }
        return num % 10 != 0;
    }
}