package g0601_0700.s0674_longest_continuous_increasing_subsequence;

// #Easy #Array #2022_03_22_Time_2_ms_(40.35%)_Space_45.9_MB_(35.43%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ ensures(*The returned value is an integer.*);
	//@ ensures(*The returned value represents the length of the longest continuous increasing subsequence in the input array `nums`.*);
	//@ ensures(*The subsequence must be strictly increasing, meaning that for each `l <= i < r`, `nums[i] < nums[i + 1]`.*);
	//@ ensures(*The subsequence is defined by two indices `l` and `r` (`l < r`) such that it is `[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]`.*);
	//@ ensures(*The subsequence is continuous, meaning that there are no elements between `nums[l]` and `nums[r]` in the input array `nums`.*);
	//@ ensures(*If there are multiple longest continuous increasing subsequences with the same length, the method can return the length of any one of them.*);
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