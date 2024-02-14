package g0601_0700.s0693_binary_number_with_alternating_bits;

// #Easy #Bit_Manipulation #2022_03_22_Time_0_ms_(100.00%)_Space_41.3_MB_(21.55%)

public class Solution {
	//@ requires(*1. The input integer n must be a positive integer.*);
	//@ requires(*2. The input integer n must be within the range of 1 to 2^31 - 1.*);
	//@ ensures(*1. The method should return a boolean value indicating whether the binary representation of n has alternating bits.*);
	//@ ensures(*2. If the binary representation of n has alternating bits, the method should return true.*);
	//@ ensures(*3. If the binary representation of n does not have alternating bits, the method should return false.*);
    public boolean hasAlternatingBits(int n) {
        int prev = -1;
        while (n != 0) {
            int v = n & 1;
            n = n >> 1;
            if (prev == v) {
                return false;
            }
            prev = v;
        }
        return true;
    }
}