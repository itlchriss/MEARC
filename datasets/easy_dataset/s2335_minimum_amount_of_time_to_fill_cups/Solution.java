package g2301_2400.s2335_minimum_amount_of_time_to_fill_cups;

// #Easy #Array #Greedy #Heap_Priority_Queue #2022_07_13_Time_1_ms_(97.92%)_Space_41.6_MB_(68.17%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `amount` must have a length of 3.*);
//@ ensures(*Each element in the `amount` array must be a non-negative integer.*);
//@ ensures(*The sum of all elements in the `amount` array must be greater than 0.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value must be a non-negative integer.*);
//@ ensures(*The return value represents the minimum number of seconds needed to fill up all the cups.*);
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        int sum = amount[0] + amount[1] + amount[2];
        return (amount[0] + amount[1] < amount[2]) ? amount[2] : (sum + 1) / 2;
    }
}