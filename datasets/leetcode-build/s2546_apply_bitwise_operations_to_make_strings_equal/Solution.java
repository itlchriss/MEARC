package g2501_2600.s2546_apply_bitwise_operations_to_make_strings_equal;

// #Medium #String #Bit_Manipulation #2023_08_15_Time_0_ms_(100.00%)_Space_44.3_MB_(87.14%)

public class Solution {
	//@ requires(*The length of `s` and `target` should be the same.*);
	//@ requires(*`s` and `target` should consist of only the digits `0` and `1`.*);
	//@ ensures(*The method should return `true` if it is possible to make `s` equal to `target` using the given operation, otherwise it should return `false`.*);
    public boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1");
    }
}