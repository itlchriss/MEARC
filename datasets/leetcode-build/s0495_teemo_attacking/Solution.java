package g0401_0500.s0495_teemo_attacking;

// #Easy #Array #Simulation #2022_07_21_Time_2_ms_(97.97%)_Space_55_MB_(14.20%)

public class Solution {
	//@ requires(*The `timeSeries` array is not null.*);
	//@ requires(*The `timeSeries` array is sorted in non-decreasing order.*);
	//@ requires(*The `duration` is a non-negative integer.*);
	//@ ensures(*The method returns an integer representing the total number of seconds that Ashe is poisoned.*);
	//@ ensures(*The total number of seconds that Ashe is poisoned is equal to the sum of the durations of all poison effects.*);
	//@ ensures(*If the `timeSeries` array is empty, the method returns 0.*);
	//@ ensures(*If the `timeSeries` array has only one element, the method returns the value of `duration`.*);
	//@ ensures(*If the `timeSeries` array has multiple elements, the method calculates the total number of seconds that Ashe is poisoned by iterating through the array and considering the poison effects of each attack.*);
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (duration == 0) {
            return 0;
        }
        int start = timeSeries[0];
        int end = timeSeries[0] + duration - 1;
        int poisonDuration = end - start + 1;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] <= end) {
                poisonDuration += (duration - (end - timeSeries[i] + 1));
                end += (duration - (end - timeSeries[i] + 1));
            } else {
                start = timeSeries[i];
                end = timeSeries[i] + duration - 1;
                poisonDuration += end - start + 1;
            }
        }
        return poisonDuration;
    }
}