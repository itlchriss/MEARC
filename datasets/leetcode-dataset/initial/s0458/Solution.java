package g0401_0500.s0458_poor_pigs;

// #Hard #Dynamic_Programming #Math #Combinatorics
// #2022_07_18_Time_0_ms_(100.00%)_Space_41_MB_(45.92%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given `buckets`, `minutesToDie`, and `minutesToTest`, return _the **minimum** number of pigs needed to figure out which bucket is poisonous within the allotted time_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
