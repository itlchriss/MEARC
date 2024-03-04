package g2901_3000.s2974_minimum_number_game;

// #Easy #Array #Sorting #Heap_Priority_Queue #Simulation
// #2024_01_18_Time_2_ms_(98.98%)_Space_45.1_MB_(23.84%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is even.*);
//@ ensures(*The elements of `nums` are integers.*);
//@ ensures(*The elements of `nums` are greater than or equal to - The elements of `nums` are less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `arr` is not null.*);
//@ ensures(*The length of `arr` is equal to the length of `nums`.*);
//@ ensures(*The elements of `arr` are in the same order as they were removed from `nums`.*);
//@ ensures(*The elements of `arr` are integers.*);
//@ ensures(*The elements of `arr` are greater than or equal to - The elements of `arr` are less than or equal to 100.*);
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int[] n = new int[nums.length];
        for (int i = 0, j = 1; i < nums.length; i += 2, j += 2) {
            n[i] = nums[j];
            n[j] = nums[i];
        }
        return n;
    }
}