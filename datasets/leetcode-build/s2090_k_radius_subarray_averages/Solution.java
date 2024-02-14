package g2001_2100.s2090_k_radius_subarray_averages;

// #Medium #Array #Sliding_Window #2022_05_23_Time_12_ms_(83.19%)_Space_166.5_MB_(16.59%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` has a length `n` greater than or equal to - The input integer `k` is greater than or equal to*);
	//@ ensures(*The output array `avgs` is not null.*);
	//@ ensures(*The output array `avgs` has a length `n`.*);
	//@ ensures(*For each index `i` in the range [0, n-1], `avgs[i]` is the k-radius average for the subarray centered at index `i`.*);
	//@ ensures(*If there are less than `k` elements before or after the index `i`, then `avgs[i]` is -- The k-radius average is calculated using integer division, which truncates towards zero.*);
    public int[] getAverages(int[] nums, int k) {
        // initialize result array with -1
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        if (nums.length <= (k * 2)) {
            // return if not enough elements
            return res;
        }
        long sum = 0;
        long range = 2 * k + 1L;
        // take sum of all elements from 0 to k*2 index
        for (int i = 0; i <= 2 * k; ++i) {
            sum += nums[i];
        }
        // update first valid avg
        res[k] = (int) (sum / range);
        // update other valid averages using sliding window
        for (int i = k + 1; i < nums.length - k; ++i) {
            sum = sum - nums[i - k - 1] + nums[i + k];
            res[i] = (int) (sum / range);
        }
        return res;
    }
}