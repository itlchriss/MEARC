package g2101_2200.s2164_sort_even_and_odd_indices_independently;

// #Easy #Array #Sorting #2022_06_05_Time_2_ms_(97.22%)_Space_42.7_MB_(82.78%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The values in the input array `nums` are integers.*);
//@ ensures(*The values in the input array `nums` are between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array has the same length as the input array.*);
//@ ensures(*The values at odd indices in the output array are sorted in non-increasing order.*);
//@ ensures(*The values at even indices in the output array are sorted in non-decreasing order.*);
//@ ensures(*The values in the output array are rearranged according to the given rules.*);
    public int[] sortEvenOdd(int[] nums) {
        int[] odd = new int[nums.length / 2];
        int[] even = new int[(nums.length + 1) / 2];
        int o = 0;
        int e = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                even[e] = nums[i];
                ++e;
            } else {
                odd[o] = nums[i];
                ++o;
            }
        }
        Arrays.sort(odd);
        Arrays.sort(even);
        e = 0;
        o = odd.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = even[e];
                ++e;
            } else {
                nums[i] = odd[o];
                --o;
            }
        }
        return nums;
    }
}