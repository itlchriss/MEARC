package g2701_2800.s2789_largest_element_in_an_array_after_merge_operations;

// #Medium #Array #Greedy #Prefix_Sum #2023_09_15_Time_1_ms_(100.00%)_Space_58_MB_(58.92%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a positive integer.*);
//@ ensures(*The output is the largest element that can be obtained in the final array after performing the operations.*);
//@ ensures(*The final array is obtained by applying the operations on the input array `nums`.*);
//@ ensures(*The final array does not contain any element that is smaller than its adjacent element.*);
//@ ensures(*The final array contains at least one element.*);
//@ ensures(*The final array contains only positive integers.*);
//@ ensures(*The final array is obtained by merging adjacent elements in the input array `nums` according to the given operation.*);
    public long maxArrayValue(int[] nums) {
        long ans = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; --i) {
            if (ans >= nums[i - 1]) {
                ans += nums[i - 1];
            } else {
                ans = nums[i - 1];
            }
        }
        return ans;
    }
}