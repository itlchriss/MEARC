package g2201_2300.s2294_partition_array_such_that_maximum_difference_is_k;

// #Medium #Array #Sorting #Greedy #2022_06_17_Time_35_ms_(94.09%)_Space_62.9_MB_(90.61%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are non-negative integers.*);
	//@ requires(*The value of `k` is a non-negative integer.*);
	//@ ensures(*The method returns an integer representing the minimum number of subsequences needed.*);
	//@ ensures(*Each element in the input array `nums` appears in exactly one of the subsequences.*);
	//@ ensures(*The difference between the maximum and minimum values in each subsequence is at most `k`.*);
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int partitions = 1;
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[j] > k) {
                partitions++;
                j = i;
            }
        }
        return partitions;
    }
}