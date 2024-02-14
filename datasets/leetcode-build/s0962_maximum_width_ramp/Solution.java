package g0901_1000.s0962_maximum_width_ramp;

// #Medium #Array #Stack #Monotonic_Stack #2022_03_31_Time_6_ms_(90.69%)_Space_57.6_MB_(36.44%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be at least 2.*);
	//@ requires(*The elements in the input array `nums` must be non-negative integers.*);
	//@ ensures(*The return value must be an integer.*);
	//@ ensures(*The return value must be the maximum width of a ramp in the input array `nums`.*);
	//@ ensures(*If there is no ramp in the input array `nums`, the return value must be 0.*);
    public int maxWidthRamp(int[] nums) {
        int m = nums.length;
        int[] dp = new int[m];
        int minInd = 0;
        int ramp = 0;
        for (int i = 0; i < m; i++) {
            int prInd = minInd;
            while (prInd > 0 && nums[i] >= nums[dp[prInd]]) {
                prInd = dp[prInd];
            }
            dp[i] = prInd;
            if (nums[i] >= nums[prInd]) {
                ramp = Math.max(ramp, i - prInd);
            }
            minInd = nums[i] < nums[minInd] ? i : minInd;
        }
        return ramp;
    }
}