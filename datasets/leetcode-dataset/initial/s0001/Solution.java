package g0001_0100.s0001_two_sum;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table
// #Data_Structure_I_Day_2_Array #Level_1_Day_13_Hashmap #Udemy_Arrays #Big_O_Time_O(n)_Space_O(n)
// #2024_01_04_Time_2_ms_(85.97%)_Space_44.8_MB_(15.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given an array of integers `nums` and an integer `target`, return _indices of the two numbers such that they add up to `target`_. You can return the answer in any order. **Explanation:** Because nums[0] + nums[1] == 9, we return [0, 1].*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
