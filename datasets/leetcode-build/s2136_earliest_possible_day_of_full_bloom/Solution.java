package g2101_2200.s2136_earliest_possible_day_of_full_bloom;

// #Hard #Array #Sorting #Greedy #2022_06_04_Time_63_ms_(94.92%)_Space_53.4_MB_(97.46%)

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("java:S1210")
public class Solution {
	//@ requires(*The lengths of `plantTime` and `growTime` arrays are equal.*);
	//@ requires(*The length of `plantTime` and `growTime` arrays is greater than or equal to 1.*);
	//@ requires(*The elements of `plantTime` and `growTime` arrays are positive integers.*);
	//@ requires(*The elements of `plantTime` and `growTime` arrays are less than or equal to 10^4.*);
	//@ ensures(*The method returns an integer representing the earliest possible day where all seeds are blooming.*);
	//@ ensures(*All seeds are planted before they begin to grow.*);
	//@ ensures(*The planting of a seed is not complete until the specified number of days have been worked on planting it.*);
	//@ ensures(*After the last day of its growth, a seed blooms and stays bloomed forever.*);
	//@ ensures(*The seeds can be planted in any order from the beginning of day 0.*);
	//@ ensures(*The seeds grow and bloom according to the specified number of days in `growTime` array.*);
	//@ ensures(*The method returns the earliest possible day where all seeds are blooming.*);
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        if (n == 1) {
            return plantTime[0] + growTime[0];
        }
        Seed[] arr = new Seed[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Seed(plantTime[i], growTime[i]);
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int ans = arr[0].plantTime + arr[0].growTime;
        int lastPlantDay = arr[0].plantTime;
        for (int i = 1; i < n; i++) {
            int currBloomDay = lastPlantDay + arr[i].plantTime + arr[i].growTime;
            ans = Math.max(ans, currBloomDay);
            lastPlantDay += arr[i].plantTime;
        }
        return ans;
    }

    static class Seed implements Comparable<Seed> {
        int plantTime;
        int growTime;

        Seed(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }

        public int compareTo(Seed s) {
            return this.growTime - s.growTime;
        }
    }
}