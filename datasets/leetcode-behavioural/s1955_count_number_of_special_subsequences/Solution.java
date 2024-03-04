package g1901_2000.s1955_count_number_of_special_subsequences;

// #Hard #Array #Dynamic_Programming #2022_05_18_Time_22_ms_(80.65%)_Space_121_MB_(27.42%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are either 0, 1, or 2.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the number of different subsequences that are special.*);
//@ ensures(*The return value is non-negative.*);
//@ ensures(*The return value is less than or equal to 10^9 + 7.*);
    public int countSpecialSubsequences(int[] nums) {
        int mod = 1000000007;
        int[] dp = new int[] {1, 0, 0, 0};
        for (int n : nums) {
            dp[n + 1] = (dp[n] + 2 * dp[n + 1] % mod) % mod;
        }
        return dp[3];
    }
}