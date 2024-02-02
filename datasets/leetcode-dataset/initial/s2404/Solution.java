package g2401_2500.s2404_most_frequent_even_element;

// #Easy #Array #Hash_Table #Counting #2022_10_23_Time_32_ms_(81.60%)_Space_56.5_MB_(79.42%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given an integer array `nums`, return _the most frequent even element_. If there is a tie, return the **smallest** one. If there is no such element, return `-1`. We return the smallest one, which is 2.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int mostFrequent = Integer.MAX_VALUE;
        int maxFrequency = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                maxFrequency =
                        Math.max(
                                maxFrequency,
                                frequencyMap.compute(num, (n, freq) -> freq == null ? 1 : ++freq));
            }
        }
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mostFrequent = Math.min(mostFrequent, entry.getKey());
            }
        }
        return mostFrequent == Integer.MAX_VALUE ? -1 : mostFrequent;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
