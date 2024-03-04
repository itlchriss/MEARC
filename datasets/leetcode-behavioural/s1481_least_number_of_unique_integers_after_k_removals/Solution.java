package g1401_1500.s1481_least_number_of_unique_integers_after_k_removals;

// #Medium #Array #Hash_Table #Sorting #Greedy #Counting
// #2022_04_05_Time_62_ms_(85.33%)_Space_80.4_MB_(77.11%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `arr` are integers.*);
//@ ensures(*The value of `k` is non-negative.*);
//@ ensures(*The value of `k` is less than or equal to the length of the input array `arr`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the least number of unique integers after removing exactly `k` elements from the input array `arr`.*);
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr) {
            count.put(a, 1 + count.getOrDefault(a, 0));
        }
        int remaining = count.size();
        int occur = 1;
        int[] occurrenceCount = new int[arr.length + 1];

        for (int v : count.values()) {
            ++occurrenceCount[v];
        }
        while (k > 0) {
            if (k - occur * occurrenceCount[occur] >= 0) {
                k -= occur * occurrenceCount[occur];
                remaining -= occurrenceCount[occur++];
            } else {
                return remaining - k / occur;
            }
        }
        return remaining;
    }
}