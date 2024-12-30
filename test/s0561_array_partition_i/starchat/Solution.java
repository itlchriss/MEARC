package g0501_0600.s0561_array_partition_i;

// #Easy #Array #Sorting #Greedy #Counting_Sort
// #2022_08_03_Time_14_ms_(84.99%)_Space_44.2_MB_(95.29%)

import java.util.Arrays;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `nums` is an even number.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and is greater than or equal to -10000.*);
//@ ensures(*The integer result is greater than or equal to the minimum possible sum of `min(a[i], b[i])` for all `i` and is less than or equal to the maximum possible sum of `min(a[i], b[i])` for all `i`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,4,3,2], the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [6,2,6,5,1,2], the integer result is equal to 9.*);
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            sum += Math.min(nums[i], nums[i + 1]);
        }
        return sum;
    }
}