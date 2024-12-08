package g0601_0700.s0643_maximum_average_subarray_i;

// #Easy #Array #Sliding_Window #2022_03_21_Time_5_ms_(74.81%)_Space_58.3_MB_(84.86%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer parameter `k` must be greater than 0 and less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*The double result is the maximum average value of a contiguous subarray of length `k` within the integer array parameter `nums`.*);
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