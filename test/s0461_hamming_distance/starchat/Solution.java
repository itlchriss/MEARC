package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
//@ requires(*The integer parameters `x` and `y` are less than or equal to 2^31 - 1 and are greater than or equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the number of positions at which the corresponding bits of `x` and `y` are different.*);
//@ ensures(*If the integer parameters `x` and `y` are equal to 1 and 4 respectively, the integer result is equal to 2.*);
//@ ensures(*If the integer parameters `x` and `y` are equal to 3 and 1 respectively, the integer result is equal to 1.*);
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}