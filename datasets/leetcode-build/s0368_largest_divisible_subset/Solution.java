package g0301_0400.s0368_largest_divisible_subset;

// #Medium #Array #Dynamic_Programming #Math #Sorting
// #2022_07_12_Time_24_ms_(87.83%)_Space_42.4_MB_(93.37%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // Helper class containing value and an arraylist
    private static class Helper {
        int val;
        List<Integer> al;

        Helper(int val) {
            this.val = val;
            al = new ArrayList<>();
        }
    }
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `nums` is less than or equal to 1000.*);
	//@ requires(*Each element in the input array `nums` is a distinct positive integer.*);
	//@ requires(*Each element in the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 2 * 10^9.*);
	//@ ensures(*The returned list `answer` is not null.*);
	//@ ensures(*The length of the returned list `answer` is greater than or equal to 1.*);
	//@ ensures(*The length of the returned list `answer` is less than or equal to the length of the input array `nums`.*);
	//@ ensures(*Each element in the returned list `answer` is a positive integer.*);
	//@ ensures(*Each element in the returned list `answer` is a distinct element from the input array `nums`.*);
	//@ ensures(*For every pair `(answer[i], answer[j])` of elements in the returned list `answer`, either `answer[i] % answer[j] == 0` or `answer[j] % answer[i] == 0`.*);

    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Initializing Helper type array
        Helper[] dp = new Helper[nums.length];
        // Sorting given array
        Arrays.sort(nums);
        // max variable will keep track size of Longest Divisible Subset
        int max = 0;
        // index variable will keep track the index at which dp contains Longest Divisible Subset
        int index = 0;
        dp[0] = new Helper(1);
        dp[0].al.add(nums[0]);
        // Operation similar to LIS
        for (int i = 1; i < nums.length; i++) {
            int max2 = 0;
            int pos = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && max2 < dp[j].val) {
                    max2 = dp[j].val;
                    pos = j;
                }
            }
            // max2 = 0, means no element exists which can divide the present element
            if (max2 == 0) {
                dp[i] = new Helper(max2 + 1);
                dp[i].al.add(nums[i]);
            } else {
                dp[i] = new Helper(max2 + 1);
                for (int val : dp[pos].al) {
                    dp[i].al.add(val);
                }
                dp[i].al.add(nums[i]);
            }
            if (max2 > max) {
                max = max2;
                index = i;
            }
        }
        // Returning Largest Divisible Subset
        return dp[index].al;
    }
}