package g0401_0500.s0447_number_of_boomerangs;

// #Medium #Array #Hash_Table #Math #2022_06_03_Time_156_ms_(80.44%)_Space_109.2_MB_(69.17%)

import java.util.HashMap;

public class Solution {
	//@ requires(*The input `points` is a 2D array of integers.*);
	//@ requires(*The length of `points` is greater than or equal to 1 and less than or equal to 500.*);
	//@ requires(*Each element in `points` is a 2-element array representing the coordinates of a point.*);
	//@ requires(*The x and y coordinates of each point are integers between -10^4 and 10^4.*);
	//@ requires(*All points in `points` are distinct.*);
	//@ ensures(*The method returns an integer representing the number of boomerangs.*);
	//@ ensures(*A boomerang is a tuple of points (i, j, k) where the distance between i and j is equal to the distance between i and k.*);
	//@ ensures(*The order of the points in the tuple matters.*);
	//@ ensures(*The method correctly counts the number of boomerangs in the given set of points.*);
	//@ ensures(*If there are no boomerangs in the given set of points, the method returns 0.*);
    public int numberOfBoomerangs(int[][] points) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int dis = dist(points[i], points[j]);
                int prev = m.getOrDefault(dis, 0);
                if (prev >= 1) {
                    ans += prev * 2;
                }
                m.put(dis, prev + 1);
            }
            m.clear();
        }
        return ans;
    }

    private int dist(int[] d1, int[] d2) {
        return (d1[0] - d2[0]) * (d1[0] - d2[0]) + (d1[1] - d2[1]) * (d1[1] - d2[1]);
    }
}