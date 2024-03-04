package g1001_1100.s1031_maximum_sum_of_two_non_overlapping_subarrays;

// #Medium #Array #Dynamic_Programming #Sliding_Window
// #2022_02_27_Time_3_ms_(46.04%)_Space_43.2_MB_(12.23%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of `nums` must be greater than or equal to `firstLen + secondLen`.*);
//@ ensures(*The values in `nums` must be non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum sum of elements in two non-overlapping subarrays.*);
//@ ensures(*The two subarrays must have lengths `firstLen` and `secondLen`.*);
//@ ensures(*The two subarrays must not overlap.*);
//@ ensures(*The subarray with length `firstLen` could occur before or after the subarray with length `secondLen`.*);
    public int maxSumTwoNoOverlap(int[] nums, int f, int s) {
        int sum = 0;
        int n = nums.length;
        int[] pref = new int[n];
        int[] suff = new int[n];
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i < f - 1) {
                continue;
            }
            pref[i] = Math.max(i > 0 ? pref[i - 1] : 0, sum);
            sum -= nums[i + 1 - f];
        }
        sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += nums[i];
            if (i > n - f) {
                continue;
            }
            suff[i] = Math.max(i < n - 1 ? suff[i + 1] : 0, sum);
            sum -= nums[i + f - 1];
        }
        sum = 0;
        for (int i = 0; i < s - 1; i++) {
            sum += nums[i];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = s - 1; i < n; i++) {
            sum += nums[i];
            if (i >= s) {
                ans = Math.max(ans, pref[i - s] + sum);
            }
            if (i < n - 1) {
                ans = Math.max(ans, suff[i + 1] + sum);
            }
            sum -= nums[i + 1 - s];
        }
        return ans;
    }
}