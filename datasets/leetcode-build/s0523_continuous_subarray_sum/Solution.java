package g0501_0600.s0523_continuous_subarray_sum;

// #Medium #Array #Hash_Table #Math #Prefix_Sum
// #2022_07_28_Time_37_ms_(41.45%)_Space_109.7_MB_(5.07%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 2.*);
	//@ requires(*The integer `k` is not null.*);
	//@ requires(*The integer `k` is greater than or equal to 1.*);
	//@ ensures(*The method returns a boolean value indicating whether `nums` has a continuous subarray of size at least two whose elements sum up to a multiple of `k`.*);
	//@ ensures(*If `nums` has a continuous subarray of size at least two whose elements sum up to a multiple of `k`, the method returns `true`.*);
	//@ ensures(*If `nums` does not have a continuous subarray of size at least two whose elements sum up to a multiple of `k`, the method returns `false`.*);
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