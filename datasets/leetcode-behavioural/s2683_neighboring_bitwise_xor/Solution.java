package g2601_2700.s2683_neighboring_bitwise_xor;

// #Medium #Array #Bit_Manipulation #2023_09_12_Time_2_ms_(100.00%)_Space_59.9_MB_(62.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `derived` is not null.*);
//@ ensures(*The length of the input array `derived` is greater than or equal to 2.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether a valid binary array `original` exists that could have formed the given `derived` array.*);
//@ ensures(*If a valid binary array `original` exists, the method returns `true`.*);
//@ ensures(*If a valid binary array `original` does not exist, the method returns `false`.*);
    public boolean doesValidArrayExist(int[] derived) {
        int xor = 0;
        for (int j : derived) {
            xor = xor ^ j;
        }
        return xor == 0;
    }
}