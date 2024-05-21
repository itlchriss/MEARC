package g0201_0300.s0217_contains_duplicate;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Sorting #Data_Structure_I_Day_1_Array
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Arrays
// #2022_07_02_Time_6_ms_(96.68%)_Space_54.4_MB_(94.38%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,3,1]*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1,2,3,4]*);
//@ requires(*Output: false*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [1,1,1,3,3,4,3,2,4,2]*);
//@ requires(*Output: true*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>*);
//@ ensures(*Given an integer array param_nums, the result is `true` if any value appears at least twice in the array, and the result is `false` if every element is distinct.*);
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }
}