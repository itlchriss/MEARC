package g0501_0600.s0532_k_diff_pairs_in_an_array;

// #Medium #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers #Udemy_Arrays
// #2022_07_28_Time_13_ms_(58.23%)_Space_48.7_MB_(27.94%)

import java.util.HashSet;

public class Solution {
//@ requires(*A k-diff pair is an integer pair `(nums[i], nums[j])`, where the following are true:*);
//@ requires(*`0 <= i < j < nums.length`*);
//@ requires(*`|nums[i] - nums[j]| == k`*);
//@ requires(*Notice that `|val|` denotes the absolute value of `val`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [3,1,4,1,5], k = 2*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1,2,3,4,5], k = 1*);
//@ requires(*Output: 4*);
//@ requires(*Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [1,3,1,5,4], k = 0*);
//@ requires(*Output: 1*);
//@ requires(*Explanation: There is one 0-diff pair in the array, (1, 1).*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>4</sup></code>*);
//@ requires(*<code>-10<sup>7</sup> <= nums[i] <= 10<sup>7</sup></code>*);
//@ requires(*<code>0 <= k <= 10<sup>7</sup></code>*);
//@ ensures(*Given an array of integers param_nums and an integer param_k, the result is the number of unique k-diff pairs in the array.*);
//@ ensures(*Although we have two 1s in the input, we should only the result is the number of unique pairs.*);
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