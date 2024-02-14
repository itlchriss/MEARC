package g0201_0300.s0300_longest_increasing_subsequence;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Binary_Search #Algorithm_II_Day_16_Dynamic_Programming #Binary_Search_II_Day_3
// #Dynamic_Programming_I_Day_18 #Udemy_Dynamic_Programming #Big_O_Time_O(n*log_n)_Space_O(n)
// #2022_07_06_Time_3_ms_(98.63%)_Space_44.3_MB_(60.27%)

public class Solution {
	//@ requires(*1. The input array `nums` is not null.*);
	//@ requires(*2. The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*3. The elements in the input array `nums` are integers.*);
	//@ requires(*4. The length of the input array `nums` is less than or equal to 2500.*);
	//@ requires(*5. The elements in the input array `nums` are within the range of -10^4 to 10^4.*);
	//@ ensures(*1. The output is an integer representing the length of the longest strictly increasing subsequence.*);
	//@ ensures(*2. The output is greater than or equal to 1.*);
	//@ ensures(*3. The output is less than or equal to the length of the input array `nums`.*);
	//@ ensures(*4. The output is obtained by deleting some or no elements from the input array `nums` without changing the order of the remaining elements.*);
	//@ ensures(*5. The output is obtained by selecting a subsequence from the input array `nums` such that the selected subsequence is strictly increasing.*);
	//@ ensures(*6. The output is obtained by selecting a subsequence from the input array `nums` such that the length of the selected subsequence is maximum.*);
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        // prefill the dp table
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        int left = 1;
        int right = 1;
        for (int curr : nums) {
            int start = left;
            int end = right;
            // binary search, find the one that is lower than curr
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (dp[mid] > curr) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            // update our dp table
            if (dp[start] > curr) {
                dp[start] = curr;
            } else if (curr > dp[start] && curr < dp[end]) {
                dp[end] = curr;
            } else if (curr > dp[end]) {
                dp[++end] = curr;
                right++;
            }
        }
        return right;
    }
}