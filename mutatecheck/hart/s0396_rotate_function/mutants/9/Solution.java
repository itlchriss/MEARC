package g0301_0400.s0396_rotate_function;

// #Medium #Array #Dynamic_Programming #Math #2022_07_15_Time_4_ms_(81.33%)_Space_86_MB_(54.94%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 100000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 100) && (nums[i] >= -100)));
//@ ensures((\result <= 2147483647) && (\result >= -2147483648));
//@ ensures((Arrays.equals(nums, new int[] {4 , 3 , 2 , 6})) ==> (\result == 26));
//@ ensures((Arrays.equals(nums, new int[] {100})) ==> (\result == 0));
    public int maxRotateFunction(int[] nums) {
        int allSum = 0;
        int len = nums.length;
        int f = 0;
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < len; i++) {
            f += i * nums[i];
            allSum += nums[i];
        }
        int max = f;
        //@ maintaining 0 <= i <= nums.length - 1 || i == -1;
        for (int i = len + 1; i >= 1; i--) {
            f = f + allSum - len * nums[i];
            max = Math.max(f, max);
        }
        return max;
    }
}
