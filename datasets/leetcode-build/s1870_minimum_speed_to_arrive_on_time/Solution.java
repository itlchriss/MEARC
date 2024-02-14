package g1801_1900.s1870_minimum_speed_to_arrive_on_time;

// #Medium #Array #Binary_Search #Binary_Search_II_Day_6
// #2022_05_10_Time_86_ms_(88.58%)_Space_100.1_MB_(51.38%)

@SuppressWarnings("java:S2184")
public class Solution {
	//@ requires(*The input array `dist` must not be null.*);
	//@ requires(*The length of the input array `dist` must be equal to the number of train rides `n`.*);
	//@ requires(*The number of train rides `n` must be greater than or equal to 1.*);
	//@ requires(*The distance of each train ride `dist[i]` must be greater than or equal to 1.*);
	//@ requires(*The input `hour` must be greater than or equal to 1.*);
	//@ requires(*The input `hour` must have at most two digits after the decimal point.*);
	//@ ensures(*The method should return a positive integer representing the minimum speed in kilometers per hour.*);
	//@ ensures(*If it is impossible to reach the office on time, the method should return -1.*);
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        return fmin(dist, n, hour);
    }

    private boolean check(int[] dist, int n, double h, int spe) {
        double cost = 0;
        for (int i = 0; i < n - 1; i++) {
            // same as ceil(doubleTime/doubleSpeed)
            cost += (dist[i] - 1) / spe + 1;
        }
        cost += (double) dist[n - 1] / (double) spe;
        return cost <= h;
    }

    private int fmin(int[] dist, int n, double h) {
        if (h + 1 <= n) {
            return -1;
        }
        int max = fmax(dist) * 100;
        int lo = 1;
        int hi = max;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            // speed of mid is possible, move to left side
            if (check(dist, n, h, mid)) {
                hi = mid;
            } else {
                // need higher speed, move to right side
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int fmax(int[] arr) {
        int res = arr[0];
        for (int num : arr) {
            res = Math.max(res, num);
        }
        return res;
    }
}