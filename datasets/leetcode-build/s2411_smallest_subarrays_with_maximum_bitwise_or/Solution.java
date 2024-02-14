package g2401_2500.s2411_smallest_subarrays_with_maximum_bitwise_or;

// #Medium #Array #Binary_Search #Bit_Manipulation #Sliding_Window
// #2022_10_24_Time_31_ms_(95.05%)_Space_52.3_MB_(94.41%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than 0.*);
	//@ requires(*The elements in the input array `nums` are non-negative integers.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of the output array `answer` is equal to the length of the input array `nums`.*);
	//@ ensures(*Each element in the output array `answer` is a non-negative integer.*);
	//@ ensures(*The subarray starting at index `i` and ending at index `i + answer[i] - 1` has the maximum possible bitwise OR among all subarrays starting at index `i`.*);
    public int[] smallestSubarrays(int[] nums) {
        int[] res = new int[nums.length];
        int[] bitPosition = new int[30];
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = 1;
            for (int j = 0; j < 30; j++) {
                if ((nums[i] & (1 << j)) > 0) {
                    bitPosition[j] = i;
                }
                res[i] = Math.max(res[i], bitPosition[j] - i + 1);
            }
        }
        return res;
    }
}