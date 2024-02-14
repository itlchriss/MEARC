package g2601_2700.s2605_form_smallest_number_from_two_digit_arrays;

// #Easy #Array #Hash_Table #Enumeration #2023_08_29_Time_1_ms_(95.34%)_Space_40.4_MB_(61.44%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The arrays `nums1` and `nums2` must not be empty.*);
	//@ requires(*The arrays `nums1` and `nums2` must contain only unique digits.*);
	//@ requires(*The length of `nums1` and `nums2` must be between 1 and 9 (inclusive).*);
	//@ requires(*The digits in `nums1` and `nums2` must be between 1 and 9 (inclusive).*);
	//@ ensures(*The returned number must be the smallest number that contains at least one digit from each array.*);
	//@ ensures(*The returned number must be formed by combining digits from `nums1` and `nums2`.*);
	//@ ensures(*The returned number must be an integer.*);
	//@ ensures(*The returned number must be the smallest possible number that satisfies the above conditions.*);
    public int minNumber(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] temp = new int[Math.max(nums1[nums1.length - 1], nums2[nums2.length - 1]) + 1];
        int n1 = nums1[0];
        int n2 = nums2[0];
        int k = Math.min(n1 * 10 + n2, n2 * 10 + n1);
        for (int value : nums1) {
            temp[value]++;
        }
        for (int i : nums2) {
            if (temp[i] > 0) {
                k = Math.min(k, i);
                return k;
            }
        }
        return k;
    }
}