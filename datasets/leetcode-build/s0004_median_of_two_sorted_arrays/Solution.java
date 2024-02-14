package g0001_0100.s0004_median_of_two_sorted_arrays;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search #Divide_and_Conquer
// #Big_O_Time_O(log(min(N,M)))_Space_O(1) #2024_01_04_Time_1_ms_(100.00%)_Space_46.5_MB_(7.80%)

@SuppressWarnings("java:S2234")
public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are sorted in ascending order.*);
	//@ requires(*The lengths of `nums1` and `nums2` are not negative.*);
	//@ requires(*The sum of the lengths of `nums1` and `nums2` is not greater than The values in `nums1` and `nums2` are within the range of -10^6 to 10^*);
	//@ ensures(*The method returns a double value representing the median of the two sorted arrays.*);
	//@ ensures(*The overall run time complexity of the method is O(log (m+n)).*);
	//@ ensures(*The merged array of `nums1` and `nums2` is sorted in ascending order.*);
	//@ ensures(*If the merged array has an odd length, the median is the middle element of the array.*);
	//@ ensures(*If the merged array has an even length, the median is the average of the two middle elements of the array.*);
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int cut1;
        int cut2;
        int n1 = nums1.length;
        int n2 = nums2.length;
        int low = 0;
        int high = n1;
        while (low <= high) {
            cut1 = (low + high) / 2;
            cut2 = ((n1 + n2 + 1) / 2) - cut1;
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];
            if (l1 <= r2 && l2 <= r1) {
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 0.0f;
    }
}