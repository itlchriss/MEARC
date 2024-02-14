package g0901_1000.s0930_binary_subarrays_with_sum;

// #Medium #Array #Hash_Table #Prefix_Sum #Sliding_Window
// #2022_03_29_Time_3_ms_(84.84%)_Space_56.9_MB_(44.53%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements of the input array `nums` are either 0 or 1.*);
	//@ requires(*The input integer `goal` is non-negative.*);
	//@ requires(*The input integer `goal` is less than or equal to the length of the input array `nums`.*);
	//@ ensures(*The method returns an integer representing the number of non-empty subarrays with a sum equal to the input integer `goal`.*);
    public int numSubarraysWithSum(int[] nums, int goal) {
        return goal == 0
                ? numSubarraysWithAtMostGoal(nums, 0)
                : numSubarraysWithAtMostGoal(nums, goal)
                        - numSubarraysWithAtMostGoal(nums, goal - 1);
    }

    private int numSubarraysWithAtMostGoal(int[] nums, int goal) {
        int windowSum = 0;
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < nums.length) {
            windowSum += nums[right++];
            while (left < right && windowSum > goal) {
                windowSum -= nums[left++];
            }
            res += (right - left);
        }
        return res;
    }
}