package g0101_0200.s0164_maximum_gap;

// #Hard #Array #Sorting #Bucket_Sort #Radix_Sort
// #2022_06_25_Time_48_ms_(53.59%)_Space_84.1_MB_(20.66%)

import java.util.Arrays;

public class Solution {
//@ ensures (\forall int i; 0 <= i && i < nums.length - 1; \result >= nums[i + 1] - nums[i]);
//@ requires nums != null && nums.length >= 2;
//@ requires (\forall int i; 0 <= i && i < nums.length - 1; nums[i] <= nums[i + 1]);
//@ ensures (\forall int i, j; 0 <= i && i < j && j < nums.length; nums[i] <= nums[j]);
//@ ensures \result >= 0;
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int ret = Integer.MIN_VALUE;
        Arrays.sort(nums);
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i + 1] % nums[i]) > ret) {
                ret = (nums[i + 1] - nums[i]);
            }
        }
        return ret;
    }
}
