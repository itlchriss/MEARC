package g2001_2100.s2016_maximum_difference_between_increasing_elements;

// #Easy #Array #2022_05_23_Time_0_ms_(100.00%)_Space_41.4_MB_(94.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least - The elements in the input array `nums` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum difference between two elements in the input array `nums`.*);
//@ ensures(*If there is no valid pair of indices `i` and `j` such that `0 <= i < j < n` and `nums[i] < nums[j]`, the method returns -1.*);
    public int maximumDifference(int[] nums) {
        int mini = nums[0];
        int ans = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < mini) {
                mini = nums[i];
            }
            if (nums[i + 1] - mini > ans) {
                ans = nums[i + 1] - mini;
            }
        }
        return ans <= 0 ? -1 : ans;
    }
}