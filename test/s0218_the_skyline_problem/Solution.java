package g0201_0300.s0218_the_skyline_problem;

// #Hard #Top_Interview_Questions #Array #Heap_Priority_Queue #Ordered_Set #Divide_and_Conquer
// #Segment_Tree #Binary_Indexed_Tree #Line_Sweep
// #2022_07_02_Time_22_ms_(76.93%)_Space_52.3_MB_(45.14%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution {
//@ requires(*The length of the integer array parameter `buildings` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*Each element in the integer array parameter `buildings` is an array of three integers `[left_i, right_i, height_i]`.*);
//@ requires(*The integer value of `left_i` is less than or equal to the integer value of `right_i`.*);
//@ requires(*The integer value of `left_i` is greater than or equal to 0 and is less than or equal to 2^31 - 1.*);
//@ requires(*The integer value of `right_i` is greater than or equal to 0 and is less than or equal to 2^31 - 1.*);
//@ requires(*The integer value of `height_i` is greater than or equal to 1 and is less than or equal to 2^31 - 1.*);
//@ requires(*Each key point represents the left endpoint of a horizontal segment in the skyline except for the last point which always has a y-coordinate of 0.*);
//@ requires(*There are no consecutive horizontal lines of equal height in the output skyline.*);
//@ requires(*The ground between the leftmost and rightmost buildings is part of the skyline's contour.*);
//@ ensures(*The integer array result is a list of key points sorted by their x-coordinate in the form `[[x1,y1],[x2,y2],...]`.*);
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