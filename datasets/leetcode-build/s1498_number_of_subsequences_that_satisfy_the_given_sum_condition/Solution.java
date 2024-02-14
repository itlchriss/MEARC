package g1401_1500.s1498_number_of_subsequences_that_satisfy_the_given_sum_condition;

// #Medium #Array #Sorting #Binary_Search #Two_Pointers #Binary_Search_II_Day_15
// #2022_03_24_Time_27_ms_(99.13%)_Space_50.5_MB_(97.89%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The target value `target` is a positive integer.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the number of non-empty subsequences of `nums` such that the sum of the minimum and maximum element on it is less or equal to `target`.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int numSubseq(int[] nums, int target) {
        // sorted array will be used to perform binary search
        Arrays.sort(nums);
        int mod = 1000_000_007;
        // powOf2[i] means (2^i) % mod
        int[] powOf2 = new int[nums.length];
        powOf2[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            powOf2[i] = (powOf2[i - 1] * 2) % mod;
        }
        int res = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                // nums[right] which is macimum is too big so decrease it
                right--;
            } else {
                // every number between right and left be either picked or not picked
                // so that is why pow(2, right - left) essentially
                res = (res + powOf2[right - left]) % mod;
                // increment left to find next set of min and max
                left++;
            }
        }
        return res;
    }
}