package g0701_0800.s0718_maximum_length_of_repeated_subarray;

// #Medium #Array #Dynamic_Programming #Binary_Search #Sliding_Window #Hash_Function #Rolling_Hash
// #2022_03_24_Time_58_ms_(77.21%)_Space_78.8_MB_(32.62%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The input arrays `nums1` and `nums2` have lengths greater than or equal to 1.*);
	//@ requires(*The elements in the input arrays `nums1` and `nums2` are integers.*);
	//@ requires(*The elements in the input arrays `nums1` and `nums2` are within the range of 0 to 100.*);
	//@ ensures(*The returned value is an integer.*);
	//@ ensures(*The returned value represents the maximum length of a subarray that appears in both `nums1` and `nums2`.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to the length of the shorter input array.*);
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}