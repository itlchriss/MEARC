package g1701_1800.s1779_find_nearest_point_that_has_the_same_x_or_y_coordinate;

// #Easy #Array #Programming_Skills_I_Day_3_Conditional_Statements
// #2022_04_30_Time_1_ms_(100.00%)_Space_50.1_MB_(80.01%)

public class Solution {
	//@ requires(*The input integers `x` and `y` must be valid Cartesian coordinates.*);
	//@ requires(*The input array `points` must not be null.*);
	//@ requires(*The length of the input array `points` must be greater than or equal to 1.*);
	//@ requires(*Each element in the input array `points` must be an array of length 2.*);
	//@ ensures(*The output integer must be the index of a valid point with the smallest Manhattan distance from the current location.*);
	//@ ensures(*If there are multiple valid points with the smallest Manhattan distance, the output must be the valid point with the smallest index.*);
	//@ ensures(*If there are no valid points, the output must be -1.*);
    public int nearestValidPoint(int x, int y, int[][] points) {
        int nearestManDistance = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] == x || point[1] == y) {
                int distance = Math.abs(point[0] - x) + Math.abs(point[1] - y);
                if (distance < nearestManDistance) {
                    result = i;
                    nearestManDistance = distance;
                }
            }
        }
        return result;
    }
}