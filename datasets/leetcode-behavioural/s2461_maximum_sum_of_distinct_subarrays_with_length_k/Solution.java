package g2401_2500.s2461_maximum_sum_of_distinct_subarrays_with_length_k;

// #Medium #Array #Hash_Table #Sliding_Window #2022_12_20_Time_40_ms_(93.40%)_Space_59.8_MB_(89.83%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to `k`.*);
//@ ensures(*The value of `k` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of 1 to 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum subarray sum.*);
//@ ensures(*If no subarray meets the conditions, the method returns - The length of the subarray is `k`.*);
//@ ensures(*All the elements in the subarray are distinct.*);
//@ ensures(*The subarray is a contiguous non-empty sequence of elements within the input array `nums`.*);
    public long maximumSubarraySum(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        long sum = 0;
        long current = 0;
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (seen.contains(nums[j])) {
                int val = nums[i++];
                seen.remove(val);
                current -= val;
            }
            seen.add(nums[j]);
            current += nums[j];
            j++;
            if (seen.size() == k) {
                sum = Math.max(sum, current);
                current -= nums[i];
                seen.remove(nums[i]);
                i++;
            }
        }
        return sum;
    }
}