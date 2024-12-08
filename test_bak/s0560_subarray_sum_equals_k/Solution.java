package g0501_0600.s0560_subarray_sum_equals_k;

// #Medium #Top_100_Liked_Questions #Array #Hash_Table #Prefix_Sum #Data_Structure_II_Day_5_Array
// #Big_O_Time_O(n)_Space_O(n) #2022_08_03_Time_21_ms_(98.97%)_Space_46.8_MB_(88.27%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and are greater than or equal to -1000.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000000 and is greater than or equal to -10000000.*);
//@ ensures(*The integer result is equal to the total number of continuous subarrays whose sum equals the integer parameter `k`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1,1] and the integer parameter `k` is equal to 2, the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3] and the integer parameter `k` is equal to 3, the integer result is equal to 2.*);
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