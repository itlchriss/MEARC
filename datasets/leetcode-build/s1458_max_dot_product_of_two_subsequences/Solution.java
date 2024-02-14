package g1401_1500.s1458_max_dot_product_of_two_subsequences;

// #Hard #Array #Dynamic_Programming #2022_03_29_Time_6_ms_(98.36%)_Space_41.8_MB_(93.44%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` must not be empty.*);
	//@ requires(*The lengths of `nums1` and `nums2` must be the same.*);
	//@ requires(*The elements in `nums1` and `nums2` must be integers.*);
	//@ requires(*The lengths of `nums1` and `nums2` must be between 1 and 500 (inclusive).*);
	//@ requires(*The elements in `nums1` and `nums2` must be between -1000 and 1000 (inclusive).*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the maximum dot product between non-empty subsequences of `nums1` and `nums2` with the same length.*);
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] marks = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int max = nums1[i] * nums2[j];
                if (i > 0 && j > 0) {
                    max = Math.max(max, max + marks[i - 1][j - 1]);
                }
                if (i > 0) {
                    max = Math.max(max, marks[i - 1][j]);
                }
                if (j > 0) {
                    max = Math.max(max, marks[i][j - 1]);
                }
                marks[i][j] = max;
            }
        }
        return marks[nums1.length - 1][nums2.length - 1];
    }
}