package g1101_1200.s1109_corporate_flight_bookings;

// #Medium #Array #Prefix_Sum #2023_06_01_Time_2_ms_(100.00%)_Space_55.6_MB_(95.36%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _an array_ `answer` _of length_ `n`_, where_ `answer[i]` _is the total number of seats reserved for flight_ `i`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
