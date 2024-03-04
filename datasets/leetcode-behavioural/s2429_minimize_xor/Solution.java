package g2401_2500.s2429_minimize_xor;

// #Medium #Greedy #Bit_Manipulation #2022_12_07_Time_0_ms_(100.00%)_Space_39.6_MB_(87.47%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `num1` is a positive integer.*);
//@ ensures(*The input `num2` is a positive integer.*);
//@ ensures(*The binary representation of `num1` has the same number of set bits as the binary representation of `num2`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output `x` is a positive integer.*);
//@ ensures(*The binary representation of `x` has the same number of set bits as the binary representation of `num2`.*);
//@ ensures(*The value of `x XOR num1` is minimal.*);
    public int minimizeXor(int num1, int num2) {
        int bits = Integer.bitCount(num2);
        int result = 0;
        for (int i = 30; i >= 0 && bits > 0; --i) {
            if ((1 << i & num1) != 0) {
                --bits;
                result = result ^ (1 << i);
            }
        }
        for (int i = 0; i <= 30 && bits > 0; ++i) {
            if ((1 << i & num1) == 0) {
                --bits;
                result = result ^ (1 << i);
            }
        }
        return result;
    }
}