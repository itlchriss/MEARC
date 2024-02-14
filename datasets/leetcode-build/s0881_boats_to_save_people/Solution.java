package g0801_0900.s0881_boats_to_save_people;

// #Medium #Array #Sorting #Greedy #Two_Pointers
// #2022_03_28_Time_20_ms_(61.11%)_Space_65.7_MB_(27.40%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `people` is not null.*);
	//@ requires(*The input array `people` has at least one element.*);
	//@ requires(*The input array `people` contains only positive integers.*);
	//@ requires(*The input integer `limit` is greater than or equal to 1.*);
	//@ requires(*The input integer `limit` is less than or equal to 30000.*);
	//@ ensures(*The output is an integer representing the minimum number of boats required to carry every given person.*);
	//@ ensures(*The output is greater than or equal to 1.*);
	//@ ensures(*The output is less than or equal to the length of the input array `people`.*);
	//@ ensures(*The output is the minimum number of boats such that each boat carries at most two people and the sum of their weights is at most `limit`.*);
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int boats = 0;
        while (i < j) {
            if (people[i] + people[j] <= limit) {
                boats++;
                i++;
                j--;
            } else if (people[i] + people[j] > limit) {
                boats++;
                j--;
            }
        }
        if (i == j) {
            return boats + 1;
        }
        return boats;
    }
}