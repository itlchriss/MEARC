package g1601_1700.s1671_minimum_number_of_removals_to_make_mountain_array;

// #Hard #Array #Dynamic_Programming #Greedy #Binary_Search
// #2022_04_22_Time_19_ms_(81.23%)_Space_48.7_MB_(6.83%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the input array `nums` is at least 3.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of 1 to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of elements to remove to make `nums` a mountain array.*);
//@ ensures(*The output is non-negative.*);
//@ ensures(*The output is the smallest possible number of elements to remove.*);
//@ ensures(*The input array `nums` is not modified.*);
//@ ensures(*The input array `nums` remains the same after the method is executed.*);
//@ ensures(*The input array `nums` is a mountain array after the method is executed.*);
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        // lbs -> longest bitomic subsequence
        int lbs = 0;
        int[] dp = new int[n];
        // dp[i] -> lis end at index i, dp2[i] -> lds end at index i
        int[] dp2 = new int[n];
        List<Integer> lis = new ArrayList<>();
        // calculate longest increasing subsequence
        for (int i = 0; i < n - 1; i++) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < nums[i]) {
                lis.add(nums[i]);
            } else {
                int idx = Collections.binarySearch(lis, nums[i]);
                if (idx < 0) {
                    lis.set(-idx - 1, nums[i]);
                }
            }
            dp[i] = lis.size();
        }
        lis = new ArrayList<>();
        // calculate longest decreasing subsequence
        for (int i = n - 1; i >= 1; i--) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < nums[i]) {
                lis.add(nums[i]);
            } else {
                int idx = Collections.binarySearch(lis, nums[i]);
                if (idx < 0) {
                    lis.set(-idx - 1, nums[i]);
                }
            }
            dp2[i] = lis.size();
            if (dp[i] > 1 && dp2[i] > 1) {
                lbs = Math.max(lbs, dp[i] + dp2[i] - 1);
            }
        }
        return n - lbs;
    }
}