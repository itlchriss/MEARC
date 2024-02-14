package g2301_2400.s2350_shortest_impossible_sequence_of_rolls;

// #Hard #Array #Hash_Table #Greedy #2022_07_30_Time_12_ms_(87.73%)_Space_84.9_MB_(79.95%)

import java.util.BitSet;

public class Solution {
	//@ requires(*The input array `rolls` is not null.*);
	//@ requires(*The length of the input array `rolls` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is less than or equal to 10^5.*);
	//@ requires(*Each element in the input array `rolls` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `rolls` is less than or equal to `k`.*);
	//@ ensures(*The output is an integer representing the length of the shortest sequence of rolls that cannot be taken from `rolls`.*);
    public int shortestSequence(int[] rolls, int k) {
        BitSet bitSet = new BitSet(k + 1);
        int cnt = 0;
        int res = 1;
        for (int roll : rolls) {
            if (!bitSet.get(roll)) {
                bitSet.set(roll);
                cnt++;
            }
            if (cnt == k) {
                res++;
                cnt = 0;
                bitSet.clear();
            }
        }
        return res;
    }
}