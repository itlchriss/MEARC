package g1101_1200.s1184_distance_between_bus_stops;

// #Easy #Array #2022_03_03_Time_0_ms_(100.00%)_Space_43.9_MB_(5.55%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `distance` must not be null.*);
//@ ensures(*The length of the input array `distance` must be equal to `n`.*);
//@ ensures(*The values in the input array `distance` must be non-negative integers.*);
//@ ensures(*The value of `n` must be greater than or equal to - The values of `start` and `destination` must be non-negative integers.*);
//@ ensures(*The values of `start` and `destination` must be less than `n`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method must return an integer representing the shortest distance between the `start` and `destination` stops.*);
//@ ensures(*The returned distance must be a non-negative integer.*);
//@ ensures(*The returned distance must be less than or equal to the sum of all distances in the input array `distance`.*);
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }
        int clockwise = 0;
        for (int i = start; i < destination; i++) {
            clockwise += distance[i];
        }
        int counterClockwise = 0;
        for (int i = destination; i < distance.length; i++) {
            counterClockwise += distance[i];
        }
        for (int i = 0; i < start; i++) {
            counterClockwise += distance[i];
        }

        return Math.min(clockwise, counterClockwise);
    }
}