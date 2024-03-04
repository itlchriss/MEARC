package g1001_1100.s1094_car_pooling;

// #Medium #Array #Sorting #Heap_Priority_Queue #Simulation #Prefix_Sum
// #2022_02_22_Time_1_ms_(99.38%)_Space_44.4_MB_(9.80%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `trips` array is not null.*);
//@ ensures(*The `capacity` is a positive integer.*);
//@ ensures(*The length of the `trips` array is between 1 and 1000.*);
//@ ensures(*Each trip in the `trips` array has a length of 3.*);
//@ ensures(*The number of passengers for each trip is between 1 and 100.*);
//@ ensures(*The pickup location for each trip is between 0 and 1000.*);
//@ ensures(*The drop-off location for each trip is between the pickup location and 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether it is possible to pick up and drop off all passengers for all the given trips.*);
//@ ensures(*If it is possible to pick up and drop off all passengers, the method returns true.*);
//@ ensures(*If it is not possible to pick up and drop off all passengers, the method returns false.*);
    public boolean carPooling(int[][] trips, int capacity) {
        int[] stops = new int[1001];
        for (int[] t : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }
        for (int i = 0; capacity >= 0 && i < 1001; ++i) {
            capacity -= stops[i];
        }
        return capacity >= 0;
    }
}