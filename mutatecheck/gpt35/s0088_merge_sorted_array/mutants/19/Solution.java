package g0001_0100.s0088_merge_sorted_array;

// #Easy #Top_Interview_Questions #Array #Sorting #Two_Pointers #Data_Structure_I_Day_2_Array
// #2022_06_20_Time_0_ms_(100.00%)_Space_42.7_MB_(55.70%)

public class Solution {
//@ requires(1 <= m + n <= 200);
//@ ensures((\forall int i; 0 <= i && i < m + n; (\exists int j; 0 <= j && j < m + n; nums1[j] == \old(nums1[i]) || nums1[j] == nums2[i])));
// requires((\forall int i; 0 <= i && i < m + n; -10^9 <= nums1[i] && nums1[i] <= 10^9));
//@ ensures((\forall int k; 0 <= k && k < m + n - 1; nums1[k] <= nums1[k + 1]));
//@ requires(0 <= m && n <= 200);
//@ requires(nums1.length == m + n && nums2.length == n);
// requires((\forall int j; 0 <= j && j < n; -10^9 <= nums2[j] && nums2[j] <= 10^9));
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = nums1.length - 1;
        int p2 = n - 1;
        while (p2 >= 0) {
            if (i >= 0 && nums1[i] >= nums2[p2]) {
                nums1[j--] = nums1[i--];
            } else {
                nums1[j--] = nums2[p2--];
            }
        }
    }
}
