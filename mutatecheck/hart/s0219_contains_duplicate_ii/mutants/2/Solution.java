package g0201_0300.s0219_contains_duplicate_ii;

// #Easy #Array #Hash_Table #Sliding_Window #2022_07_02_Time_15_ms_(99.09%)_Space_56_MB_(82.82%)

import java.util.HashMap;
import java.util.Map;

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((k <= 100000) && (k >= 0));
//@ requires((nums.length <= 100000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 1000000000) && (nums[i] >= -1000000000)));
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; false; i++) {
            Integer index = map.put(nums[i], i);
            if (index != null && Math.abs(index - i) <= k) {
                return true;
            }
        }
        return false;
    }
}
