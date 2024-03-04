package g1601_1700.s1680_concatenation_of_consecutive_binary_numbers;

// #Medium #Math #Bit_Manipulation #Simulation
// #2022_04_15_Time_70_ms_(95.05%)_Space_39.2_MB_(98.02%)

public class Solution {
    private static final long MOD = 1000_000_007;
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer.*);
//@ ensures(*`n` is greater than or equal to 1 and less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer.*);
//@ ensures(*The returned value is the decimal value of the binary string formed by concatenating the binary representations of 1 to `n` in order.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int concatenatedBinary(int n) {
        // calculate the length of binary string
        int length = 0;
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & i - 1) == 0) {
                length++;
            }
            sum <<= length;
            sum += i;
            if (sum > MOD) {
                sum %= MOD;
            }
        }
        return (int) (sum % MOD);
    }
}