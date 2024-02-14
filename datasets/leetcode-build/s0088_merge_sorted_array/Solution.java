package g0001_0100.s0088_merge_sorted_array;

// #Easy #Top_Interview_Questions #Array #Sorting #Two_Pointers #Data_Structure_I_Day_2_Array
// #2022_06_20_Time_0_ms_(100.00%)_Space_42.7_MB_(55.70%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are sorted in non-decreasing order.*);
	//@ requires(*The length of `nums1` is equal to `m + n`.*);
	//@ requires(*The length of `nums2` is equal to `n`.*);
	//@ requires(*The values of `m` and `n` are within the range of 0 to 200.*);
	//@ requires(*The values of `nums1[i]` and `nums2[j]` are within the range of -10^9 to 10^9.*);
	//@ ensures(*The elements of `nums1` are merged with the elements of `nums2` in non-decreasing order.*);
	//@ ensures(*The merged array is stored inside `nums1`.*);
	//@ ensures(*The first `m` elements of `nums1` represent the merged elements.*);
	//@ ensures(*The last `n` elements of `nums1` are set to 0 and should be ignored.*);
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