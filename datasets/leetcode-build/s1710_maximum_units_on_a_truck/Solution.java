package g1701_1800.s1710_maximum_units_on_a_truck;

// #Easy #Array #Sorting #Greedy #2022_04_24_Time_9_ms_(78.69%)_Space_42.8_MB_(78.53%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `boxTypes` is a 2D array where each element `boxTypes[i]` is an array of size 2, representing the number of boxes of type `i` and the number of units per box of type `i`.*);
	//@ requires(*The input `truckSize` is an integer representing the maximum number of boxes that can be put on the truck.*);
	//@ requires(*The length of `boxTypes` is between 1 and - The number of boxes and units per box for each type `i` is between 1 and - The truck size is between 1 and 10^*);
	//@ ensures(*The method returns an integer representing the maximum total number of units that can be put on the truck.*);
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (b1, b2) -> Integer.compare(b2[1], b1[1]));
        int maxUnits = 0;
        int i = 0;
        while (truckSize > 0 && i < boxTypes.length) {
            if (boxTypes[i][0] <= truckSize) {
                maxUnits += boxTypes[i][0] * boxTypes[i][1];
                truckSize -= boxTypes[i][0];
            } else {
                maxUnits += Math.min(truckSize, boxTypes[i][0]) * boxTypes[i][1];
                truckSize -= Math.min(truckSize, boxTypes[i][0]);
            }
            i++;
        }
        return maxUnits;
    }
}