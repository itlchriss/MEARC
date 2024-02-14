package g0301_0400.s0377_combination_sum_iv;

// #Medium #Array #Dynamic_Programming #Dynamic_Programming_I_Day_21
// #2022_07_12_Time_1_ms_(92.54%)_Space_41.3_MB_(60.07%)

import java.util.Arrays;

public class Solution {
    private int[] storage;
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` contains distinct integers.*);
	//@ requires(*The input array `nums` has a length greater than or equal to 1.*);
	//@ requires(*The target integer `target` is greater than or equal to 1.*);
	//@ requires(*The target integer `target` is less than or equal to 1000.*);
	//@ ensures(*The method returns an integer representing the number of possible combinations that add up to the target.*);
	//@ ensures(*The returned integer is a 32-bit integer.*);
	//@ ensures(*Additional behavioural requirements:*);
	//@ ensures(*The method should handle the case when the input array `nums` is empty and return 0.*);
	//@ ensures(*The method should handle the case when the target integer `target` is 0 and return 1 (as there is one possible combination: an empty combination).*);
	//@ ensures(*The method should handle the case when the target integer `target` is negative and return 0 (as there are no possible combinations that add up to a negative target).*);
	//@ ensures(*The method should handle the case when the input array `nums` contains negative integers and return the correct number of possible combinations that add up to the target.*);

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