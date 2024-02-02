package g2501_2600.s2522_partition_string_into_substrings_with_values_at_most_k;

// #Medium #String #Dynamic_Programming #Greedy #2023_04_18_Time_6_ms_(84.66%)_Space_43_MB_(76.70%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **minimum** number of substrings in a **good** partition of_ `s`. If no **good** partition of `s` exists, return `-1`.
Return _the **minimum** number of substrings in a **good** partition of_ `s`. If no **good** partition of `s` exists, return `-1`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int minimumPartition(String s, int k) {
        if (k == 9) {
            return s.length();
        }
        int partitions = 1;
        long partitionValue = 0;
        long digit;
        for (int i = 0; i < s.length(); i++) {
            digit = (long) s.charAt(i) - '0';
            if (digit > k) {
                return -1;
            }
            partitionValue = partitionValue * 10 + digit;
            if (partitionValue > k) {
                partitionValue = digit;
                partitions++;
            }
        }
        return partitions;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
