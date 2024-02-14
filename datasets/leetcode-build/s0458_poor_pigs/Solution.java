package g0401_0500.s0458_poor_pigs;

// #Hard #Dynamic_Programming #Math #Combinatorics
// #2022_07_18_Time_0_ms_(100.00%)_Space_41_MB_(45.92%)

public class Solution {
	//@ requires(*The number of buckets must be between 1 and - The number of minutes to die must be between 1 and the number of minutes to test.*);
	//@ requires(*The number of minutes to test must be between 1 and*);
	//@ ensures(*The output must be an integer representing the minimum number of pigs needed to determine the poisonous bucket within the allotted time.*);
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets-- == 1) {
            return 0;
        }
        int base = minutesToTest / minutesToDie + 1;
        int count = 0;
        while (buckets > 0) {
            buckets /= base;
            count++;
        }
        return count;
    }
}