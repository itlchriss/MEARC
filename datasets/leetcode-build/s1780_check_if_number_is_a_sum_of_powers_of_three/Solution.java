package g1701_1800.s1780_check_if_number_is_a_sum_of_powers_of_three;

// #Medium #Math #2022_07_14_Time_0_ms_(100.00%)_Space_39_MB_(98.65%)

public class Solution {
	//@ requires(*The input integer `n` is greater than or equal to 1.*);
	//@ requires(*The input integer `n` is less than or equal to 10^7.*);
	//@ ensures(*The method returns `true` if it is possible to represent `n` as the sum of distinct powers of three.*);
	//@ ensures(*The method returns `false` if it is not possible to represent `n` as the sum of distinct powers of three.*);
    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            int rem = n % 3;
            n /= 3;
            if (rem == 2 || n == 2) {
                return false;
            }
        }
        return true;
    }
}