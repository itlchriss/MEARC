package g0401_0500.s0454_4sum_ii;

// #Medium #Top_Interview_Questions #Array #Hash_Table
// #2022_07_18_Time_133_ms_(95.19%)_Space_42.4_MB_(88.53%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input arrays `nums1`, `nums2`, `nums3`, and `nums4` must not be null.*);
	//@ requires(*The length of `nums1`, `nums2`, `nums3`, and `nums4` must be equal to `n`.*);
	//@ requires(*The value of `n` must be greater than or equal to 1 and less than or equal to 200.*);
	//@ requires(*The values in `nums1`, `nums2`, `nums3`, and `nums4` must be within the range of -2^28 to 2^28.*);
	//@ ensures(*The method should return an integer representing the number of tuples `(i, j, k, l)` such that `nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0`.*);
	//@ ensures(*The returned integer should be greater than or equal to 0.*);
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