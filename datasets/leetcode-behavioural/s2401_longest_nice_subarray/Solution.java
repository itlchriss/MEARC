package g2401_2500.s2401_longest_nice_subarray;

// #Medium #Array #Bit_Manipulation #Sliding_Window
// #2022_09_26_Time_4_ms_(87.45%)_Space_70.5_MB_(79.87%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` is not empty.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer representing the length of the longest nice subarray.*);
//@ ensures(*The returned value is greater than or equal to 1.*);
//@ ensures(*The returned value is less than or equal to the length of the input array `nums`.*);
//@ ensures(*The returned value is the maximum length such that the bitwise AND of every pair of elements in the subarray is equal to 0.*);
//@ ensures(*The returned value is the length of a subarray that satisfies the conditions:*);
//@ ensures(*  - The bitwise AND of every pair of elements in the subarray is equal to 0.*);
//@ ensures(*  - The subarray is contiguous.*);
//@ ensures(*If there are multiple subarrays with the same maximum length, any of them can be chosen as the longest nice subarray.*);
    public int longestNiceSubarray(int[] nums) {
        int ans = 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            for (int i = right - 1; i >= left; i--) {
                if ((nums[i] & nums[right]) != 0) {
                    left = i + 1;
                    break;
                }
                if (i == left) {
                    ans = Math.max(ans, right - left + 1);
                }
            }
            right++;
        }
        return ans;
    }
}