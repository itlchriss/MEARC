package g0401_0500.s0454_4sum_ii;

// #Medium #Top_Interview_Questions #Array #Hash_Table
// #2022_07_18_Time_133_ms_(95.19%)_Space_42.4_MB_(88.53%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*`0 <= i, j, k, l < n`*);
//@ requires(*`nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0`*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: The two tuples are:*);
//@ requires(*1. (*);
//@ requires(*0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0*);
//@ requires(*2. (*);
//@ requires(*1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]*);
//@ requires(*Output: 1*);
//@ requires(*Constraints:*);
//@ requires(*`n == nums1.length`*);
//@ requires(*`n == nums2.length`*);
//@ requires(*`n == nums3.length`*);
//@ requires(*`n == nums4.length`*);
//@ requires(*`1 <= n <= 200`*);
//@ requires(*<code>-2<sup>28</sup> <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2<sup>28</sup></code>*);
//@ ensures(*Given four integer arrays param_nums1, param_nums2, param_nums3, and param_nums4 all of length `n`, the result is the number of tuples `(i, j, k, l)` such that:*);
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