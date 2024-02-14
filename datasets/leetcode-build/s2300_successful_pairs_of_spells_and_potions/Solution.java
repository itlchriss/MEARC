package g2201_2300.s2300_successful_pairs_of_spells_and_potions;

// #Medium #Array #Sorting #Binary_Search #Two_Pointers
// #2022_06_14_Time_85_ms_(71.70%)_Space_135.9_MB_(33.90%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input arrays `spells` and `potions` must not be null.*);
	//@ requires(*The lengths of the input arrays `spells` and `potions` must be equal to `n` and `m` respectively.*);
	//@ requires(*The length of the input array `pairs` must be equal to `n`.*);
	//@ requires(*The input integer `success` must be a positive integer.*);
	//@ ensures(*The output array `pairs` must not be null.*);
	//@ ensures(*The length of the output array `pairs` must be equal to `n`.*);
	//@ ensures(*Each element in the output array `pairs` must be a non-negative integer.*);
	//@ ensures(*The sum of all elements in the output array `pairs` must be equal to the total number of successful pairs of spells and potions.*);
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int l = 0;
            int r = potions.length;
            while (l < r) {
                int m = l + (r - l) / 2;
                if ((long) spells[i] * potions[m] >= success) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            spells[i] = potions.length - l;
        }
        return spells;
    }
}