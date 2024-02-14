package g1901_2000.s1936_add_minimum_number_of_rungs;

// #Medium #Array #Greedy #2022_05_15_Time_1_ms_(100.00%)_Space_54_MB_(89.37%)

public class Solution {
	//@ requires(*The input array `rungs` is not null.*);
	//@ requires(*The input array `rungs` is strictly increasing.*);
	//@ requires(*The input integer `dist` is greater than or equal to 1.*);
	//@ ensures(*The output is an integer representing the minimum number of rungs that must be added to the ladder.*);
	//@ ensures(*The ladder has rungs at heights that are strictly increasing.*);
	//@ ensures(*The ladder has rungs at heights that are at most `dist` apart.*);
	//@ ensures(*The ladder has rungs at heights that allow the user to reach the last rung.*);
    public int addRungs(int[] rungs, int dist) {
        int addons = 0;
        int currentHeight = 0;
        int i = 0;
        while (i < rungs.length) {
            int nextRung = rungs[i];
            if (nextRung - currentHeight <= dist) {
                currentHeight = nextRung;
                i++;
            } else {
                int adds = (nextRung - currentHeight - 1) / dist;
                addons += adds;
                currentHeight += dist * adds;
            }
        }
        return addons;
    }
}