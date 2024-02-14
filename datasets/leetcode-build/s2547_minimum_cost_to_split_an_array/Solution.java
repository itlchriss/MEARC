package g2501_2600.s2547_minimum_cost_to_split_an_array;

// #Hard #Array #Hash_Table #Dynamic_Programming #Counting
// #2023_08_15_Time_32_ms_(98.70%)_Space_44.3_MB_(66.23%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to - The elements of `nums` are non-negative integers.*);
	//@ requires(*The value of `k` is a positive integer.*);
	//@ ensures(*The method returns an integer representing the minimum possible cost of splitting the array `nums`.*);
	//@ ensures(*The array `nums` remains unchanged after the method is executed.*);
    public int minCost(int[] nums, int k) {
        int[] dp = new int[nums.length + 1];
        for (int startPos = nums.length - 1; startPos >= 0; startPos--) {
            dp[startPos] = Integer.MAX_VALUE;
            int[] freq = new int[nums.length + 1];
            int nRepeated = 0;
            for (int pos = startPos; pos < nums.length; pos++) {
                int curNum = nums[pos];
                if (freq[curNum] == 1) {
                    nRepeated += 2;
                } else if (freq[curNum] > 0) {
                    nRepeated++;
                }
                freq[curNum]++;
                dp[startPos] = Math.min(dp[startPos], k + nRepeated + dp[pos + 1]);
            }
        }
        return dp[0];
    }
}