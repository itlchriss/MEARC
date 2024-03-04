package g0101_0200.s0137_single_number_ii;

// #Medium #Array #Bit_Manipulation #2022_06_24_Time_0_ms_(100.00%)_Space_42.1_MB_(84.59%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the single element that appears exactly once in the integer array parameter `nums`.*);
//@ ensures(*The integer result must be found with linear runtime complexity.*);
//@ ensures(*The solution must use only constant extra space.*);
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & (~twos);
            twos = (twos ^ num) & (~ones);
        }
        return ones;
    }
}