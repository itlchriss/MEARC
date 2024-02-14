package g1101_1200.s1109_corporate_flight_bookings;

// #Medium #Array #Prefix_Sum #2023_06_01_Time_2_ms_(100.00%)_Space_55.6_MB_(95.36%)

public class Solution {
	//@ requires(*The input array `bookings` is not null.*);
	//@ requires(*The length of the input array `bookings` is not zero.*);
	//@ requires(*The value of `n` is greater than or equal to 1.*);
	//@ requires(*The length of each booking in `bookings` is 3.*);
	//@ requires(*The value of `first_i` is greater than or equal to 1.*);
	//@ requires(*The value of `last_i` is less than or equal to `n`.*);
	//@ requires(*The value of `seats_i` is greater than or equal to 1.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of the output array `answer` is equal to `n`.*);
	//@ ensures(*Each element in the output array `answer` is an integer.*);
	//@ ensures(*The sum of all elements in the output array `answer` is equal to the total number of seats reserved for all flights.*);
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ret = new int[n];
        for (int[] booking : bookings) {
            ret[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                ret[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; ++i) {
            ret[i] += ret[i - 1];
        }
        return ret;
    }
}