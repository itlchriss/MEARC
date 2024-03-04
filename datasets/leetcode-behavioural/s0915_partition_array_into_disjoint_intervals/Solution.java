package g0901_1000.s0915_partition_array_into_disjoint_intervals;

// #Medium #Array #2022_03_29_Time_2_ms_(99.81%)_Space_96.1_MB_(47.60%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is at least 2.*);
//@ ensures(*The elements in `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is the length of the `left` subarray after partitioning.*);
//@ ensures(*The `left` subarray is non-empty.*);
//@ ensures(*Every element in the `left` subarray is less than or equal to every element in the `right` subarray.*);
//@ ensures(*The `left` subarray has the smallest possible size.*);
    public int partitionDisjoint(int[] nums) {
        int res = 0;
        int leftMax = nums[0];
        int greater = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (greater <= nums[i]) {
                greater = nums[i];
            } else if (nums[i] < leftMax) {
                res = i;
                leftMax = greater;
            }
        }
        return res + 1;
    }
}