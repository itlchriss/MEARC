package g0401_0500.s0436_find_right_interval;

// #Medium #Array #Sorting #Binary_Search #Binary_Search_II_Day_11
// #2022_07_16_Time_20_ms_(81.51%)_Space_59.1_MB_(5.83%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private int[] findminmax(int[][] num) {
        int min = num[0][0];
        int max = num[0][0];
        for (int i = 1; i < num.length; i++) {
            min = Math.min(min, num[i][0]);
            max = Math.max(max, num[i][0]);
        }
        return new int[] {min, max};
    }
//@ ensures(*The integer array parameter `intervals` must not be null.*);
//@ ensures(*The integer array parameter `intervals` must have a length greater than 0.*);
//@ ensures(*The integer array parameter `intervals` must contain arrays of length 2, where the first element is the start point and the second element is the end point.*);
//@ ensures(*The integer result array must have the same length as the integer array parameter `intervals`.*);
//@ ensures(*For each interval `i` in the integer array parameter `intervals`, if there exists a right interval `j`, the result at index `i` is the index `j`.*);
//@ ensures(*If no right interval exists for interval `i`, the result at index `i` is -1.*);

    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length <= 1) {
            return new int[] {-1};
        }
        int n = intervals.length;
        int[] result = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }
        int[] minmax = findminmax(intervals);
        for (int i = minmax[1] - 1; i >= minmax[0] + 1; i--) {
            map.computeIfAbsent(i, k -> map.get(k + 1));
        }
        for (int i = 0; i < n; i++) {
            result[i] = map.getOrDefault(intervals[i][1], -1);
        }
        return result;
    }
}