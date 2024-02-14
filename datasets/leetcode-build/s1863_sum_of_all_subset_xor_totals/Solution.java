package g1801_1900.s1863_sum_of_all_subset_xor_totals;

// #Easy #Array #Math #Bit_Manipulation #Backtracking #Combinatorics
// #2022_06_21_Time_0_ms_(100.00%)_Space_39.3_MB_(96.98%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is an integer.*);
	//@ requires(*Each element in the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 20.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is the sum of all XOR totals for every subset of the input array `nums`.*);
    public int subsetXORSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return subsetXORSum(nums, 0, 0);
    }

    private int subsetXORSum(int[] nums, int currIndex, int res) {
        if (currIndex == nums.length) {
            return res;
        }
        int sum1 = subsetXORSum(nums, currIndex + 1, nums[currIndex] ^ res);
        int sum2 = subsetXORSum(nums, currIndex + 1, res);
        return sum1 + sum2;
    }
}