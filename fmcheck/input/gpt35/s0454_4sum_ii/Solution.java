package g0401_0500.s0454_4sum_ii;

// #Medium #Top_Interview_Questions #Array #Hash_Table
// #2022_07_18_Time_133_ms_(95.19%)_Space_42.4_MB_(88.53%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires nums1.length > 0 && nums1.length <= 200;
//@ requires nums1 != null && nums2 != null && nums3 != null && nums4 != null;
//@ requires (\forall int i; 0 <= i && i < nums4.length; -2^28 <= nums4[i] && nums4[i] <= 2^28);
//@ requires nums1.length == nums2.length && nums2.length == nums3.length && nums3.length == nums4.length;
//@ ensures \result >= 0;
//@ requires (\forall int i; 0 <= i && i < nums1.length; -2^28 <= nums1[i] && nums1[i] <= 2^28);
//@ requires (\forall int i; 0 <= i && i < nums2.length; -2^28 <= nums2[i] && nums2[i] <= 2^28);
//@ requires (\forall int i; 0 <= i && i < nums3.length; -2^28 <= nums3[i] && nums3[i] <= 2^28);
//@ ensures (\forall int i, j, k, l; 0 <= i && i < nums1.length && 0 <= j && j < nums2.length && 0 <= k && k < nums3.length && 0 <= l && l < nums4.length; nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0 ==> \result > 0);
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
