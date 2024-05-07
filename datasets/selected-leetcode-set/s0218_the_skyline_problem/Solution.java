package g0201_0300.s0218_the_skyline_problem;

// #Hard #Top_Interview_Questions #Array #Heap_Priority_Queue #Ordered_Set #Divide_and_Conquer
// #Segment_Tree #Binary_Indexed_Tree #Line_Sweep
// #2022_07_02_Time_22_ms_(76.93%)_Space_52.3_MB_(45.14%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution {
//@ ensures(*The integer array parameter `buildings` must not be null.*);
//@ ensures(*The integer array parameter `buildings` must be sorted by the left edge coordinate in non-decreasing order.*);
//@ ensures(*The list result is sorted by the x-coordinate of the key points in non-decreasing order.*);
//@ ensures(*Each key point in the list represents the left endpoint of a horizontal segment in the skyline, except for the last point which always has a y-coordinate of 0 to mark the skyline's termination.*);
//@ ensures(*There must be no consecutive horizontal lines of equal height in the output skyline.*);
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> list = new ArrayList<>();
        List<int[]> lines = new ArrayList<>();
        for (int[] building : buildings) {
            lines.add(new int[] {building[0], building[2]});
            lines.add(new int[] {building[1], -building[2]});
        }
        lines.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int prev = 0;
        for (int[] line : lines) {
            if (line[1] > 0) {
                map.put(line[1], map.getOrDefault(line[1], 0) + 1);
            } else {
                int f = map.get(-line[1]);
                if (f == 1) {
                    map.remove(-line[1]);
                } else {
                    map.put(-line[1], f - 1);
                }
            }
            int curr = map.lastKey();
            if (curr != prev) {
                list.add(Arrays.asList(line[0], curr));
                prev = curr;
            }
        }
        return list;
    }
}