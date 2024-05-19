package g0401_0500.s0454_4sum_ii;

// #Medium #Top_Interview_Questions #Array #Hash_Table
// #2022_07_18_Time_133_ms_(95.19%)_Space_42.4_MB_(88.53%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of each integer array parameter `nums1`, `nums2`, `nums3`, and `nums4` is equal to `n` and is greater than or equal to 1 and less than or equal to 200.*);
//@ requires(*All the values in each integer array parameter `nums1`, `nums2`, `nums3`, and `nums4` are greater than or equal to -2^28 and less than or equal to 2^28.*);
//@ ensures(*The integer result is less than or equal to the maximum value of java integer and is greater than or equal to the minimum value of java integer.*);
//@ ensures(*If the integer result is equal to 2, the tuples are (0, 0, 0, 1) and (1, 1, 0, 0) where the sum of elements at the corresponding indices in `nums1`, `nums2`, `nums3`, and `nums4` is equal to 0.*);
//@ ensures(*If the integer result is equal to 1, there is one tuple where the sum of elements at the corresponding indices in `nums1`, `nums2`, `nums3`, and `nums4` is equal to 0.*);
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int k : nums3) {
            for (int i : nums4) {
                int sum = k + i;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int k : nums1) {
            for (int i : nums2) {
                int m = -(k + i);
                count += map.getOrDefault(m, 0);
            }
        }
        return count;
    }
}