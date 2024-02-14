package g2501_2600.s2588_count_the_number_of_beautiful_subarrays;

// #Medium #Array #Hash_Table #Bit_Manipulation #Prefix_Sum
// #2023_08_22_Time_38_ms_(96.52%)_Space_58_MB_(52.17%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are non-negative integers.*);
	//@ requires(*The elements in the input array `nums` are less than or equal to 10^6.*);
	//@ ensures(*The method returns an integer representing the number of beautiful subarrays in the input array `nums`.*);
    public long beautifulSubarrays(int[] nums) {
        long count = 0;
        int xor = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            xor ^= num;
            count += map.merge(xor, 1, Integer::sum) - 1;
        }
        return count;
    }
}