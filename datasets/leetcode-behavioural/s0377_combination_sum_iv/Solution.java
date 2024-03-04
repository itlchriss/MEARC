package g0301_0400.s0377_combination_sum_iv;

// #Medium #Array #Dynamic_Programming #Dynamic_Programming_I_Day_21
// #2022_07_12_Time_1_ms_(92.54%)_Space_41.3_MB_(60.07%)

import java.util.Arrays;

public class Solution {
    private int[] storage;
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer parameter `target` must be greater than or equal to 1.*);
//@ ensures(*The integer result is the number of possible combinations that add up to the integer parameter `target` using the distinct integers in the integer array parameter `nums`.*);

    public int combinationSum4(int[] nums, int target) {
        storage = new int[target + 1];
        Arrays.fill(storage, -1);
        return result(nums, target);
    }

    private int result(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        if (storage[target] != -1) {
            return storage[target];
        }
        int count = 0;
        for (int i : nums) {
            count += result(nums, target - i);
        }
        storage[target] = count;
        return count;
    }
}