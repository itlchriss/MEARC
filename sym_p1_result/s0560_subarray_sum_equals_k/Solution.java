package g0501_0600.s0560_subarray_sum_equals_k;

// #Medium #Top_100_Liked_Questions #Array #Hash_Table #Prefix_Sum #Data_Structure_II_Day_5_Array
// #Big_O_Time_O(n)_Space_O(n) #2022_08_03_Time_21_ms_(98.97%)_Space_46.8_MB_(88.27%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,1,1], k = 2*);
//@ requires(*Output: 2*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1,2,3], k = 3*);
//@ requires(*Output: 2*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 2  10<sup>4</sup></code>*);
//@ requires(*`-1000 <= nums[i] <= 1000`*);
//@ requires(*<code>-10<sup>7</sup> <= k <= 10<sup>7</sup></code>*);
//@ ensures(*Given an array of integers param_nums and an integer param_k, the result is the total number of continuous subarrays whose sum equals to param_k.*);
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
                sumCount.put(tempSum, sumCount.get(tempSum) + 1);
            } else {
                sumCount.put(tempSum, 1);
            }
        }
        return ret;
    }
}