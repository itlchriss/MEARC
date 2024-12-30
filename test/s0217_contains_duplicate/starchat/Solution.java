package g0201_0300.s0217_contains_duplicate;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Sorting #Data_Structure_I_Day_1_Array
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Arrays
// #2022_07_02_Time_6_ms_(96.68%)_Space_54.4_MB_(94.38%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 219\. Contains Duplicate II*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer array `nums` and an integer `k`, return `true` if there are two **distinct indices** `i` and `j` in the array such that `nums[i] = nums[j]` and the **absolute** difference between `i` and `j` is less than or equal to `k`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,1\], k = 3*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,0,1,1\], k = 1*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,1,2,3\], k = 2*);
//@ requires(**);
//@ requires(***Output:** false*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 105`*);
//@ requires(**   `-109 <= nums[i] <= 109`*);
//@ requires(**   `0 <= k <= 105`*);
//@ requires(**);
//@ requires(*Method signature: public boolean containsNearbyDuplicate(int[] nums, int k)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(*The integer parameter `k` is less than or equal to 100000 and is greater than or equal to 0.*);
//@ ensures(*The boolean result is true if any value appears at least twice in the array, and false if every element is distinct.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1], the boolean result is true.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4], the boolean result is false.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1,1,3,3,4,3,2,4,2], the boolean result is true.*);
//@ ensures(*The boolean result is true if there are two distinct indices `i` and `j` in the array such that `nums[i] = nums[j]` and the absolute difference between `i` and `j` is less than or equal to `k`.*);
//@ ensures(*The boolean result is false if there are no two distinct indices `i` and `j` in the array such that `nums[i] = nums[j]` and the absolute difference between `i` and `j` is less than or equal to `k`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1] and the integer parameter `k` is equal to 3, the boolean result is true.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,0,1,1] and the integer parameter `k` is equal to 1, the boolean result is true.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1,2,3] and the integer parameter `k` is equal to 2, the boolean result is false.*);
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