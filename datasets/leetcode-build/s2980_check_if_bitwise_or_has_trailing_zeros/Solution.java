package g2901_3000.s2980_check_if_bitwise_or_has_trailing_zeros;

// #Easy #Array #Bit_Manipulation #2024_01_18_Time_1_ms_(88.11%)_Space_44.2_MB_(64.58%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be at least 2.*);
	//@ requires(*All elements in the input array `nums` must be positive integers.*);
	//@ requires(*The maximum value of any element in the input array `nums` must be 100.*);
	//@ ensures(*The method should return a boolean value indicating whether it is possible to select two or more elements whose bitwise OR has at least one trailing zero in its binary representation.*);
    public boolean hasTrailingZeros(int[] nums) {
        int hasTrailingZero = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                hasTrailingZero++;
                if (hasTrailingZero > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}