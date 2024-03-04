package g2501_2600.s2592_maximize_greatness_of_an_array;

// #Medium #Array #Sorting #Greedy #Two_Pointers
// #2023_08_23_Time_8_ms_(100.00%)_Space_57.2_MB_(41.49%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is the maximum possible greatness that can be achieved after permuting the input array `nums`.*);
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int perm = 0;
        for (int num : nums) {
            if (nums[perm] < num) {
                perm++;
            }
        }
        return perm;
    }
}