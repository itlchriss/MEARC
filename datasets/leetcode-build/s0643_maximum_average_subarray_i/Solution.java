package g0601_0700.s0643_maximum_average_subarray_i;

// #Easy #Array #Sliding_Window #2022_03_21_Time_5_ms_(74.81%)_Space_58.3_MB_(84.86%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to `k`.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ requires(*The value of `k` is less than or equal to the length of the input array `nums`.*);
	//@ requires(*The values in the input array `nums` are within the range of -10^4 to 10^4.*);
	//@ ensures(*The returned value is a double representing the maximum average value of a contiguous subarray of length `k` in the input array `nums`.*);
	//@ ensures(*The returned value has a calculation error less than 10^-5.*);
    public double findMaxAverage(int[] nums, int k) {
        double windowSum = 0;
        int windowStart = 0;
        double max = Integer.MIN_VALUE;
        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            windowSum += nums[windowEnd];
            if (windowEnd >= k - 1) {
                double candidate = windowSum / k;
                max = Math.max(candidate, max);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }
        return max;
    }
}