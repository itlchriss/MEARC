package g0201_0300.s0260_single_number_iii;

// #Medium #Array #Bit_Manipulation #2022_07_05_Time_1_ms_(100.00%)_Space_44.5_MB_(77.86%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array result contains exactly two elements that appear only once in the integer array parameter `nums`.*);
//@ ensures(*The algorithm must run in linear runtime complexity.*);
//@ ensures(*The algorithm must use only constant extra space.*);
    public int[] singleNumber(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            // will give xor of required nos
            xorSum ^= num;
        }
        // find rightmost bit which is set
        int rightBit = xorSum & -xorSum;
        int a = 0;
        for (int num : nums) {
            // xor only those number whose rightmost bit is set
            if ((num & rightBit) != 0) {
                a ^= num;
            }
        }
        return new int[] {a, a ^ xorSum};
    }
}