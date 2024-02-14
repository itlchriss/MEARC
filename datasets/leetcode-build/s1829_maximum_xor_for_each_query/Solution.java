package g1801_1900.s1829_maximum_xor_for_each_query;

// #Medium #Array #Bit_Manipulation #Prefix_Sum
// #2022_07_14_Time_5_ms_(61.24%)_Space_149.5_MB_(14.34%)

public class Solution {
	//@ requires(*The input array `nums` is sorted in ascending order.*);
	//@ requires(*The length of the input array `nums` is `n`.*);
	//@ requires(*The value of `n` is between 1 and 10^5 (inclusive).*);
	//@ requires(*The value of `maximumBit` is between 1 and 20 (inclusive).*);
	//@ requires(*Each element in the input array `nums` is a non-negative integer between 0 and 2^maximumBit - 1 (inclusive).*);
	//@ ensures(*The method returns an array `answer` of length `n`.*);
	//@ ensures(*Each element in the array `answer` is a non-negative integer between 0 and 2^maximumBit - 1 (inclusive).*);
	//@ ensures(*The first element in the array `answer` is the answer to the first query.*);
	//@ ensures(*The last element in the array `answer` is the answer to the last query.*);
	//@ ensures(*After each query, the last element in the current array `nums` is removed.*);
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int[] result = new int[nums.length];
        int val = nums[0];
        int target = (1 << maximumBit) - 1;
        for (int i = 1; i < nums.length; i++) {
            val ^= nums[i];
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = target ^ val;
            val ^= nums[nums.length - i - 1];
        }
        return result;
    }
}