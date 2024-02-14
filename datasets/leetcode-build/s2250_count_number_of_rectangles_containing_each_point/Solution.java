package g2201_2300.s2250_count_number_of_rectangles_containing_each_point;

// #Medium #Array #Sorting #Binary_Search #Binary_Indexed_Tree
// #2022_06_13_Time_49_ms_(98.80%)_Space_71.8_MB_(79.17%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `rectangles` is a 2D integer array.*);
	//@ requires(*The input `points` is a 2D integer array.*);
	//@ requires(*The length of the input `rectangles` is greater than or equal to 1.*);
	//@ requires(*The length of the input `points` is greater than or equal to 1.*);
	//@ requires(*Each element in `rectangles` is a 2-element array representing the length and height of a rectangle.*);
	//@ requires(*Each element in `points` is a 2-element array representing the x and y coordinates of a point.*);
	//@ requires(*The length and height of each rectangle in `rectangles` is greater than or equal to 1.*);
	//@ requires(*The x and y coordinates of each point in `points` is greater than or equal to 1.*);
	//@ requires(*The length and height of each rectangle in `rectangles` is less than or equal to 10^9.*);
	//@ requires(*The x and y coordinates of each point in `points` is less than or equal to 100.*);
	//@ requires(*All rectangles in `rectangles` are unique.*);
	//@ requires(*All points in `points` are unique.*);
	//@ ensures(*The output `count` is an integer array of length `points.length`.*);
	//@ ensures(*Each element in `count` represents the number of rectangles that contain the corresponding point in `points`.*);
	//@ ensures(*The elements in `count` are in the same order as the points in `points`.*);
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = rectangles.length;
        int q = points.length;
        int[][] es = new int[n + q][];
        System.arraycopy(rectangles, 0, es, 0, n);
        for (int i = 0; i < q; i++) {
            es[n + i] = new int[] {points[i][0], points[i][1], i};
        }
        Arrays.sort(es, (x, y) -> x[0] != y[0] ? -(x[0] - y[0]) : x.length - y.length);
        int[] ct = new int[101];
        int[] ans = new int[q];
        for (int[] e : es) {
            if (e.length == 2) {
                for (int i = 0; i <= e[1]; i++) {
                    ct[i]++;
                }
            } else {
                ans[e[2]] = ct[e[1]];
            }
        }
        return ans;
    }
}