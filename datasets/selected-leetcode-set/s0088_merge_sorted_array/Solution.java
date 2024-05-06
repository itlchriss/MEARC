package g0001_0100.s0088_merge_sorted_array;

// #Easy #Top_Interview_Questions #Array #Sorting #Two_Pointers #Data_Structure_I_Day_2_Array
// #2022_06_20_Time_0_ms_(100.00%)_Space_42.7_MB_(55.70%)

public class Solution {
//@ ensures(*The integer array parameter `nums1` is sorted in non-decreasing order.*);
//@ ensures(*The integer array parameter `nums2` is sorted in non-decreasing order.*);
//@ ensures(*The integer parameter `m` is the number of elements in the integer array parameter `nums1`.*);
//@ ensures(*The integer parameter `n` is the number of elements in the integer array parameter `nums2`.*);
//@ ensures(*The integer array parameter `nums1` has a length of `m + n`.*);
//@ ensures(*The integer array parameter `nums2` has a length of `n`.*);
//@ ensures(*The integer array parameter `nums1` contains the elements that should be merged in the first `m` elements.*);
//@ ensures(*The integer array parameter `nums2` contains the elements that should be merged in the last `n` elements.*);
//@ ensures(*The integer array parameter `nums2`'s elements are set to `0` and should be ignored during the merge.*);
//@ ensures(*The final merged array is stored inside the integer array parameter `nums1`.*);
//@ ensures(*The final merged array is sorted in non-decreasing order.*);
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = nums1.length - 1;
        int p2 = n - 1;
        while (p2 >= 0) {
            if (i >= 0 && nums1[i] > nums2[p2]) {
                nums1[j--] = nums1[i--];
            } else {
                nums1[j--] = nums2[p2--];
            }
        }
    }
}