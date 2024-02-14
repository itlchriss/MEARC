package g1001_1100.s1043_partition_array_for_maximum_sum;

// #Medium #Array #Dynamic_Programming #2022_02_27_Time_5_ms_(90.43%)_Space_41.8_MB_(37.80%)

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is at least 1.*);
	//@ requires(*The length of the input array `arr` is at most 500.*);
	//@ requires(*Each element in the input array `arr` is an integer.*);
	//@ requires(*Each element in the input array `arr` is between 0 and 10^9 (inclusive).*);
	//@ requires(*The input integer `k` is at least 1.*);
	//@ requires(*The input integer `k` is at most the length of the input array `arr`.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is the largest sum of the given array after partitioning.*);
	//@ ensures(*The output fits in a 32-bit integer.*);
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int right = 0; right < n; right++) {
            int localMax = arr[right];
            for (int left = right; left > Math.max(-1, right - k); left--) {
                localMax = Math.max(localMax, arr[left]);
                if (left == 0) {
                    dp[right] = Math.max(dp[right], (right - left + 1) * localMax);
                } else {
                    dp[right] = Math.max(dp[right], dp[left - 1] + (right - left + 1) * localMax);
                }
            }
        }
        return dp[n - 1];
    }
}