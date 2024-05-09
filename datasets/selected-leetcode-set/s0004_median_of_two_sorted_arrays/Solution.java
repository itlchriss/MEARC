package g0001_0100.s0004_median_of_two_sorted_arrays;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search #Divide_and_Conquer
// #Big_O_Time_O(log(min(N,M)))_Space_O(1) #2024_01_04_Time_1_ms_(100.00%)_Space_46.5_MB_(7.80%)

@SuppressWarnings("java:S2234")
public class Solution {
//@ requires(*The overall run time complexity of the method should be O(log (m+n)).*);
//@ requires(*If the length of the merged array of `nums1` and `nums2` is odd, the median is the middle element of the merged array.*);
//@ requires(*If the length of the merged array of `nums1` and `nums2` is even, the median is the average of the two middle elements of the merged array.*);
//@ requires(*The integer arrays `nums1` and `nums2` are sorted in ascending order.*);
//@ requires(*The length of the integer array `nums1` is greater than or equal to 0 and is less than or equal to 1000.*);
//@ requires(*The length of the integer array `nums2` is greater than or equal to 0 and is less than or equal to 1000.*);
//@ requires(*The sum of the lengths of the integer arrays `nums1` and `nums2` is greater than or equal to 1 and is less than or equal to 2000.*);
//@ requires(*All values in the integer arrays `nums1` and `nums2` are greater than or equal to -1000000 and are less than or equal to 1000000.*);
//@ ensures(*The double result is the median of the two sorted arrays `nums1` and `nums2`.*);
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
        //@ maintaining 0 <= low <= nums2.length && -1 <= high <= nums2.length && -1 <= (low + high)/2 - 1 <= nums2.length;
        while (low <= high) {            
            // this line has potential overflow problem. but it is not our duty to fix it.
            cut1 = (low + high) / 2;
            // this line has potential overflow problem. but it is not our duty to fix it.
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