package g1401_1500.s1486_xor_operation_in_an_array;

// #Easy #Math #Bit_Manipulation #2022_04_05_Time_0_ms_(100.00%)_Space_40.7_MB_(58.44%)

public class Solution {
	//@ requires(*1. The input `n` must be a positive integer greater than or equal to 1.*);
	//@ requires(*2. The input `start` must be a non-negative integer.*);
	//@ requires(*3. The length of the array `nums` must be equal to `n`.*);
	//@ ensures(*1. The return value must be an integer.*);
	//@ ensures(*2. The return value must be the bitwise XOR of all elements in the array `nums`.*);
    public int xorOperation(int n, int start) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = start + 2 * i;
        }
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}