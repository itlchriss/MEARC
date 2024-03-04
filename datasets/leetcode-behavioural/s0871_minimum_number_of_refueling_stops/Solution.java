package g0801_0900.s0871_minimum_number_of_refueling_stops;

// #Hard #Array #Dynamic_Programming #Greedy #Heap_Priority_Queue
// #2022_03_28_Time_3_ms_(88.52%)_Space_48.8_MB_(60.04%)

import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The target distance must be a positive integer greater than or equal to 1.*);
//@ ensures(*The starting fuel must be a positive integer greater than or equal to 1.*);
//@ ensures(*The stations array must be a valid array of size 0 to 500.*);
//@ ensures(*Each station in the stations array must be a valid array of size 2, where the first element represents the position of the station and the second element represents the amount of fuel at the station.*);
//@ ensures(*The positions of the stations must be in increasing order and less than the target distance.*);
//@ ensures(*The amount of fuel at each station must be a positive integer greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return the minimum number of refueling stops required to reach the target distance.*);
//@ ensures(*If it is not possible to reach the target distance, the method should return -1.*);
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        } else if (stations == null || stations.length == 0) {
            return -1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int start = 0;
        int end = stations.length;
        int currentFuel = startFuel;
        int stops = 0;
        while (currentFuel < target) {
            while (start < end && currentFuel >= stations[start][0]) {
                pq.add(stations[start++]);
            }
            if (pq.isEmpty()) {
                return -1;
            }
            int[] current = pq.poll();
            currentFuel += current[1];
            stops++;
        }
        return stops;
    }
}