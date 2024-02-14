package g2901_3000.s2956_find_common_elements_between_two_arrays;

// #Easy #Array #Hash_Table #2024_01_15_Time_1_ms_(100.00%)_Space_45.6_MB_(11.23%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The lengths of `nums1` and `nums2` are greater than or equal to - The elements in `nums1` and `nums2` are integers between 1 and*);
	//@ ensures(*The returned array `answer` is not null.*);
	//@ ensures(*The length of `answer` is - The first element of `answer` is the number of indices `i` such that `0 <= i < n` and `nums1[i]` occurs at least once in `nums2`.*);
	//@ ensures(*The second element of `answer` is the number of indices `i` such that `0 <= i < m` and `nums2[i]` occurs at least once in `nums1`.*);
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] freq2 = new int[101];
        int[] freq1 = new int[101];
        int[] ans = new int[2];
        for (int j : nums2) {
            freq2[j] = 1;
        }
        for (int j : nums1) {
            freq1[j] = 1;
            ans[0] = ans[0] + freq2[j];
        }
        for (int j : nums2) {
            ans[1] = ans[1] + freq1[j];
        }
        return ans;
    }
}