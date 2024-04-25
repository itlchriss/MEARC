package g0001_0100.s0001_two_sum;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table
// #Data_Structure_I_Day_2_Array #Level_1_Day_13_Hashmap #Udemy_Arrays #Big_O_Time_O(n)_Space_O(n)
// #2024_01_04_Time_2_ms_(85.97%)_Space_44.8_MB_(15.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
// requires(-10^9 <= target <= 10^9);
//@ ensures((\result != null && \result.length == 2));
//@ requires(numbers != null && numbers.length >= 2);
//@ ensures((\exists int i, j; 0 <= i && i < numbers.length && 0 <= j && j < numbers.length && i != j; numbers[i] + numbers[j] == target));
// requires((\forall int i; 0 <= i && i < numbers.length; -10^9 <= numbers[i] && numbers[i] <= 10^9));
//@ ensures(numbers[\result[0]] + numbers[\result[1]] == target);
//@ ensures((\forall int i; 0 <= i && i < \result.length; 0 <= \result[i] && \result[i] < numbers.length));
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        //@ loop_invariant 0 <= i <= numbers.length;
        for (int i = 0; i < numbers.length; i++) {
            //@ assume target - numbers[i] >= Integer.MIN_VALUE && target - numbers[i] <= Integer.MAX_VALUE;
            Integer requiredNum = target + numbers[i];
            if (indexMap.containsKey(requiredNum)) {
                return new int[] {indexMap.get(requiredNum), i};
            }
            indexMap.put(numbers[i], i);
        }
        return new int[] {-1, -1};
    }
}
