package g0501_0600.s0532_k_diff_pairs_in_an_array;

// #Medium #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers #Udemy_Arrays
// #2022_07_28_Time_13_ms_(58.23%)_Space_48.7_MB_(27.94%)

import java.util.HashSet;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 2.*);
	//@ requires(*The value of `k` is non-negative.*);
	//@ ensures(*The method returns an integer representing the number of unique k-diff pairs in the array.*);
	//@ ensures(*The returned value is non-negative.*);
	//@ ensures(*The returned value is the correct count of unique k-diff pairs in the array.*);
	//@ ensures(*The method does not modify the input array `nums`.*);
	//@ ensures(*The method does not modify the value of `k`.*);
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