package g0601_0700.s0698_partition_to_k_equal_sum_subsets;

// #Medium #Array #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask #Memoization
// #2022_03_22_Time_24_ms_(85.43%)_Space_51.3_MB_(21.34%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` has at least `k` elements.*);
	//@ requires(*The input integer `k` is greater than or equal to - The input integer `k` is less than or equal to the length of the input array `nums`.*);
	//@ requires(*Each element in the input array `nums` is a positive integer.*);
	//@ requires(*The frequency of each element in the input array `nums` is between 1 and 4 (inclusive).*);
	//@ ensures(*The method returns `true` if it is possible to divide the input array `nums` into `k` non-empty subsets whose sums are all equal.*);
	//@ ensures(*The method returns `false` if it is not possible to divide the input array `nums` into `k` non-empty subsets whose sums are all equal.*);
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        // sum of each subset = sum / k
        sum /= k;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (dp[i] == -1) {
                continue;
            }
            int rem = sum - (dp[i] % sum);
            for (int j = 0; j < n; j++) {
                // bitmask
                int tmp = i | (1 << j);
                // skip if the bit is already taken
                if (tmp != i) {
                    // num too big for current subset
                    if (nums[j] > rem) {
                        break;
                    }
                    // cumulative sum
                    dp[tmp] = dp[i] + nums[j];
                }
            }
        }
        // true if total sum of all nums is the same
        return dp[(1 << n) - 1] == k * sum;
    }
}