package g0101_0200.s0137_single_number_ii;

// #Medium #Array #Bit_Manipulation #2022_06_24_Time_0_ms_(100.00%)_Space_42.1_MB_(84.59%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*Each element in the input array `nums` appears exactly three times except for one element which appears once.*);
	//@ ensures(*The method returns an integer, which is the single element that appears exactly once in the input array `nums`.*);
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