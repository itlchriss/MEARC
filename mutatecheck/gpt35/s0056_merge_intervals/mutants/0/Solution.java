package g0001_0100.s0056_merge_intervals;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting
// #Data_Structure_II_Day_2_Array #Level_2_Day_17_Interval #Udemy_2D_Arrays/Matrix
// #Big_O_Time_O(n_log_n)_Space_O(n) #2023_08_11_Time_8_ms_(96.27%)_Space_45.2_MB_(90.13%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ requires(intervals != null && intervals.length > 0);
//@ ensures((\forall int i; 0 <= i && i < \result.length; (\exists int j; 0 <= j && j < intervals.length; \result[i][0] <= intervals[j][0] && \result[i][1] >= intervals[j][1])));
//@ ensures(\result != null && \result.length <= intervals.length);
//@ ensures((\forall int i, j; 0 <= i && i < \result.length && 0 <= j && j < \result.length && i != j; (\result[i][0] > \result[j][1] || \result[j][0] > \result[i][1])));
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int[] current = intervals[0];
        list.add(current);
        for (int[] next : intervals) {
            if (current[1] == next[0]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next;
                list.add(current);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
