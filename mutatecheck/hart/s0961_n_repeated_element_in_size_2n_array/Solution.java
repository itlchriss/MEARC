package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 10000) && (nums.length >= 4));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 10000) && (nums[i] >= 0)));
//@ ensures((Arrays.equals(nums, new int[] {1 , 2 , 3 , 3})) ==> (\result == 3));
//@ ensures((Arrays.equals(nums, new int[] {2 , 1 , 2 , 5 , 3 , 2})) ==> (\result == 2));
//@ ensures((Arrays.equals(nums, new int[] {5 , 1 , 5 , 2 , 5 , 3 , 5 , 4})) ==> (\result == 5));
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}
