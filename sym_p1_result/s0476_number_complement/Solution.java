package g0401_0500.s0476_number_complement;

// #Easy #Bit_Manipulation #2022_07_20_Time_0_ms_(100.00%)_Space_40.7_MB_(65.79%)

public class Solution {
//@ requires(*The complement of an integer is the integer you get when you flip all the `0`'s to `1`'s and all the `1`'s to `0`'s in its binary representation.*);
//@ requires(*For example, The integer `5` is `"101"` in binary and its complement is `"010"` which is the integer `2`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: num = 5*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010.*);
//@ requires(*So you need to output 2.*);
//@ requires(*Example 2:*);
//@ requires(*Input: num = 1*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0.*);
//@ requires(*So you need to output 0.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= num < 2<sup>31</sup></code>*);
//@ requires(*Note: This question is the same as 1009: [https://leetcode.com/problems/complement-of-base-10-integer/](https://leetcode.com/problems/complement-of-base-10-integer/)*);
//@ ensures(*Given an integer param_num, the result is its complement.*);
    public int findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
}