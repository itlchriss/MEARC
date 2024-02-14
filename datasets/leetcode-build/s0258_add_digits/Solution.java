package g0201_0300.s0258_add_digits;

// #Easy #Math #Simulation #Number_Theory #2022_07_05_Time_1_ms_(100.00%)_Space_39.3_MB_(98.44%)

public class Solution {
	//@ requires(*The input `num` must be a non-negative integer.*);
	//@ ensures(*The output is a single-digit integer.*);
	//@ ensures(*The sum of all the digits in the input `num` is equal to the output.*);
	//@ ensures(*The output is obtained by repeatedly adding the digits of the input `num` until a single-digit number is obtained.*);
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }
}