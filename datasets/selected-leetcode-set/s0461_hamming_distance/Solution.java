package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `x` and the integer parameter `y` must be non-negative integers.*);
//@ ensures(*The integer result is the number of positions at which the corresponding bits of the integer parameter `x` and the integer parameter `y` are different.*);
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}