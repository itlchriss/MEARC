package g0201_0300.s0219_contains_duplicate_ii;

// #Easy #Array #Hash_Table #Sliding_Window #2022_07_02_Time_15_ms_(99.09%)_Space_56_MB_(82.82%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,3,1], k = 3*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1,0,1,1], k = 1*);
//@ requires(*Output: true*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [1,2,3,1,2,3], k = 2*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>*);
//@ requires(*<code>0 <= k <= 10<sup>5</sup></code>*);
//@ ensures(*Given an integer array param_nums and an integer param_k, the result is `true` if there are two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.*);
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