package g2801_2900.s2874_maximum_value_of_an_ordered_triplet_ii;

// #Medium #Array #2023_12_22_Time_2_ms_(99.67%)_Space_57.5_MB_(41.20%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _**the maximum value over all triplets of indices**_ `(i, j, k)` _such that_ `i < j < k`_._ If all such triplets have a negative value, return `0`.
Return _**the maximum value over all triplets of indices**_ `(i, j, k)` _such that_ `i < j < k`_._ If all such triplets have a negative value, return `0`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public long maximumTripletValue(int[] nums) {
        int[] diff = new int[nums.length];
        int tempMax = nums[0];
        for (int i = 1; i < diff.length - 1; i++) {
            diff[i] = tempMax - nums[i];
            tempMax = Math.max(tempMax, nums[i]);
        }
        long max = Long.MIN_VALUE;
        tempMax = nums[nums.length - 1];
        for (int i = nums.length - 2; i > 0; i--) {
            max = Math.max(max, (long) tempMax * diff[i]);
            tempMax = Math.max(tempMax, nums[i]);
        }
        return Math.max(max, 0);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
