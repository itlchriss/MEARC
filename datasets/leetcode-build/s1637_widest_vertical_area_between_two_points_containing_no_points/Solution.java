package g1601_1700.s1637_widest_vertical_area_between_two_points_containing_no_points;

// #Medium #Array #Sorting #2022_04_20_Time_17_ms_(74.19%)_Space_78.6_MB_(62.28%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `points` is a 2D array of integers.*);
	//@ requires(*The length of `points` is greater than or equal to 2.*);
	//@ requires(*Each element in `points` is an array of length 2.*);
	//@ requires(*The values of `x` and `y` in each element of `points` are non-negative integers.*);
	//@ ensures(*The method returns an integer representing the maximum width of the widest vertical area between two points.*);
	//@ ensures(*The maximum width is calculated by finding the difference between the maximum and minimum x-coordinates of any two points.*);
	//@ ensures(*The maximum width does not include points that are on the edge of the vertical area.*);
    public int maxWidthOfVerticalArea(int[][] points) {
        int[] xValues = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i][0];
        }
        Arrays.sort(xValues);
        int max = 0;
        for (int j = 0; j < xValues.length - 1; j++) {
            if (xValues[j + 1] - xValues[j] > max) {
                max = xValues[j + 1] - xValues[j];
            }
        }
        return max;
    }
}