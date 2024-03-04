package g0401_0500.s0416_partition_equal_subset_sum;

// #Medium #Top_100_Liked_Questions #Array #Dynamic_Programming #Level_2_Day_13_Dynamic_Programming
// #Big_O_Time_O(n*sums)_Space_O(n*sums) #2022_12_29_Time_27_ms_(94.53%)_Space_41.8_MB_(95.29%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The boolean result is true if the integer array parameter `nums` can be partitioned into two subsets such that the sum of elements in both subsets is equal.*);
//@ ensures(*The boolean result is false if the integer array parameter `nums` cannot be partitioned into equal sum subsets.*);
    public boolean canPartition(int[] nums) {
        int sums = 0;
        for (int num : nums) {
            sums += num;
        }
        if (sums % 2 == 1) {
            return false;
        }
        sums /= 2;
        boolean[] dp = new boolean[sums + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int sum = sums; sum >= num; sum--) {
                dp[sum] = dp[sum] || dp[sum - num];
            }
        }
        return dp[sums];
    }
}