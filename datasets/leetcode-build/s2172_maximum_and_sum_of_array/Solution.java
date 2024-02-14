package g2101_2200.s2172_maximum_and_sum_of_array;

// #Hard #Array #Dynamic_Programming #Bit_Manipulation #Bitmask
// #2022_06_02_Time_21_ms_(87.50%)_Space_41.7_MB_(98.44%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be equal to `n`.*);
	//@ requires(*The input integer `numSlots` must be greater than or equal to 1.*);
	//@ requires(*The input integer `numSlots` must be less than or equal to 9.*);
	//@ requires(*The input integer `n` must be greater than or equal to 1.*);
	//@ requires(*The input integer `n` must be less than or equal to `2 * numSlots`.*);
	//@ requires(*Each element in the input array `nums` must be greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` must be less than or equal to 15.*);
	//@ ensures(*The method must return an integer value.*);
	//@ ensures(*The returned integer value must represent the maximum possible AND sum of the input array `nums` given the number of slots `numSlots`.*);
    public int maximumANDSum(int[] nums, int numSlots) {
        int mask = (int) Math.pow(3, numSlots) - 1;
        int[] memo = new int[mask + 1];
        return dp(nums.length - 1, mask, numSlots, memo, nums);
    }

    private int dp(int i, int mask, int numSlots, int[] memo, int[] ints) {
        if (memo[mask] > 0) {
            return memo[mask];
        }
        if (i < 0) {
            return 0;
        }
        for (int slot = 1, bit = 1; slot <= numSlots; ++slot, bit *= 3) {
            if (mask / bit % 3 > 0) {
                memo[mask] =
                        Math.max(
                                memo[mask],
                                (ints[i] & slot) + dp(i - 1, mask - bit, numSlots, memo, ints));
            }
        }
        return memo[mask];
    }
}