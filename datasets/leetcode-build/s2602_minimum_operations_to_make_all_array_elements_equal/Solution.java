package g2601_2700.s2602_minimum_operations_to_make_all_array_elements_equal;

// #Medium #Array #Sorting #Binary_Search #Prefix_Sum
// #2023_08_29_Time_41_ms_(97.39%)_Space_59.4_MB_(83.66%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `queries` is not null.*);
	//@ requires(*The length of `nums` is equal to `n`.*);
	//@ requires(*The length of `queries` is equal to `m`.*);
	//@ requires(*The elements of `nums` are positive integers.*);
	//@ requires(*The elements of `queries` are positive integers.*);
	//@ requires(*`n` and `m` are both greater than or equal to 1.*);
	//@ requires(*The maximum value of `n` is 10^5.*);
	//@ requires(*The maximum value of `m` is 10^5.*);
	//@ requires(*The maximum value of each element in `nums` is 10^9.*);
	//@ requires(*The maximum value of each element in `queries` is 10^9.*);
	//@ ensures(*The returned array `answer` is not null.*);
	//@ ensures(*The length of `answer` is equal to `m`.*);
	//@ ensures(*Each element in `answer` is a non-negative integer.*);
	//@ ensures(*The maximum value of each element in `answer` is 10^9.*);
	//@ ensures(*After each query, the array `nums` is reset to its original state.*);
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum[i] = sum[i - 1] + nums[i];
        }
        List<Long> res = new ArrayList<>();
        for (int query : queries) {
            res.add(getOperations(sum, nums, query));
        }
        return res;
    }

    private long getOperations(long[] sum, int[] nums, int target) {
        long res = 0;
        int index = getIndex(nums, target);
        int rightCounts = nums.length - 1 - index;
        if (index > 0) {
            res += (long) index * target - sum[index - 1];
        }
        if (rightCounts > 0) {
            res += sum[nums.length - 1] - sum[index] - (long) rightCounts * target;
        }
        res += Math.abs(target - nums[index]);
        return res;
    }

    private int getIndex(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            index = -(index + 1);
        }
        if (index == nums.length) {
            --index;
        }
        return index;
    }
}