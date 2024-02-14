package g0901_1000.s0918_maximum_sum_circular_subarray;

// #Medium #Array #Dynamic_Programming #Divide_and_Conquer #Queue #Monotonic_Queue
// #Dynamic_Programming_I_Day_5 #2022_03_29_Time_3_ms_(92.86%)_Space_64.3_MB_(40.27%)

public class Solution {
    private int kadane(int[] nums, int sign) {
        int currSum = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for (int i : nums) {
            currSum = sign * i + Math.max(currSum, 0);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
	//@ requires(*1. The input array `nums` is not null.*);
	//@ requires(*2. The length of the input array `nums` is greater than 0.*);
	//@ requires(*3. The length of the input array `nums` is less than or equal to 30,000.*);
	//@ requires(*4. Each element in the input array `nums` is an integer.*);
	//@ requires(*5. Each element in the input array `nums` is within the range of -30,000 to 30,000.*);
	//@ ensures(*1. The method returns an integer representing the maximum possible sum of a non-empty subarray of `nums`.*);
	//@ ensures(*2. The subarray included in the maximum sum does not contain any repeated elements.*);
	//@ ensures(*3. The subarray can be circular, meaning the end of the array connects to the beginning of the array.*);
	//@ ensures(*4. The next element of `nums[i]` is `nums[(i + 1) % n]` and the previous element of `nums[i]` is `nums[(i - 1 + n) % n]`.*);
	//@ ensures(*5. If all elements in the input array `nums` are negative, the method returns the maximum negative value in the array.*);
	//@ ensures(*6. If all elements in the input array `nums` are positive, the method returns the sum of all elements in the array.*);
	//@ ensures(*7. If the maximum sum of a non-empty subarray is achieved by wrapping around the circular array, the method returns the maximum sum.*);
	//@ ensures(*8. If the maximum sum of a non-empty subarray is achieved without wrapping around the circular array, the method returns the maximum sum.*);

    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int sumOfArray = 0;
        for (int i : nums) {
            sumOfArray += i;
        }
        int maxSumSubarray = kadane(nums, 1);
        int minSumSubarray = kadane(nums, -1) * -1;
        if (sumOfArray == minSumSubarray) {
            return maxSumSubarray;
        } else {
            return Math.max(maxSumSubarray, sumOfArray - minSumSubarray);
        }
    }
}