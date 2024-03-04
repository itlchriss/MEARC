package g1001_1100.s1005_maximize_sum_of_array_after_k_negations;

// #Easy #Array #Sorting #Greedy #2022_02_21_Time_3_ms_(81.75%)_Space_41.4_MB_(41.36%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be greater than or equal to 1.*);
//@ ensures(*The value of `k` must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The modified array `nums` should have the same length as the original array.*);
//@ ensures(*The modified array `nums` should have the largest possible sum after applying the negation process `k` times.*);
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int minIndex = 0;
        for (int i = 0; i < nums.length && k > 0; i++) {
            if (nums[i] < 0) {
                nums[i] *= -1;
                k--;
            }
            if (nums[minIndex] > nums[i]) {
                minIndex = i;
            }
        }
        if ((k & 1) == 1) {
            nums[minIndex] *= -1;
        }
        return Arrays.stream(nums).sum();
    }
}