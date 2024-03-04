package g2701_2800.s2740_find_the_value_of_the_partition;

// #Medium #Array #Sorting #2023_09_23_Time_18_ms_(100.00%)_Space_54.6_MB_(33.05%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 2.*);
//@ ensures(*All elements in the input array `nums` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the value of the partition.*);
//@ ensures(*The value of the partition is minimized.*);
//@ ensures(*The partition divides the input array `nums` into two non-empty arrays `nums1` and `nums2`.*);
//@ ensures(*Each element of the input array `nums` belongs to either `nums1` or `nums2`.*);
//@ ensures(*The maximum element of `nums1` is equal to the maximum element of `nums1`.*);
//@ ensures(*The minimum element of `nums2` is equal to the minimum element of `nums2`.*);
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int minDifference = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int difference = nums[i] - nums[i - 1];
            if (difference < minDifference) {
                minDifference = difference;
            }
        }
        return minDifference;
    }
}