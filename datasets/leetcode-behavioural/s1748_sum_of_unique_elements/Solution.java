package g1701_1800.s1748_sum_of_unique_elements;

// #Easy #Array #Hash_Table #Counting #2022_04_30_Time_2_ms_(54.08%)_Space_42_MB_(38.95%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the sum of all the unique elements in the input array `nums`.*);
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                sum += entry.getKey();
            }
        }
        return sum;
    }
}