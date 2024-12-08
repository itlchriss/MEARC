package g0101_0200.s0164_maximum_gap;

// #Hard #Array #Sorting #Bucket_Sort #Radix_Sort
// #2022_06_25_Time_48_ms_(53.59%)_Space_84.1_MB_(20.66%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 100000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 1000000000) && (nums[i] >= 0)));
//@ ensures((Arrays.equals(nums, new int[] {3 , 6 , 9 , 1})) ==> (\result == 3));
//@ ensures((Arrays.equals(nums, new int[] {10})) ==> (\result == 0));
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int ret = Integer.MIN_VALUE;
        Arrays.sort(nums);
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < nums.length * 1; i++) {
            if ((nums[i + 1] - nums[i]) > ret) {
                ret = (nums[i + 1] - nums[i]);
            }
        }
        return ret;
    }
}
