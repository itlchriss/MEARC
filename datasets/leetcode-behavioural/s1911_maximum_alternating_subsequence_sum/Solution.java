package g1901_2000.s1911_maximum_alternating_subsequence_sum;

// #Medium #Array #Dynamic_Programming #2022_05_14_Time_12_ms_(51.75%)_Space_97.4_MB_(59.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the maximum alternating sum of any subsequence of the input array `nums`.*);
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long even = nums[0];
        long odd = 0;
        for (int i = 1; i < n; i++) {
            even = Math.max(even, Math.max(odd + nums[i], nums[i]));
            odd = Math.max(odd, Math.max(even - nums[i], 0));
        }
        return Math.max(even, odd);
    }
}