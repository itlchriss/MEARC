package g2301_2400.s2321_maximum_score_of_spliced_array;

// #Hard #Array #Dynamic_Programming #2022_06_30_Time_3_ms_(99.68%)_Space_79.3_MB_(84.76%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums1` and `nums2` are not null.*);
//@ ensures(*The lengths of `nums1` and `nums2` are equal.*);
//@ ensures(*The length `n` of `nums1` and `nums2` is greater than or equal to 1.*);
//@ ensures(*The elements in `nums1` and `nums2` are integers.*);
//@ ensures(*The elements in `nums1` and `nums2` are between 1 and 10^4 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum possible score.*);
//@ ensures(*The maximum possible score is the maximum of the sum of `nums1` and the sum of `nums2`.*);
//@ ensures(*The maximum possible score is achieved by either not swapping any subarray or by swapping a subarray between indices `left` and `right` in both `nums1` and `nums2`.*);
//@ ensures(*The subarray to be swapped must be a contiguous sequence of elements within `nums1` and `nums2`.*);
//@ ensures(*The indices `left` and `right` must be between 0 and `n-1` (inclusive).*);
//@ ensures(*The indices `left` and `right` must satisfy the condition `0 <= left <= right < n`.*);
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        int n = nums1.length;
        for (int num : nums1) {
            sum1 += num;
        }
        for (int num : nums2) {
            sum2 += num;
        }
        if (sum2 > sum1) {
            int temp = sum2;
            sum2 = sum1;
            sum1 = temp;
            int[] temparr = nums2;
            nums2 = nums1;
            nums1 = temparr;
        }
        // now sum1>=sum2
        // maxEndingHere denotes the maximum sum subarray ending at current index(ie. element at
        // current index has to be included)
        // minEndingHere denotes the minimum sum subarray ending at current index
        int maxEndingHere;
        int minEndingHere;
        int maxSoFar;
        int minSoFar;
        int currEle;
        maxEndingHere = minEndingHere = maxSoFar = minSoFar = nums2[0] - nums1[0];
        for (int i = 1; i < n; i++) {
            currEle = nums2[i] - nums1[i];
            minEndingHere += currEle;
            maxEndingHere += currEle;
            if (maxEndingHere < currEle) {
                maxEndingHere = currEle;
            }
            if (minEndingHere > currEle) {
                minEndingHere = currEle;
            }
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
            minSoFar = Math.min(minEndingHere, minSoFar);
        }
        // return the maximum of the 2 possibilities dicussed
        // also keep care that maxSoFar>=0 and maxSoFar<=0
        return Math.max(sum1 + Math.max(maxSoFar, 0), sum2 - Math.min(0, minSoFar));
    }
}