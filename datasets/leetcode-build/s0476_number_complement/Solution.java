package g0401_0500.s0476_number_complement;

// #Easy #Bit_Manipulation #2022_07_20_Time_0_ms_(100.00%)_Space_40.7_MB_(65.79%)

public class Solution {
	//@ requires(*The input `num` is a non-negative integer.*);
	//@ requires(*The binary representation of `num` does not have any leading zero bits.*);
	//@ ensures(*The output is an integer representing the complement of `num`.*);
	//@ ensures(*The binary representation of the output does not have any leading zero bits.*);
    public int findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
}