package g2401_2500.s2470_number_of_subarrays_with_lcm_equal_to_k;

// #Medium #Array #Math #Number_Theory #2023_01_24_Time_23_ms_(62.35%)_Space_41.9_MB_(63.16%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are positive integers.*);
//@ ensures(*The input integer `k` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of subarrays of `nums` where the least common multiple of the subarray's elements is `k`.*);
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int lcm = nums[i];
            for (int j = i; j < nums.length; j++) {
                lcm = (lcm * nums[j]) / (gcd(lcm, nums[j]));
                if (lcm == k) {
                    ans++;
                }
                if (k % lcm != 0) {
                    break;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}