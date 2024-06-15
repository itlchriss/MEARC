package g0501_0600.s0560_subarray_sum_equals_k;

// #Medium #Top_100_Liked_Questions #Array #Hash_Table #Prefix_Sum #Data_Structure_II_Day_5_Array
// #Big_O_Time_O(n)_Space_O(n) #2022_08_03_Time_21_ms_(98.97%)_Space_46.8_MB_(88.27%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures \result >= 0;
// requires public int subarraySum(int[] nums, int k)
//@ ensures \result == (\sum int i, j; 0 <= i <= j < nums.length; (\sum int m; i <= m <= j; nums[m]) == k ? 1 : 0);
// ensures \result == countSubarrays(nums, k); - private int countSubarrays(int[] nums, int k)
//@ ensures (\forall int i; 0 <= i < nums.length; nums[i] >= -1000 && nums[i] <= 1000);
//@ ensures -10000000 <= k && k <= 10000000;
//@ requires nums != null && k != null;
    public int subarraySum(int[] nums, int k) {
        int tempSum = 0;
        int ret = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1);
        for (int i : nums) {

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
