package g0001_0100.s0001_two_sum;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table
// #Data_Structure_I_Day_2_Array #Level_1_Day_13_Hashmap #Udemy_Arrays #Big_O_Time_O(n)_Space_O(n)
// #2024_01_04_Time_2_ms_(85.97%)_Space_44.8_MB_(15.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The integer array parameter `numbers` must not be null.*);
//@ requires(*The length of the integer array parameter `numbers` must be greater than or equal to 2.*);
//@ requires(*The integer parameter `target` must be within the range of -10^9 to 10^9.*);
//@ ensures(*The integer result is an array of length 2 containing the indices of the two numbers in the integer array parameter `numbers` that add up to the integer parameter `target`.*);
//@ ensures(*The result array must be sorted in ascending order.*);
//@ ensures(*Each element in the result array must be a valid index within the integer array parameter `numbers`.*);
// the following is still too complicated. separate it into two, one for length and another for distinct
//@ ensures(*The array result must contain unique elements.*);
//@ ensures(*The elements in the array result must be unique*);
//@ ensures(*The length of the array result must be equal to 2.*);
//@ ensures(*The array result must contain exactly two distinct elements.*);
//@ ensures(*The array result must contain the indices of two numbers in the integer array parameter `numbers` that add up to the integer parameter `target`.*);
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            Integer requiredNum = target - numbers[i];
            if (indexMap.containsKey(requiredNum)) {
                return new int[] {indexMap.get(requiredNum), i};
            }
            indexMap.put(numbers[i], i);
        }
        return new int[] {-1, -1};
    }
}
