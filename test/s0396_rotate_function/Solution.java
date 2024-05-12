package g0301_0400.s0396_rotate_function;

// #Medium #Array #Dynamic_Programming #Math #2022_07_15_Time_4_ms_(81.33%)_Space_86_MB_(54.94%)

public class Solution {
//@ requires(*The integer array parameter `nums` must not be null.*);
//@ requires(*The integer array parameter `nums` length is equal to the integer parameter `n`.*);
//@ requires(*All values in the integer array parameter `nums` are greater than or equal to -100 and are less than or equal to 100.*);
//@ ensures(*The integer result is the maximum value of the rotation function `F(0), F(1), ..., F(n-1)`.*);
//@ ensures(*The integer result is less than or equal to the maximum value of a 32-bit integer.*);
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
        for (int i = len - 1; i >= 1; i--) {
            f = f + allSum - len * nums[i];
            max = Math.max(f, max);
        }
        return max;
    }
}