package g0201_0300.s0260_single_number_iii;

// #Medium #Array #Bit_Manipulation #2022_07_05_Time_1_ms_(100.00%)_Space_44.5_MB_(77.86%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 2.*);
	//@ requires(*Each integer in the input array `nums` appears exactly twice.*);
	//@ requires(*Only two integers in the input array `nums` appear once.*);
	//@ ensures(*The output array contains exactly two elements.*);
	//@ ensures(*The two elements in the output array are the two integers that appear only once in the input array `nums`.*);
	//@ ensures(*The order of the elements in the output array does not matter.*);
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