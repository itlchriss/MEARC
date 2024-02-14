package g0401_0500.s0462_minimum_moves_to_equal_array_elements_ii;

// #Medium #Array #Math #Sorting #2022_07_19_Time_7_ms_(31.31%)_Space_46.7_MB_(6.63%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `nums` is less than or equal to 10^5.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^9 to 10^9.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output represents the minimum number of moves required to make all elements in the input array `nums` equal.*);
	//@ ensures(*Each move can increment or decrement an element of the array by 1.*);
	//@ ensures(*The answer will fit in a 32-bit integer.*);
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = (nums.length - 1) / 2;
        int ops = 0;
        for (int num : nums) {
            if (num != nums[median]) {
                ops += Math.abs(nums[median] - num);
            }
        }
        return ops;
    }
}