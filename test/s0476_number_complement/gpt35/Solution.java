package g0401_0500.s0476_number_complement;

// #Easy #Bit_Manipulation #2022_07_20_Time_0_ms_(100.00%)_Space_40.7_MB_(65.79%)

public class Solution {
//@ requires(*The integer parameter `num` is greater than or equal to 1 and is less than 2^31.*);
//@ ensures(*The integer result is the complement of the binary representation of the integer parameter `num`.*);
//@ ensures(*If the integer parameter `num` is equal to 5, the integer result is equal to 2.*);
//@ ensures(*If the integer parameter `num` is equal to 1, the integer result is equal to 0.*);
    public int findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
}