package g0501_0600.s0561_array_partition_i;

// #Easy #Array #Sorting #Greedy #Counting_Sort
// #2022_08_03_Time_14_ms_(84.99%)_Space_44.2_MB_(95.29%)

import java.util.Arrays;

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array parameter `nums` must have a length of `2n`.*);
//@ ensures(*The integer result is the maximized sum of `min(ai, bi)` for all pairs `(ai, bi)` in the integer array parameter `nums`.*);
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            sum += Math.min(nums[i], nums[i + 1]);
        }
        return sum;
    }
}