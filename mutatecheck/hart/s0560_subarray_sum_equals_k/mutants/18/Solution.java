package g0501_0600.s0560_subarray_sum_equals_k;

// #Medium #Top_100_Liked_Questions #Array #Hash_Table #Prefix_Sum #Data_Structure_II_Day_5_Array
// #Big_O_Time_O(n)_Space_O(n) #2022_08_03_Time_21_ms_(98.97%)_Space_46.8_MB_(88.27%)

import java.util.HashMap;
import java.util.Map;

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((k <= 10000000) && (k >= -10000000));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 1000) && (nums[i] >= -1000)));
//@ requires((nums.length <= 20000) && (nums.length >= 1));
    public int subarraySum(int[] nums, int k) {
        int tempSum = 0;
        int ret = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1);
        for (int i : nums) {
            tempSum += i;
            if (sumCount.containsKey(tempSum - k)) {
                ret += sumCount.get(tempSum - k);
            }
            if (sumCount.get(tempSum) != null) {

            } else {
                sumCount.put(tempSum, 1);
            }
        }
        return ret;
    }
}
