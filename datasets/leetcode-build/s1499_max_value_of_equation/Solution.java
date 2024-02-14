package g1401_1500.s1499_max_value_of_equation;

// #Hard #Array #Heap_Priority_Queue #Sliding_Window #Queue #Monotonic_Queue
// #2022_03_21_Time_7_ms_(98.61%)_Space_105.2_MB_(79.40%)

public class Solution {
	//@ requires(*The input array `points` is not null.*);
	//@ requires(*The length of the input array `points` is at least - The elements in the input array `points` are not null.*);
	//@ requires(*The length of each element in the input array `points` is - The value of `k` is non-negative.*);
	//@ requires(*The elements in the input array `points` are sorted in ascending order based on the x-values.*);
	//@ requires(*The x-values in the input array `points` form a strictly increasing sequence.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the maximum value of the equation `y_i + y_j + |x_i - x_j|` where `|x_i - x_j| <= k` and `1 <= i < j <= points.length`.*);
    public int findMaxValueOfEquation(int[][] points, int k) {
        int res = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        int r = 0;
        int rMax = 0;
        for (int l = 0; l < points.length - 1; l++) {
            if (rMax == l) {
                max = Integer.MIN_VALUE;
                r = l + 1;
                rMax = r;
            }
            while (r < points.length && points[r][0] - points[l][0] <= k) {
                int v = points[r][0] + points[r][1];
                if (max < v) {
                    max = v;
                    rMax = r;
                }
                r++;
            }
            if (points[rMax][0] - points[l][0] <= k) {
                res =
                        Math.max(
                                res,
                                points[rMax][0] - points[l][0] + points[rMax][1] + points[l][1]);
            }
        }
        return res;
    }
}