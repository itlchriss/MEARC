package g1401_1500.s1453_maximum_number_of_darts_inside_of_a_circular_dartboard;

// #Hard #Array #Math #Geometry #2022_03_28_Time_22_ms_(100.00%)_Space_41.7_MB_(96.43%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("java:S1210")
public class Solution {
    private static class Angle implements Comparable<Angle> {
        double a;
        boolean enter;

        Angle(double a, boolean enter) {
            this.a = a;
            this.enter = enter;
        }

        @Override
	//@ requires(*The input array `darts` is not null.*);
	//@ requires(*The length of the input array `darts` is greater than or equal to - Each element in the input array `darts` is an array of length - The values of `x_i` and `y_i` in each element of `darts` are within the range of -10^4 to 10^- The value of `r` is within the range of 1 to*);
	//@ ensures(*The method returns an integer representing the maximum number of darts that can lie on the dartboard.*);
	//@ ensures(*The dartboard is placed on the wall such that the maximum number of darts lie on it.*);
	//@ ensures(*The dartboard has a center point and a radius.*);
	//@ ensures(*The center point of the dartboard is represented by the coordinates (x, y).*);
	//@ ensures(*The coordinates (x, y) of the center point are within the range of -10^4 to 10^- The radius of the dartboard is equal to the value of `r`.*);
	//@ ensures(*The maximum number of darts lie on the dartboard, considering the positions of the darts on the wall.*);
        public int compareTo(Angle o) {
            if (a > o.a) {
                return 1;
            } else if (a < o.a) {
                return -1;
            } else if (enter == o.enter) {
                return 0;
            } else if (enter) {
                return -1;
            }
            return 1;
        }
    }

    private int getPointsInside(int i, double r, int n, int[][] points, double[][] dis) {
        List<Angle> angles = new ArrayList<>(2 * n);
        for (int j = 0; j < n; j++) {
            if (i != j && dis[i][j] <= 2 * r) {
                double b = Math.acos(dis[i][j] / (2 * r));
                double a =
                        Math.atan2(
                                points[j][1] - points[i][1] * 1.0,
                                points[j][0] * 1.0 - points[i][0]);
                double alpha = a - b;
                double beta = a + b;
                angles.add(new Angle(alpha, true));
                angles.add(new Angle(beta, false));
            }
        }
        Collections.sort(angles);
        int count = 1;
        int res = 1;
        for (Angle a : angles) {
            if (a.enter) {
                count++;
            } else {
                count--;
            }
            if (count > res) {
                res = count;
            }
        }
        return res;
    }

    public int numPoints(int[][] points, int r) {
        int n = points.length;
        double[][] dis = new double[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                dis[i][j] =
                        dis[j][i] =
                                Math.sqrt(
                                        Math.pow(points[i][0] * 1.0 - points[j][0], 2)
                                                + Math.pow(points[i][1] * 1.0 - points[j][1], 2));
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getPointsInside(i, r, n, points, dis));
        }
        return ans;
    }
}