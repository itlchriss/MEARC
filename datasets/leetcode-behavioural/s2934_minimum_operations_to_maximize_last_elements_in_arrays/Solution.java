package g2901_3000.s2934_minimum_operations_to_maximize_last_elements_in_arrays;

// #Medium #Array #Greedy #2024_01_02_Time_1_ms_(100.00%)_Space_45.2_MB_(5.34%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums1` and `nums2` must have the same length `n`.*);
//@ ensures(*The length `n` of the input arrays must be at least 1 and at most 1000.*);
//@ ensures(*Each element in `nums1` and `nums2` must be an integer between 1 and 10^9 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations needed to satisfy the conditions.*);
//@ ensures(*If it is impossible to satisfy both conditions, the method returns -1.*);
//@ ensures(*After the operations, `nums1[n - 1]` is equal to the maximum value among all elements of `nums1`.*);
//@ ensures(*After the operations, `nums2[n - 1]` is equal to the maximum value among all elements of `nums2`.*);
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int max1;
        int max2;
        int count1 = 0;
        int count2 = 0;
        max1 = Math.min(nums1[n - 1], nums2[n - 1]);
        max2 = Math.max(nums1[n - 1], nums2[n - 1]);
        for (int i = 0; i < n; i++) {
            int min = Math.min(nums1[i], nums2[i]);
            int max = Math.max(nums1[i], nums2[i]);
            if (max > max2 || min > max1) {
                return -1;
            }
            if (max == nums1[i] && max != min && max > max1) {
                count1++;
            }
            if (max == nums2[i] && max != min && max > max1) {
                count2++;
            }
        }
        return Math.min(count1, count2);
    }
}