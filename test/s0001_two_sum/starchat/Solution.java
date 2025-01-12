package g0001_0100.s0001_two_sum;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table
// #Data_Structure_I_Day_2_Array #Level_1_Day_13_Hashmap #Udemy_Arrays #Big_O_Time_O(n)_Space_O(n)
// #2024_01_04_Time_2_ms_(85.97%)_Space_44.8_MB_(15.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `numbers` is less than or equal to 10000 and is greater than or equal to 2.*);
//@ requires(*All values in the integer array parameter `numbers` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(*The integer parameter `target` is less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ ensures(*The integer array result is of length 2.*);
//@ ensures(*The values in the integer array result are less than or equal to the length of the integer array parameter `numbers` and is greater than or equal to 0.*);
//@ ensures(*The sum of the values at the indices in the integer array result is equal to the integer parameter `target`.*);
//@ ensures(*The values at the indices in the integer array result are unique.*);
//@ ensures(*The integer array result is sorted in ascending order.*);
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