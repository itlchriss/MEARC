package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
// requires public int hammingDistance(int x, int y)
//@ ensures \result == Integer.bitCount(x ^ y);
//@ ensures \result >= 0;
//@ requires x >= 0 && y >= 0 && x <= Math.pow(2, 31) - 1 && y <= Math.pow(2, 31) - 1;
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x & y);
    }
}
