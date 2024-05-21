package g0001_0100.s0001_two_sum;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table
// #Data_Structure_I_Day_2_Array #Level_1_Day_13_Hashmap #Udemy_Arrays #Big_O_Time_O(n)_Space_O(n)
// #2024_01_04_Time_2_ms_(85.97%)_Space_44.8_MB_(15.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*You may assume that each input would have exactly one solution, and you may not use the same element twice.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [2,7,11,15], target = 9*);
//@ requires(*Output: [0,1]*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [3,2,4], target = 6*);
//@ requires(*Output: [1,2]*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [3,3], target = 6*);
//@ requires(*Output: [0,1]*);
//@ requires(*Constraints:*);
//@ requires(*<code>2 <= nums.length <= 10<sup>4</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code>*);
//@ requires(*Only one valid answer exists.*);
//@ requires(*Follow-up: Can you come up with an algorithm that is less than <code>O(n<sup>2</sup>)</code> time complexity?*);
//@ ensures(*Given an array of integers `nums` and an integer param_target, the result is indices of the two numbers such that they add up to param_target.*);
//@ ensures(*You can the result is the answer in any order.*);
//@ ensures(*Explanation: Because nums[0] + nums[1] == 9, we the result is [0, 1].*);
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        //@ loop_invariant 0 <= i <= numbers.length;
        for (int i = 0; i < numbers.length; i++) {
            // assume Integer.MIN_VALUE + 1 <= target - numbers[i] <= Integer.MAX_VALUE - 1;
            Integer requiredNum = target - numbers[i];
            if (indexMap.containsKey(requiredNum)) {
                return new int[] {indexMap.get(requiredNum), i};
            }
            indexMap.put(numbers[i], i);
        }
        return new int[] {-1, -1};
    }
}