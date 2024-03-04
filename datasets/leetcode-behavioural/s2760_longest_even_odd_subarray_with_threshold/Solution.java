package g2701_2800.s2760_longest_even_odd_subarray_with_threshold;

// #Easy #Array #Sliding_Window #2023_09_25_Time_4_ms_(96.92%)_Space_43.8_MB_(40.90%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The input integer `threshold` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned integer is greater than or equal to 0.*);
//@ ensures(*The returned integer is less than or equal to the length of the input array `nums`.*);
//@ ensures(*The returned integer represents the length of the longest subarray that satisfies the given conditions.*);
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxLength = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                int length = 1;
                int j = i + 1;
                while (j < nums.length && nums[j] <= threshold && nums[j] % 2 != nums[j - 1] % 2) {
                    length++;
                    j++;
                }
                maxLength = Math.max(maxLength, length);
                i = j - 1;
            }
            i++;
        }
        return maxLength;
    }
}