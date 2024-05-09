package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
//@ requires(*The integer parameter `x` is greater than or equal to 0 and is less than or equal to 2^31 - 1.*);
//@ requires(*The integer parameter `y` is greater than or equal to 0 and is less than or equal to 2^31 - 1.*);
//@ ensures(*The integer result is the number of positions at which the corresponding bits of the integer parameters `x` and `y` are different.*);
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}