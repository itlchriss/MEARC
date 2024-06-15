package g0501_0600.s0532_k_diff_pairs_in_an_array;

// #Medium #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers #Udemy_Arrays
// #2022_07_28_Time_13_ms_(58.23%)_Space_48.7_MB_(27.94%)

import java.util.HashSet;

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 10000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 10000000) && (nums[i] >= -10000000)));
//@ requires((k <= 10000000) && (k >= 0));
//@ ensures(\result <= nums.length);
//@ ensures(((Arrays.equals(nums, new int[] {1 , 2 , 3 , 4 , 5})) && (k == 1)) ==> (\result == 4));
//@ ensures(((Arrays.equals(nums, new int[] {3 , 1 , 4 , 1 , 5})) && (k == 2)) ==> (\result == 2));
//@ ensures(((Arrays.equals(nums, new int[] {1 , 3 , 1 , 5 , 4})) && (k == 0)) ==> (\result == 1));
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
                if (set.contains(n - k)) {

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
