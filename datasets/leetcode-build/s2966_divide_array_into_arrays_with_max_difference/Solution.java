package g2901_3000.s2966_divide_array_into_arrays_with_max_difference;

// #Medium #Array #Sorting #Greedy #2024_01_16_Time_20_ms_(99.04%)_Space_59.4_MB_(10.50%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is a multiple of 3.*);
	//@ requires(*The positive integer `k` is greater than or equal to 1.*);
	//@ ensures(*The returned 2D array contains one or more arrays of size 3.*);
	//@ ensures(*Each element of the input array `nums` is in exactly one array in the returned 2D array.*);
	//@ ensures(*The difference between any two elements in each array in the returned 2D array is less than or equal to `k`.*);
	//@ ensures(*If it is impossible to satisfy the conditions, the returned 2D array is empty.*);
	//@ ensures(*If there are multiple valid solutions, any of them can be returned.*);
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int triplets = n / 3;
        int[][] result = new int[triplets][];
        for (int i = 0, j = 0; i < n; i += 3, j++) {
            int first = nums[i];
            int third = nums[i + 2];
            if (third - first > k) {
                return new int[0][];
            }
            result[j] = new int[] {first, nums[i + 1], third};
        }
        return result;
    }
}