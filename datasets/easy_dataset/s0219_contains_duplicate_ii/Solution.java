package g0201_0300.s0219_contains_duplicate_ii;

// #Easy #Array #Hash_Table #Sliding_Window #2022_07_02_Time_15_ms_(99.09%)_Space_56_MB_(82.82%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*If there exist two distinct indices `i` and `j` in the integer array parameter `nums` such that `nums[i] == nums[j]` and the absolute difference between `i` and `j` is less than or equal to the integer parameter `k`, the boolean result is true.*);
//@ ensures(*If there are no two distinct indices `i` and `j` in the integer array parameter `nums` such that `nums[i] == nums[j]` and the absolute difference between `i` and `j` is less than or equal to the integer parameter `k`, the boolean result is false.*);
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            Integer index = map.put(nums[i], i);
            if (index != null && Math.abs(index - i) <= k) {
                return true;
            }
        }
        return false;
    }
}