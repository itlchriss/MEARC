package g1101_1200.s1124_longest_well_performing_interval;

// #Medium #Array #Hash_Table #Stack #Prefix_Sum #Monotonic_Stack
// #2023_06_01_Time_13_ms_(59.62%)_Space_44.5_MB_(22.12%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `hours` is not null.*);
//@ ensures(*The length of the input array `hours` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `hours` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer representing the length of the longest well-performing interval.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to the length of the input array `hours`.*);
    public int longestWPI(int[] hours) {
        int i = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(sum, -1);
        int max = Integer.MIN_VALUE;
        for (int val : hours) {
            sum += (val > 8) ? 1 : -1;
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            if (sum > 0) {
                max = i + 1;
            } else if (map.containsKey(sum - 1)) {
                max = Math.max(i - map.get(sum - 1), max);
            }
            i++;
        }
        if (max == Integer.MIN_VALUE) {
            max = 0;
        }
        return max;
    }
}