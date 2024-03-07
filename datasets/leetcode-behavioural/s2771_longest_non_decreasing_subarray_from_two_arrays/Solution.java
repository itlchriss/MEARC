package g2701_2800.s2771_longest_non_decreasing_subarray_from_two_arrays;

// #Medium #Array #Dynamic_Programming #2023_09_21_Time_4_ms_(97.62%)_Space_56.9_MB_(78.75%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums1` and `nums2` are not null.*);
//@ ensures(*The lengths of `nums1` and `nums2` are equal.*);
//@ ensures(*The length of `nums1` and `nums2` is at least 1.*);
//@ ensures(*The elements in `nums1` and `nums2` are integers.*);
//@ ensures(*The elements in `nums1` and `nums2` are between 1 and 10^9 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer representing the length of the longest non-decreasing subarray in `nums3`.*);
//@ ensures(*The length of `nums3` is equal to the length of `nums1` and `nums2`.*);
//@ ensures(*The elements in `nums3` are either from `nums1` or `nums2`.*);
//@ ensures(*The subarray in `nums3` starting from index 0 and ending at index `k` (0 <= k < n) is non-decreasing.*);
//@ ensures(*The length of the longest non-decreasing subarray in `nums3` is maximized.*);
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int res = 1;
        int dp1 = 1;
        int dp2 = 1;
        int n = nums1.length;
        int t11;
        int t12;
        int t21;
        int t22;
        for (int i = 1; i < n; i++) {
            t11 = (nums1[i - 1] <= nums1[i]) ? dp1 + 1 : 1;
            t12 = (nums1[i - 1] <= nums2[i]) ? dp1 + 1 : 1;
            t21 = (nums2[i - 1] <= nums1[i]) ? dp2 + 1 : 1;
            t22 = (nums2[i - 1] <= nums2[i]) ? dp2 + 1 : 1;
            dp1 = Math.max(t11, t21);
            dp2 = Math.max(t12, t22);
            res = Math.max(res, Math.max(dp1, dp2));
        }
        return res;
    }
}