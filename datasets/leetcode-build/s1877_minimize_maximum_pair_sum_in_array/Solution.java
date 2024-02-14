package g1801_1900.s1877_minimize_maximum_pair_sum_in_array;

// #Medium #Array #Sorting #Greedy #Two_Pointers
// #2022_05_10_Time_62_ms_(82.87%)_Space_50.5_MB_(99.71%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is even.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ ensures(*The output is an integer representing the minimized maximum pair sum.*);
	//@ ensures(*The output is the maximum sum of pairs formed from the elements in the input array `nums`.*);
	//@ ensures(*Each element in the input array `nums` is paired with exactly one other element.*);
	//@ ensures(*The maximum pair sum is minimized, meaning that there is no other possible pairing of elements that would result in a smaller maximum pair sum.*);
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int min = Integer.MIN_VALUE;
        while (start < end) {
            min = Math.max(min, nums[start] + nums[end]);
            --end;
            ++start;
        }
        return min;
    }
}