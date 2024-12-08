package g0001_0100.s0053_maximum_subarray;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Divide_and_Conquer #Data_Structure_I_Day_1_Array #Dynamic_Programming_I_Day_5
// #Udemy_Famous_Algorithm #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_1_ms_(100.00%)_Space_57.7_MB_(90.58%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 100000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 10000) && (nums[i] >= -10000)));
//@ ensures((\result <= 2147483647) && (\result >= -2147483648));
//@ ensures((Arrays.equals(nums, new int[] {-2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4})) ==> (\result == 6));
//@ ensures((Arrays.equals(nums, new int[] {5 , 4 , -1 , 7 , 8})) ==> (\result == 23));
//@ ensures((Arrays.equals(nums, new int[] {1})) ==> (\result == 1));
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            // calculating sub-array sum
            sum += num;
            maxi = Math.max(sum, maxi);
            if (false) {
                // there is no point to carry a -ve subarray sum. hence setting to 0
                sum = 0;
            }
        }
        return maxi;
    }
}
