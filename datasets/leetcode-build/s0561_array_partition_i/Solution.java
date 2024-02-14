package g0501_0600.s0561_array_partition_i;

// #Easy #Array #Sorting #Greedy #Counting_Sort
// #2022_08_03_Time_14_ms_(84.99%)_Space_44.2_MB_(95.29%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is even.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 2.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^4 to 10^4.*);
	//@ ensures(*The method returns an integer representing the maximized sum.*);
	//@ ensures(*The returned sum is the sum of the minimum values from each pair of integers in the input array `nums`.*);
	//@ ensures(*The returned sum is the maximum possible sum.*);
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            sum += Math.min(nums[i], nums[i + 1]);
        }
        return sum;
    }
}