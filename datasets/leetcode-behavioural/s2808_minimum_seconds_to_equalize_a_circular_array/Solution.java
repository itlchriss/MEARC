package g2801_2900.s2808_minimum_seconds_to_equalize_a_circular_array;

// #Medium #Array #Hash_Table #Greedy #2023_09_25_Time_59_ms_(88.86%)_Space_82.4_MB_(29.30%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` is not null.*);
//@ ensures(*The length of the input list `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input list `nums` are integers.*);
//@ ensures(*The elements in the input list `nums` are greater than or equal to 1.*);
//@ ensures(*The elements in the input list `nums` are less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of seconds needed to make all elements in the input list `nums` equal.*);
//@ ensures(*The input list `nums` remains unchanged after the method is executed.*);
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        int min = n / 2;
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = nums.get(i);
            hm.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : hm.values()) {
            if (list.size() > 1) {
                int curr = (list.get(0) + n - list.get(list.size() - 1)) / 2;
                for (int i = 1; i < list.size(); i++) {
                    curr = Math.max(curr, (list.get(i) - list.get(i - 1)) / 2);
                }
                min = Math.min(min, curr);
            }
        }
        return min;
    }
}