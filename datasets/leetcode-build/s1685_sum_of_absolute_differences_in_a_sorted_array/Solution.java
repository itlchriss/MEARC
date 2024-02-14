package g1601_1700.s1685_sum_of_absolute_differences_in_a_sorted_array;

// #Medium #Array #Math #Prefix_Sum #2022_04_18_Time_4_ms_(80.17%)_Space_58.3_MB_(88.84%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is sorted in non-decreasing order.*);
	//@ requires(*The length of the input array `nums` is between 2 and 10^5.*);
	//@ requires(*Each element in the input array `nums` is between 1 and 10^4.*);
	//@ ensures(*The output array `result` is not null.*);
	//@ ensures(*The length of the output array `result` is the same as the input array `nums`.*);
	//@ ensures(*Each element in the output array `result` is the summation of absolute differences between the corresponding element in the input array `nums` and all the other elements in the array.*);
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int len = nums.length;
        int[] preSums = new int[len];
        for (int i = 1; i < len; i++) {
            preSums[i] = preSums[i - 1] + nums[i - 1];
        }
        int[] postSums = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            postSums[i] = postSums[i + 1] + nums[i + 1];
        }
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = nums[i] * i - preSums[i] + postSums[i] - nums[i] * (len - i - 1);
        }
        return result;
    }
}