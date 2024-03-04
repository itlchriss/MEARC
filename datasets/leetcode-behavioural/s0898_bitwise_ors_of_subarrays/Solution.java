package g0801_0900.s0898_bitwise_ors_of_subarrays;

// #Medium #Array #Dynamic_Programming #Bit_Manipulation
// #2022_03_28_Time_151_ms_(97.74%)_Space_71.9_MB_(85.31%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `arr` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of unique results obtained from taking the bitwise OR of all elements in each contiguous subarray of `arr`.*);
//@ ensures(*Results that occur more than once are only counted once in the final answer.*);
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
            for (int j = i - 1; j >= 0; j--) {
                if ((arr[i] | arr[j]) == arr[j]) {
                    break;
                }
                arr[j] |= arr[i];
                set.add(arr[j]);
            }
        }
        return set.size();
    }
}