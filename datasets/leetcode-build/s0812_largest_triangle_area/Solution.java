package g0801_0900.s0812_largest_triangle_area;

// #Easy #Array #Math #Geometry #2022_03_23_Time_5_ms_(92.04%)_Space_39.8_MB_(87.61%)

public class Solution {
	//@ requires(*The input array `points` must not be null.*);
	//@ requires(*The length of the input array `points` must be at least 3.*);
	//@ requires(*The coordinates of each point in the input array `points` must be within the range of -50 to 50.*);
	//@ ensures(*The method should return a double value representing the area of the largest triangle that can be formed by any three different points.*);
	//@ ensures(*The returned area value should be within 10^-5 of the actual answer.*);
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double area;
                    int[] a = points[i];
                    int[] b = points[j];
                    int[] c = points[k];
                    area = Math.abs(area(a, b) + area(b, c) + area(c, a));
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }

    private double area(int[] a, int[] b) {
        int l = b[0] - a[0];
        double h = (a[1] + b[1] + 200) / 2.0;
        return l * h;
    }
}