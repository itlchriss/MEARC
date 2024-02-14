package g1901_2000.s1937_maximum_number_of_points_with_cost;

// #Medium #Array #Dynamic_Programming #2022_05_16_Time_18_ms_(44.04%)_Space_128_MB_(22.97%)

public class Solution {
	//@ requires(*The input matrix `points` is not null.*);
	//@ requires(*The input matrix `points` has at least one row and one column.*);
	//@ requires(*The input matrix `points` has a maximum size of 10^5 rows and columns.*);
	//@ requires(*The values in the input matrix `points` are non-negative integers.*);
	//@ ensures(*The method returns a long value representing the maximum number of points that can be achieved.*);
	//@ ensures(*The maximum number of points is calculated based on the rules described in the requirements.*);
	//@ ensures(*The method does not modify the input matrix `points`.*);
    public long maxPoints(int[][] points) {
        int m = points[0].length;
        long[] pre = new long[m];
        for (int[] point : points) {
            long[] current = new long[m];
            long max = Long.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                max = Math.max(max, pre[j] + j);
                current[j] = Math.max(current[j], point[j] - j + max);
            }
            max = Long.MIN_VALUE;
            for (int j = m - 1; j >= 0; j--) {
                max = Math.max(max, pre[j] - j);
                current[j] = Math.max(current[j], point[j] + j + max);
            }
            pre = current;
        }
        long max = Long.MIN_VALUE;
        for (long val : pre) {
            max = Math.max(max, val);
        }
        return max;
    }
}