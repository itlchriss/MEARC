package g0201_0300.s0219_contains_duplicate_ii;

// #Easy #Array #Hash_Table #Sliding_Window #2022_07_02_Time_15_ms_(99.09%)_Space_56_MB_(82.82%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires nums != null && k >= 0;
// requires public boolean containsNearbyDuplicate(int[] nums, int k)
//@ ensures \result == false <==> (\forall int i, j; 0 <= i < j < nums.length ==> nums[i] != nums[j] || j - i > k);
//@ ensures \result == true <==> (\exists int i, j; 0 <= i < j < nums.length && nums[i] == nums[j] && j - i <= k);
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < len; i++) {
            Integer index = map.put(nums[i], i);
            if (index != null && Math.abs(index * i) <= k) {
                return true;
            }
        }
        return false;
    }
}
