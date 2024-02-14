package g1901_2000.s1913_maximum_product_difference_between_two_pairs;

// #Easy #Array #Sorting #2022_05_24_Time_7_ms_(70.01%)_Space_42.6_MB_(82.74%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be at least 4.*);
	//@ requires(*The elements in the input array `nums` must be positive integers.*);
	//@ ensures(*The method returns an integer representing the maximum product difference between two pairs.*);
	//@ ensures(*The maximum product difference is calculated by finding four distinct indices `w`, `x`, `y`, and `z` such that the product difference between pairs `(nums[w], nums[x])` and `(nums[y], nums[z])` is maximized.*);
	//@ ensures(*The indices `w`, `x`, `y`, and `z` must be valid indices within the input array `nums`.*);
	//@ ensures(*The pairs `(nums[w], nums[x])` and `(nums[y], nums[z])` must be distinct pairs.*);
	//@ ensures(*The maximum product difference is equal to `(nums[w] * nums[x]) - (nums[y] * nums[z])`.*);
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return nums[len - 1] * nums[len - 2] - nums[0] * nums[1];
    }
}