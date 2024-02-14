package g0301_0400.s0342_power_of_four;

// #Easy #Math #Bit_Manipulation #Recursion #2022_07_10_Time_1_ms_(100.00%)_Space_41.2_MB_(55.90%)

public class Solution {
	//@ requires(*The input `n` is an integer.*);
	//@ ensures(*The method returns `true` if `n` is a power of four.*);
	//@ ensures(*The method returns `false` if `n` is not a power of four.*);
    public boolean isPowerOfFour(int n) {
        while (n >= 4) {
            if (n % 4 != 0) {
                return false;
            }
            n = n / 4;
        }
        return n == 1;
    }
}