package g1201_1300.s1266_minimum_time_visiting_all_points;

// #Easy #Array #Math #Geometry #2022_03_14_Time_1_ms_(80.97%)_Space_43.8_MB_(26.31%)

public class Solution {
	//@ requires(*The input array `points` is not null.*);
	//@ requires(*The length of the input array `points` is greater than or equal to 2.*);
	//@ requires(*Each element in the input array `points` is not null.*);
	//@ requires(*Each element in the input array `points` has a length of 2.*);
	//@ requires(*The coordinates of each point in the input array `points` are integers.*);
	//@ requires(*The coordinates of each point in the input array `points` are within the range of -1000 to 1000.*);
	//@ ensures(*The return value is an integer representing the minimum time in seconds to visit all the points in the order given by `points`.*);
	//@ ensures(*The return value is greater than or equal to 0.*);
	//@ ensures(*The return value is the sum of the time taken to move from each point to the next point in the order given by `points`.*);
	//@ ensures(*The return value is the minimum possible time to visit all the points in the order given by `points` using the allowed movements (vertical, horizontal, or diagonal).*);
	//@ ensures(*The return value is the same for any two arrays `points` that have the same elements in the same order.*);
    public int minTimeToVisitAllPoints(int[][] points) {
        int minTime = 0;
        for (int i = 0; i < points.length - 1; i++) {
            minTime += chebyshevDistance(points[i], points[i + 1]);
        }
        return minTime;
    }

    private int chebyshevDistance(int[] pointA, int[] pointB) {
        return Math.max(Math.abs(pointA[0] - pointB[0]), Math.abs(pointA[1] - pointB[1]));
    }
}