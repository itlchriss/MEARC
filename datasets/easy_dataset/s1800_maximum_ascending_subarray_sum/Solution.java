package g1701_1800.s1800_maximum_ascending_subarray_sum;

// #Easy #Array #2022_04_19_Time_0_ms_(100.00%)_Space_42.1_MB_(22.29%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The elements in the input array `nums` are in ascending order.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum possible sum of an ascending subarray in the input array `nums`.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is the sum of the elements in the ascending subarray with the maximum sum.*);
    public int maxAscendingSum(int[] nums) {
        int maxSum = nums[0];
        int i = 0;
        int j = i + 1;
        while (i < nums.length - 1 && j < nums.length) {
            int sum = nums[j - 1];
            while (j < nums.length && nums[j] - nums[j - 1] > 0) {
                sum += nums[j];
                j++;
            }
            i = j;
            maxSum = Math.max(maxSum, sum);
            j++;
        }
        return maxSum;
    }
}