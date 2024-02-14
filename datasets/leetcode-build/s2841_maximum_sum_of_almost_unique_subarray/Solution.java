package g2801_2900.s2841_maximum_sum_of_almost_unique_subarray;

// #Medium #Array #Hash_Table #Sliding_Window #2023_12_12_Time_18_ms_(97.37%)_Space_45.4_MB_(81.05%)

import java.util.HashMap;
import java.util.List;

public class Solution {
	//@ requires(*The input list `nums` must not be null.*);
	//@ requires(*The input list `nums` must have a length greater than or equal to 1.*);
	//@ requires(*The input integers `m` and `k` must be positive.*);
	//@ requires(*The input integer `m` must be less than or equal to `k`.*);
	//@ requires(*The input list `nums` must have a length greater than or equal to `k`.*);
	//@ ensures(*The output must be a long integer.*);
	//@ ensures(*The output must be the maximum sum out of all almost unique subarrays of length `k` of `nums`.*);
	//@ ensures(*If no such subarray exists, the output must be 0.*);
    public long maxSum(List<Integer> nums, int m, int k) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        int count = 0;
        long ans = 0;
        int left = 0;
        long cur = 0;
        for (int i = 0; i < nums.size(); i++) {
            cur += nums.get(i);
            if (hash.containsKey(nums.get(i))) {
                hash.put(nums.get(i), hash.get(nums.get(i)) + 1);
            } else {
                hash.put(nums.get(i), 1);
                count++;
            }
            if (i - left + 1 == k) {
                if (count >= m) {
                    ans = Math.max(ans, cur);
                }
                if (hash.get(nums.get(left)) > 1) {
                    hash.put(nums.get(left), hash.get(nums.get(left)) - 1);
                } else {
                    count--;
                    hash.remove(nums.get(left));
                }
                cur -= nums.get(left);
                left++;
            }
        }
        return ans;
    }
}