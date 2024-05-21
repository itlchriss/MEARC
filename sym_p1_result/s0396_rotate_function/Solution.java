package g0301_0400.s0396_rotate_function;

// #Medium #Array #Dynamic_Programming #Math #2022_07_15_Time_4_ms_(81.33%)_Space_86_MB_(54.94%)

public class Solution {
//@ requires(*You are given an integer array param_nums of length `n`.*);
//@ requires(*Assume <code>arr<sub>k</sub></code> to be an array obtained by rotating param_nums by `k` positions clock-wise.*);
//@ requires(*We define the rotation function `F` on param_nums as follow:*);
//@ requires(*<code>F(k) = 0  arr<sub>k</sub>[0] + 1  arr<sub>k</sub>[1] + ... + (n - 1)  arr<sub>k</sub>[n - 1].</code>*);
//@ requires(*Return the maximum value of `F(0), F(1), ..., F(n-1)`.*);
//@ requires(*The test cases are generated so that the answer fits in a 32-bit integer.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [4,3,2,6]*);
//@ requires(*Output: 26*);
//@ requires(*Explanation:*);
//@ requires(*F(0) = (0  4) + (1  3) + (2  2) + (3  6) = 0 + 3 + 4 + 18 = 25*);
//@ requires(*F(1) = (0  6) + (1  4) + (2  3) + (3  2) = 0 + 4 + 6 + 6 = 16*);
//@ requires(*F(2) = (0  2) + (1  6) + (2  4) + (3  3) = 0 + 6 + 8 + 9 = 23*);
//@ requires(*F(3) = (0  3) + (1  2) + (2  6) + (3  4) = 0 + 2 + 12 + 12 = 26*);
//@ requires(*So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [100]*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*`n == nums.length`*);
//@ requires(*<code>1 <= n <= 10<sup>5</sup></code>*);
//@ requires(*`-100 <= nums[i] <= 100`*);
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