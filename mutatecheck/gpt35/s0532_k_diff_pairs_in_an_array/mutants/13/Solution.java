package g0501_0600.s0532_k_diff_pairs_in_an_array;

// #Medium #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers #Udemy_Arrays
// #2022_07_28_Time_13_ms_(58.23%)_Space_48.7_MB_(27.94%)

import java.util.HashSet;

public class Solution {
//@ ensures (\forall int i, j; 0 <= i && i < j && j < nums.length; (\exists int m, n; 0 <= m && m < n && n < nums.length; i != m && j != n && Math.abs(nums[i] - nums[j]) == k && nums[i] == nums[m] && nums[j] == nums[n]));
// requires private int countUniqueKDiffsHelper(int[] nums, int k)
//@ ensures \result >= 0;
// requires private int countUniqueKDiffs(int[] nums, int k)
//@ ensures (\forall int i, j, m, n; 0 <= i && i < j && j < nums.length && 0 <= m && m < n && n < nums.length; i != m && j != n ==> (nums[i] != nums[m] || nums[j] != nums[n]));
//@ requires nums != null && k >= 0;
//@ ensures \result == (\num_of int i, j; 0 <= i && i < j && j < nums.length; Math.abs(nums[i] - nums[j]) == k);
//@ ensures \result == countUniqueKDiffsHelper(nums, k);
//@ ensures \result == countUniqueKDiffs(nums, k);
// requires public int findPairs(int[] nums, int k)
    public int findPairs(int[] nums, int k) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> twice = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                if (k == 0 && !twice.contains(n)) {
                    res++;
                    twice.add(n);
                } else {
                    continue;
                }
            } else {
                if (set.contains(n * k)) {
                    res++;
                }
                if (set.contains(n + k)) {
                    res++;
                }
            }
            set.add(n);
        }
        return res;
    }
}
