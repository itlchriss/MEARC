package g1701_1800.s1776_car_fleet_ii;

// #Hard #Array #Math #Stack #Heap_Priority_Queue #Monotonic_Stack
// #2022_04_30_Time_19_ms_(93.81%)_Space_100.6_MB_(87.17%)

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
	//@ requires(*The input array `cars` is not null.*);
	//@ requires(*The length of the input array `cars` is greater than or equal to 1.*);
	//@ requires(*The positions of the cars in the input array `cars` are in increasing order.*);
	//@ requires(*The positions and speeds of the cars in the input array `cars` are within the valid range of 1 to 10^6.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of the output array `answer` is equal to the length of the input array `cars`.*);
	//@ ensures(*The values in the output array `answer` are either a positive number representing the time at which the car collides with the next car, or -1 if the car does not collide with the next car.*);
	//@ ensures(*The values in the output array `answer` are within a range of 10^-5 of the actual collision times.*);
    public double[] getCollisionTimes(int[][] cars) {
        Deque<Integer> stack = new LinkedList<>();
        int n = cars.length;
        double[] ans = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = -1.0;
            int[] presentCar = cars[i];
            int presentCarSpeed = presentCar[1];
            while (!stack.isEmpty()) {
                int previousCar = stack.peekLast();
                int previousCarSpeed = cars[previousCar][1];
                if (presentCarSpeed > previousCarSpeed
                        && (ans[previousCar] == -1.0
                                || catchTime(cars, i, previousCar) <= ans[previousCar])) {
                    ans[i] = catchTime(cars, i, previousCar);
                    break;
                }
                stack.pollLast();
            }
            stack.offerLast(i);
        }
        return ans;
    }

    private double catchTime(int[][] cars, int presentCar, int previousCar) {
        int dist = cars[previousCar][0] - cars[presentCar][0];
        int speed = cars[presentCar][1] - cars[previousCar][1];
        return 1.0 * dist / speed;
    }
}