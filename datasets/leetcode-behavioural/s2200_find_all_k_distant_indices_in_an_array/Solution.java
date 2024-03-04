package g2101_2200.s2200_find_all_k_distant_indices_in_an_array;

// #Easy #Array #2022_06_01_Time_2_ms_(95.30%)_Space_45.9_MB_(61.88%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` has at least one element.*);
//@ ensures(*The input array `nums` has a length less than or equal to 1000.*);
//@ ensures(*The input array `nums` contains integers between 1 and 1000.*);
//@ ensures(*The input integer `key` is an element from the array `nums`.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(*The input integer `k` is less than or equal to the length of the array `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output list contains the indices of the k-distant elements in the array `nums`.*);
//@ ensures(*The output list is sorted in increasing order.*);
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> ans = new ArrayList<>();
        int start = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                start = Math.max(i - k, start);
                int end = Math.min(i + k, n - 1);
                while (start <= end) {
                    ans.add(start++);
                }
            }
        }
        return ans;
    }
}