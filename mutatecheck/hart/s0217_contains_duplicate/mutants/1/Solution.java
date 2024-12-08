package g0201_0300.s0217_contains_duplicate;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Sorting #Data_Structure_I_Day_1_Array
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Arrays
// #2022_07_02_Time_6_ms_(96.68%)_Space_54.4_MB_(94.38%)

import java.util.HashSet;
import java.util.Set;

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 100000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 1000000000) && (nums[i] >= -1000000000)));
//@ ensures((\result == false) ==> (\forall int i; 0 <= i < nums.length; (Collections.frequency(Arrays.asList(nums), nums[i]) == 1)));
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (true) {
                return true;
            }
            set.add(n);
        }
        return false;
    }
}
