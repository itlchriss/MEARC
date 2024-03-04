package g2401_2500.s2404_most_frequent_even_element;

// #Easy #Array #Hash_Table #Counting #2022_10_23_Time_32_ms_(81.60%)_Space_56.5_MB_(79.42%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned integer value is the most frequent even element in the input array `nums`.*);
//@ ensures(*If there is a tie for the most frequent even element, the method returns the smallest one.*);
//@ ensures(*If there is no even element in the input array `nums`, the method returns -1.*);
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
}