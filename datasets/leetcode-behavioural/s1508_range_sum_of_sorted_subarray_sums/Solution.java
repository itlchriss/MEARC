package g1501_1600.s1508_range_sum_of_sorted_subarray_sums;

// #Medium #Array #Sorting #Binary_Search #Two_Pointers #Binary_Search_II_Day_14
// #2022_04_09_Time_60_ms_(93.84%)_Space_47.8_MB_(87.32%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is greater than or equal to 1.*);
//@ ensures(*The length of `nums` is less than or equal to 1000.*);
//@ ensures(*Each element in `nums` is a positive integer.*);
//@ ensures(*The value of `n` is equal to the length of `nums`.*);
//@ ensures(*The value of `left` is greater than or equal to 1.*);
//@ ensures(*The value of `right` is less than or equal to `n * (n + 1) / 2`.*);
//@ ensures(*The value of `left` is less than or equal to the value of `right`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer.*);
//@ ensures(*The returned value is the sum of the numbers from index `left` to index `right`, inclusive, in the new array.*);
//@ ensures(*The returned value is modulo `10^9 + 7`.*);
    public int rangeSum(int[] nums, int n, int left, int right) {
        int len = n * (n + 1) / 2;
        int[] arr = new int[len];
        int idx = 0;
        int prev = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                arr[idx] = prev + nums[j];
                prev = arr[idx];
                idx++;
            }
            prev = 0;
        }
        Arrays.sort(arr);
        int result = 0;
        int mod = 1000000007;
        for (int i = left - 1; i < right; i++) {
            result = (result + arr[i]) % mod;
        }
        return result;
    }
}