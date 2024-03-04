package g0901_1000.s0939_minimum_area_rectangle;

// #Medium #Array #Hash_Table #Math #Sorting #Geometry
// #2022_03_30_Time_63_ms_(94.31%)_Space_54.6_MB_(73.31%)

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `points` is not null.*);
//@ ensures(*The length of the input array `points` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `points` is an array of length 2.*);
//@ ensures(*The x-coordinate and y-coordinate of each point in the input array `points` are non-negative integers.*);
//@ ensures(*The x-coordinate and y-coordinate of each point in the input array `points` are less than or equal to 40000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the minimum area of a rectangle formed from the given points.*);
//@ ensures(*The return value is 0 if there is no rectangle that can be formed from the given points.*);
//@ ensures(*The sides of the rectangle formed from the given points are parallel to the X and Y axes.*);
    public int minAreaRect(int[][] points) {
        if (points.length < 4) {
            return 0;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            map.putIfAbsent(p[0], new HashSet<>());
            map.get(p[0]).add(p[1]);
        }
        Arrays.sort(
                points,
                (a, b) ->
                        (a[0] == b[0]) ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                int area = Math.abs((p1[0] - p2[0]) * (p1[1] - p2[1]));
                if (area >= min || area == 0) {
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    min = area;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}