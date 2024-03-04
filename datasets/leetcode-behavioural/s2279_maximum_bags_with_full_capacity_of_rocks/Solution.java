package g2201_2300.s2279_maximum_bags_with_full_capacity_of_rocks;

// #Medium #Array #Sorting #Greedy #2022_06_18_Time_17_ms_(91.12%)_Space_84.5_MB_(81.15%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `capacity` and `rocks` must have the same length.*);
//@ ensures(*The length of the input arrays `capacity` and `rocks` must be greater than or equal to 1.*);
//@ ensures(*The values in the input array `capacity` must be positive integers.*);
//@ ensures(*The values in the input array `rocks` must be non-negative integers.*);
//@ ensures(*The values in the input array `rocks` must not exceed the corresponding values in the input array `capacity`.*);
//@ ensures(*The value of `additionalRocks` must be a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum number of bags that could have full capacity after placing the additional rocks.*);
//@ ensures(*The number of rocks in each bag is updated according to the placement of additional rocks.*);
//@ ensures(*The bags that have full capacity are identified.*);
//@ ensures(*The method returns the correct maximum number of bags at full capacity.*);
//@ ensures(*The method does not modify the input arrays `capacity` and `rocks`.*);
//@ ensures(*The method does not use all of the additional rocks if it is not necessary.*);
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        for (int i = 0; i < len; i++) {
            capacity[i] -= rocks[i];
        }
        Arrays.sort(capacity);
        int total = 0;
        for (int i = 0; i < len && additionalRocks > 0; i++) {
            if (capacity[i] <= additionalRocks) {
                additionalRocks -= capacity[i];
                total++;
            }
        }
        return total;
    }
}