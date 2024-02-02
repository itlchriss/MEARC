package g1401_1500.s1433_check_if_a_string_can_break_another_string;

// #Medium #String #Sorting #Greedy #2022_03_28_Time_9_ms_(77.89%)_Space_50.6_MB_(68.34%)

import java.util.Objects;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some permutation of string s2 or vice-versa. In other words s2 can break s1 or vice-versa. A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean checkIfCanBreak(String s1, String s2) {
        if (s1 == null && s2 == null || Objects.requireNonNull(s1).length() == 1) {
            return true;
        }
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = s1.length() - 1; i >= 0; i--) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        boolean isS1Greater = count1[25] >= count2[25];
        boolean isS2Greater = count2[25] >= count1[25];

        for (int i = 24; (isS1Greater || isS2Greater) && i >= 0; i--) {
            count1[i] += count1[i + 1];
            count2[i] += count2[i + 1];

            isS1Greater = isS1Greater && count1[i] >= count2[i];
            isS2Greater = isS2Greater && count2[i] >= count1[i];
        }
        return isS1Greater || isS2Greater;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
