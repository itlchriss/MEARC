package g2701_2800.s2779_maximum_beauty_of_an_array_after_applying_operation;

// #Medium #Array #Sorting #Binary_Search #Sliding_Window
// #2023_09_21_Time_37_ms_(93.81%)_Space_58.5_MB_(50.34%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The value of `k` is a non-negative integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum possible beauty of the array `nums` after applying the operation any number of times.*);
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int n = nums.length;
        int j = 0;
        while (j < n) {
            if (nums[j] - nums[i] > k * 2) {
                i++;
            }
            j++;
        }
        return j - i;
    }
}