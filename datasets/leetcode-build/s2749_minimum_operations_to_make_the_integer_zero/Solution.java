package g2701_2800.s2749_minimum_operations_to_make_the_integer_zero;

// #Medium #Bit_Manipulation #Brainteaser #2023_09_24_Time_1_ms_(91.11%)_Space_40.2_MB_(9.63%)

public class Solution {
	//@ requires(*The method takes two integer parameters `num1` and `num2`.*);
	//@ requires(*The value of `num1` is between 1 and 10^9 (inclusive).*);
	//@ requires(*The value of `num2` is between -10^9 and 10^9 (inclusive).*);
	//@ ensures(*The method returns an integer value denoting the minimum number of operations needed to make `num1` equal to 0.*);
	//@ ensures(*If it is impossible to make `num1` equal to 0, the method returns -1.*);
    public int makeTheIntegerZero(int num1, int num2) {
        for (int i = 0; i <= 60; i++) {
            long target = num1 - (long) num2 * i;
            long noOfBits = Long.bitCount(target);
            if (i >= noOfBits && i <= target) {
                return i;
            }
        }
        return -1;
    }
}