package g2701_2800.s2750_ways_to_split_array_into_good_subarrays;

// #Medium #Array #Dynamic_Programming #Math #2023_09_24_Time_7_ms_(96.36%)_Space_59.3_MB_(75.71%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is either 0 or 1.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is greater than or equal to 0.*);
	//@ ensures(*The return value is less than or equal to 10^9 + 7.*);
	//@ ensures(*The return value represents the number of ways to split the array `nums` into good subarrays.*);
    public int numberOfGoodSubarraySplits(int[] nums) {
        int lastOne = -1;
        int n = nums.length;
        long ans = 1;
        long mod = (long) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (lastOne != -1) {
                    ans = ans * (i - lastOne) % mod;
                }
                lastOne = i;
            }
        }
        if (lastOne == -1) {
            return 0;
        }
        return (int) ans;
    }
}