package g0401_0500.s0476_number_complement;

// #Easy #Bit_Manipulation #2022_07_20_Time_0_ms_(100.00%)_Space_40.7_MB_(65.79%)

public class Solution {
//@ requires(*The integer parameter `num` is greater than or equal to 1 and is less than 2^31.*);
//@ ensures(*The integer result is the complement of the integer parameter `num`.*);
//@ ensures(*The binary representation of the integer result is obtained by flipping all `0`'s to `1`'s and all `1`'s to `0`'s in the binary representation of the integer parameter `num`.*);
    public int findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
}