package g0501_0600.s0525_contiguous_array;

// #Medium #Array #Hash_Table #Prefix_Sum #2022_07_28_Time_31_ms_(80.05%)_Space_77.6_MB_(61.34%)

import java.util.HashMap;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is either 0 or 1.*);
	//@ ensures(*The method returns an integer value representing the maximum length of a contiguous subarray with an equal number of 0 and 1.*);
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ps = 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            ps += nums[i];
            if (!map.containsKey(ps)) {
                map.put(ps, i);
            } else {
                len = Math.max(len, i - map.get(ps));
            }
        }
        return len;
    }
}