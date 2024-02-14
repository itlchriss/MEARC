package g0801_0900.s0849_maximize_distance_to_closest_person;

// #Medium #Array #2022_03_24_Time_1_ms_(100.00%)_Space_44_MB_(92.64%)

public class Solution {
    private int maxDist = 0;
	//@ requires(*The input array `seats` is not null.*);
	//@ requires(*The length of the input array `seats` is at least 2.*);
	//@ requires(*The elements of the input array `seats` are either 0 or 1.*);
	//@ requires(*There is at least one empty seat (0) in the input array `seats`.*);
	//@ requires(*There is at least one occupied seat (1) in the input array `seats`.*);
	//@ ensures(*The output is an integer representing the maximum distance to the closest person.*);
	//@ ensures(*The output is greater than or equal to 0.*);

    public int maxDistToClosest(int[] seats) {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                extend(seats, i);
            }
        }
        return maxDist;
    }

    private void extend(int[] seats, int position) {
        int left = position - 1;
        int right = position + 1;
        int leftMinDistance = 1;
        while (left >= 0) {
            if (seats[left] == 0) {
                leftMinDistance++;
                left--;
            } else {
                break;
            }
        }
        int rightMinDistance = 1;
        while (right < seats.length) {
            if (seats[right] == 0) {
                rightMinDistance++;
                right++;
            } else {
                break;
            }
        }
        int maxReach;
        if (position == 0) {
            maxReach = rightMinDistance;
        } else if (position == seats.length - 1) {
            maxReach = leftMinDistance;
        } else {
            maxReach = Math.min(leftMinDistance, rightMinDistance);
        }
        maxDist = Math.max(maxDist, maxReach);
    }
}