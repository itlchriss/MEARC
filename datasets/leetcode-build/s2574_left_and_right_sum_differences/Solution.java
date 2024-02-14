package g2501_2600.s2574_left_and_right_sum_differences;

// #Easy #Array #Prefix_Sum #2023_08_21_Time_2_ms_(74.57%)_Space_43.7_MB_(76.84%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of 1 to 10^5.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of the output array `answer` is equal to the length of the input array `nums`.*);
	//@ ensures(*The elements in the output array `answer` are integers.*);
	//@ ensures(*The elements in the output array `answer` are the absolute differences between the corresponding elements in the arrays `leftSum` and `rightSum`.*);
	//@ ensures(*The elements in the output array `answer` are calculated correctly according to the given formula `answer[i] = |leftSum[i] - rightSum[i]|`.*);
	//@ ensures(*The elements in the output array `answer` are calculated correctly for all indices `i` in the range from 0 to the length of the input array `nums` minus 1.*);
	//@ ensures(*The elements in the output array `answer` are calculated correctly when there are no elements to the left or right of the index `i` in the input array `nums`. In this case, the corresponding element in the output array `answer` should be equal to the absolute value of the sum of the elements to the left or right of the index `i`, respectively.*);
    public int[] leftRightDifference(int[] nums) {
        int ls = 0;
        int rs = 0;
        for (int i : nums) {
            rs += i;
        }
        for (int i = 0; i < nums.length; ++i) {
            ls += nums[i];
            rs -= nums[i];
            nums[i] = Math.abs(rs - (ls - nums[i]));
        }
        return nums;
    }
}