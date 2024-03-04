package g0501_0600.s0523_continuous_subarray_sum;

// #Medium #Array #Hash_Table #Math #Prefix_Sum
// #2022_07_28_Time_37_ms_(41.45%)_Space_109.7_MB_(5.07%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer parameter `k` must be greater than 0.*);
//@ ensures(*If there exists a continuous subarray of size at least two in the integer array parameter `nums` whose elements sum up to a multiple of the integer parameter `k`, the boolean result is true.*);
//@ ensures(*If there is no continuous subarray of size at least two in the integer array parameter `nums` whose elements sum up to a multiple of the integer parameter `k`, the boolean result is false.*);
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;
            if (map.containsKey(remainder)) {
                if (map.get(remainder) + 1 < i) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}