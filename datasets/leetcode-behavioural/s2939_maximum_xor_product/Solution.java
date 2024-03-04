package g2901_3000.s2939_maximum_xor_product;

// #Medium #Math #Greedy #Bit_Manipulation #2024_01_03_Time_1_ms_(100.00%)_Space_41.4_MB_(14.19%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `a` and `b` must be non-negative and less than 2^50.*);
//@ ensures(*The input integer `n` must be non-negative and less than or equal to 50.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the maximum value of `(a XOR x) * (b XOR x)` for all `x` where `0 <= x < 2^n`.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int maximumXorProduct(long a, long b, int n) {
        for (int i = n - 1; i >= 0; i--) {
            long mask = (1L << i);
            boolean bitA = (a & mask) != 0;
            boolean bitB = (b & mask) != 0;
            if (bitA) {
                if (a > b) {
                    a ^= mask;
                    b |= mask;
                }
            } else if (bitB) {
                if (b > a) {
                    b ^= mask;
                    a |= mask;
                }
            } else {
                b |= mask;
                a |= mask;
            }
        }
        int mod = 1000000007;
        a = a % mod;
        b = b % mod;
        return (int) ((a * b) % mod);
    }
}