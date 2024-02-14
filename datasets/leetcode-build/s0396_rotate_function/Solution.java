package g0301_0400.s0396_rotate_function;

// #Medium #Array #Dynamic_Programming #Math #2022_07_15_Time_4_ms_(81.33%)_Space_86_MB_(54.94%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than 0.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the maximum value of `F(0), F(1), ..., F(n-1)`.*);
	//@ ensures(*The returned value fits in a 32-bit integer.*);
    public int maxRotateFunction(int[] nums) {
        int allSum = 0;
        int len = nums.length;
        int f = 0;
        for (int i = 0; i < len; i++) {
            f += i * nums[i];
            allSum += nums[i];
        }
        int max = f;
        for (int i = len - 1; i >= 1; i--) {
            f = f + allSum - len * nums[i];
            max = Math.max(f, max);
        }
        return max;
    }
}