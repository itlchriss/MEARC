package g2101_2200.s2172_maximum_and_sum_of_array;

// #Hard #Array #Dynamic_Programming #Bit_Manipulation #Bitmask
// #2022_06_02_Time_21_ms_(87.50%)_Space_41.7_MB_(98.44%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the maximum possible **AND sum** of_ `nums` _given_ `numSlots` _slots._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
