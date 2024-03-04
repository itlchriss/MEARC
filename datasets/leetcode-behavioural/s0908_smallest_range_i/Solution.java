package g0901_1000.s0908_smallest_range_i;

// #Easy #Array #Math #2022_03_28_Time_2_ms_(88.84%)_Space_41.9_MB_(99.76%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` must be non-negative integers.*);
//@ ensures(*The value of `k` must be a non-negative integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value must be an integer.*);
//@ ensures(*The returned value must be the minimum score of `nums` after applying the operation at most once for each index.*);
//@ ensures(*The minimum score is defined as the difference between the maximum and minimum elements in `nums`.*);
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min + k >= max - k) {
            return 0;
        }
        return (max - k) - (min + k);
    }
}