package g0001_0100.s0057_insert_interval;

// #Medium #Array #Level_2_Day_17_Interval #2023_08_11_Time_0_ms_(100.00%)_Space_43.7_MB_(95.60%)

import java.util.Arrays;

public class Solution {
//@ ensures(*The integer array parameter `intervals` must not be null.*);
//@ ensures(*The integer array parameter `intervals` is sorted in ascending order by the first element of each interval.*);
//@ ensures(*The integer array parameter `newInterval` must not be null.*);
//@ ensures(*The integer array parameter `newInterval` represents a non-overlapping interval.*);
//@ ensures(*The integer array result is the `intervals` array after inserting `newInterval` such that the intervals are still sorted in ascending order by the first element of each interval and there are no overlapping intervals.*);
//@ ensures(*If the `newInterval` overlaps with existing intervals in `intervals`, the overlapping intervals are merged appropriately.*);
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int l = 0;
        int r = n - 1;
        //@ assume \forall int k; 0 <= k < intervals.length; intervals[k] != null;
        //@ decreases n - l;
        while (l < n && newInterval[0] > intervals[l][1]) {
            l++;
        }
        //@ maintaining n - 1 >= r && r != 0;
        //@ decreasing r;
        while (r >= 0 && newInterval[1] < intervals[r][0]) {
            r--;
        }
        int[][] res = new int[l + n - r][2];
        //@ loop_invariant 0 <= i <= l;
        for (int i = 0; i < l; i++) {
            res[i] = Arrays.copyOf(intervals[i], intervals[i].length);
        }
        res[l][0] = Math.min(newInterval[0], l == n ? newInterval[0] : intervals[l][0]);
        res[l][1] = Math.max(newInterval[1], r == -1 ? newInterval[1] : intervals[r][1]);
        //@ maintaining l + 1 <= i < res.length;
        //@ loop_invariant r + 1 <= j <= n;
        for (int i = l + 1, j = r + 1; j < n; i++, j++) {
            res[i] = intervals[j];
        }
        return res;
    }
}