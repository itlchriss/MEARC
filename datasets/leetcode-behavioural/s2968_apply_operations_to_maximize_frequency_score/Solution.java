package g2901_3000.s2968_apply_operations_to_maximize_frequency_score;

// #Hard #Array #Sorting #Binary_Search #Prefix_Sum #Sliding_Window
// #2024_01_16_Time_27_ms_(78.37%)_Space_55.8_MB_(53.47%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The input integer `k` is non-negative.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum score.*);
//@ ensures(*The maximum score is the frequency of the most frequent element in the final array.*);
//@ ensures(*The final array is obtained by performing at most `k` operations on the input array `nums`.*);
//@ ensures(*Each operation can either increase or decrease the value of an element in the array by 1.*);
//@ ensures(*The resulting array after each operation is a valid array with non-negative integers.*);
//@ ensures(*The elements in the resulting array are the same as the elements in the input array except for the element that was modified during the operation.*);
//@ ensures(*The frequency of an element is the number of occurrences of that element in the array.*);
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int start = 0;
        int end = 1;
        int out = 1;
        while (end < n) {
            end++;
            int mid = (start + end) / 2;
            long target = nums[mid];
            long cost =
                    (target * (mid - start) - (prefixSum[mid] - prefixSum[start]))
                            + (prefixSum[end] - prefixSum[mid] - target * (end - mid));
            while (start < end && cost > k) {
                start++;
                mid = (start + end) / 2;
                target = nums[mid];
                cost =
                        (target * (mid - start) - (prefixSum[mid] - prefixSum[start]))
                                + (prefixSum[end] - prefixSum[mid] - target * (end - mid));
            }
            out = Math.max(out, end - start);
        }
        return out;
    }
}