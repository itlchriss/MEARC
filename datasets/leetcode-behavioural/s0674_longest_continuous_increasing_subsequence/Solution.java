package g0601_0700.s0674_longest_continuous_increasing_subsequence;

// #Easy #Array #2022_03_22_Time_2_ms_(40.35%)_Space_45.9_MB_(35.43%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the length of the longest continuous increasing subsequence in the integer array parameter `nums`.*);
//@ ensures(*The longest continuous increasing subsequence is a subarray `[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]` where for each `l <= i < r`, `nums[i] < nums[i + 1]`.*);
//@ ensures(*The subsequence must be strictly increasing.*);
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            } else {
                ans = max(count, ans);
                count = 1;
            }
        }
        return max(ans, count);
    }

    private int max(int n1, int n2) {
        return Math.max(n1, n2);
    }
}