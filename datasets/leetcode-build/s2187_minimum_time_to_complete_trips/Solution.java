package g2101_2200.s2187_minimum_time_to_complete_trips;

// #Medium #Array #Binary_Search #2022_06_02_Time_187_ms_(95.03%)_Space_92.6_MB_(9.55%)

public class Solution {
	//@ requires(*The input array `time` is not null.*);
	//@ requires(*The length of the input array `time` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `time` are positive integers.*);
	//@ requires(*The input integer `totalTrips` is a positive integer.*);
	//@ ensures(*The method returns a long value representing the minimum time required for all buses to complete at least `totalTrips` trips.*);
	//@ ensures(*The returned minimum time is a positive integer.*);
	//@ ensures(*The returned minimum time is the smallest possible time that satisfies the condition of completing at least `totalTrips` trips.*);
	//@ ensures(*The method does not modify the input array `time`.*);
	//@ ensures(*The method does not modify the input integer `totalTrips`.*);
    public long minimumTime(int[] time, int totalTrips) {
        return bs(0, Long.MAX_VALUE, time, totalTrips);
    }

    private long bs(long left, long right, int[] time, long totalTrips) {
        if (left > right) {
            return Long.MAX_VALUE;
        }
        long mid = (left + right) >> 1;
        return isPossible(time, mid, totalTrips)
                ? Math.min(mid, bs(left, mid - 1, time, totalTrips))
                : bs(mid + 1, right, time, totalTrips);
    }

    private boolean isPossible(int[] time, long mid, long totalTrips) {
        long count = 0;
        for (int i : time) {
            count += mid / i;
            if (count >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}