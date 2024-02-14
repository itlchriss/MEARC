package g1001_1100.s1035_uncrossed_lines;

// #Medium #Array #Dynamic_Programming #2022_02_27_Time_5_ms_(85.32%)_Space_45.3_MB_(5.21%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The lengths of `nums1` and `nums2` are both greater than or equal to 1.*);
	//@ requires(*The elements in `nums1` and `nums2` are integers.*);
	//@ requires(*The elements in `nums1` and `nums2` are within the range of 1 to 2000.*);
	//@ ensures(*The method returns an integer representing the maximum number of connecting lines that can be drawn.*);
	//@ ensures(*The method does not modify the input arrays `nums1` and `nums2`.*);
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int[] dpRow = new int[nums2.length + 1];
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dpRow[j] = dp[j - 1] + 1;
                } else {
                    dpRow[j] = Math.max(dp[j], dpRow[j - 1]);
                }
            }
            dp = dpRow;
        }

        return dp[nums2.length];
    }
}