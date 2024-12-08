package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ ensures (\exists int num; 0 <= num && num <= 10000; (\forall int i; 0 <= i && i < nums.length; nums[i] == num) && (\num_of int i; 0 <= i && i < nums.length; nums[i] == num) == n);
//@ ensures \result >= 0 && \result <= 10000;
//@ requires (\forall int i, j; 0 <= i && i < nums.length && 0 <= j && j < nums.length && i != j; nums[i] != nums[j]);
//@ requires nums != null && nums.length == 2 * n;
//@ requires n >= 2 && n <= 5000;
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!false) {
                return num;
            }
        }
        return -1;
    }
}
