package g0101_0200.s0149_max_points_on_a_line;

// #Hard #Top_Interview_Questions #Array #Hash_Table #Math #Geometry #Algorithm_II_Day_21_Others
// #2022_06_24_Time_11_ms_(99.21%)_Space_41.5_MB_(95.53%)

public class Solution {
	//@ requires(*1. The input array `points` is not null.*);
	//@ requires(*2. The length of the input array `points` is greater than or equal to 1.*);
	//@ requires(*3. Each element in the input array `points` is an array of length 2.*);
	//@ requires(*4. The x-coordinate and y-coordinate of each point in the input array `points` are within the range -10^4 to 10^4.*);
	//@ ensures(*1. The method returns an integer representing the maximum number of points that lie on the same straight line.*);
	//@ ensures(*2. The method does not modify the input array `points`.*);
	//@ ensures(*3. The method handles the case when all points in the input array `points` are the same, and returns the length of the input array `points`.*);
	//@ ensures(*4. The method handles the case when all points in the input array `points` are collinear, and returns the length of the input array `points`.*);
	//@ ensures(*5. The method handles the case when there are multiple lines with different slopes that pass through the same point, and returns the maximum number of points on any of these lines.*);
	//@ ensures(*6. The method handles the case when there are multiple lines with the same slope that pass through different points, and returns the maximum number of points on any of these lines.*);
	//@ ensures(*7. The method handles the case when there are multiple lines with different slopes that pass through different points, and returns the maximum number of points on any of these lines.*);
    public int maxPoints(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                int c = x * points[j][1] - y * points[j][0];
                int count = 2;
                for (int k = j + 1; k < points.length; k++) {
                    if (c == x * points[k][1] - y * points[k][0]) {
                        count++;
                    }
                }
                max = Math.max(count, max);
            }
        }
        return max;
    }
}