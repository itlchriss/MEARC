package g1501_1600.s1546_maximum_number_of_non_overlapping_subarrays_with_sum_equals_target;

// #Medium #Array #Hash_Table #Greedy #Prefix_Sum
// #2022_04_11_Time_56_ms_(71.58%)_Space_89_MB_(38.59%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` is not empty.*);
//@ ensures(*The input integer `target` is non-negative.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the maximum number of non-empty non-overlapping subarrays with a sum equal to `target`.*);
//@ ensures(*The input array `nums` is not modified.*);
    public int maxNonOverlapping(int[] nums, int target) {
        int culSum = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int num : nums) {
            culSum += num;
            if (map.containsKey(culSum - target)) {
                res = Math.max(res, map.get(culSum - target) + 1);
            }
            map.put(culSum, res);
        }
        return res;
    }
}