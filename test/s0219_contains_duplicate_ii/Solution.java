package g0201_0300.s0219_contains_duplicate_ii;

// #Easy #Array #Hash_Table #Sliding_Window #2022_07_02_Time_15_ms_(99.09%)_Space_56_MB_(82.82%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and are greater than or equal to -1000000000.*);
//@ requires(*The integer parameter `k` is less than or equal to 100000 and is greater than or equal to 0.*);
//@ ensures(*If the boolean result is equal to true, there exist two distinct indices `i` and `j` in the integer array parameter `nums` such that the values at these indices are equal and the absolute difference between `i` and `j` is less than or equal to the integer parameter `k`.*);
//@ ensures(*If the boolean result is equal to false, there are no two distinct indices `i` and `j` in the integer array parameter `nums` such that the values at these indices are equal and the absolute difference between `i` and `j` is less than or equal to the integer parameter `k`.*);
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < len; i++) {
            Integer index = map.put(nums[i], i);
            if (index != null && Math.abs(index - i) <= k) {
                return true;
            }
        }
        return false;
    }
}