package g0401_0500.s0454_4sum_ii;

// #Medium #Top_Interview_Questions #Array #Hash_Table
// #2022_07_18_Time_133_ms_(95.19%)_Space_42.4_MB_(88.53%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` is equal to `n`.*);
//@ requires(*The length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` is less than or equal to 200.*);
//@ requires(*The length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are less than or equal to 2^28.*);
//@ requires(*All values in the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are greater than or equal to -2^28.*);
//@ ensures(*The integer result is less than or equal to the length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4`.*);
//@ ensures(*The integer result is greater than or equal to 1.*);
//@ ensures(*If the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are equal to [1,2], [-2,-1], [-1,2], and [0,2], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are equal to [0], [0], [0], and [0], the integer result is equal to 1.*);
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