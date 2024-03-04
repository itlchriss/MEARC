package g0401_0500.s0458_poor_pigs;

// #Hard #Dynamic_Programming #Math #Combinatorics
// #2022_07_18_Time_0_ms_(100.00%)_Space_41_MB_(45.92%)

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `buckets` must be greater than or equal to 1 and less than or equal to 1000.*);
//@ ensures(*The integer parameter `minutesToDie` must be greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(*The integer parameter `minutesToTest` must be greater than or equal to `minutesToDie` and less than or equal to 100.*);
//@ ensures(*The integer result is the minimum number of pigs needed to determine the poisonous bucket within the allotted time.*);
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