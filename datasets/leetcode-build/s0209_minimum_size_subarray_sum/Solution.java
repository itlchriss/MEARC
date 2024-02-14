package g0201_0300.s0209_minimum_size_subarray_sum;

// #Medium #Array #Binary_Search #Prefix_Sum #Sliding_Window #Algorithm_II_Day_5_Sliding_Window
// #Binary_Search_II_Day_1 #2022_06_28_Time_1_ms_(100.00%)_Space_50.1_MB_(11.60%)

public class Solution {
	//@ requires(*1. The input array `nums` is not null.*);
	//@ requires(*2. The input array `nums` is not empty.*);
	//@ requires(*3. The target value `target` is a positive integer.*);
	//@ requires(*4. The elements in the input array `nums` are positive integers.*);
	//@ requires(*5. The length of the input array `nums` is less than or equal to 10^5.*);
	//@ requires(*6. The target value `target` is less than or equal to 10^9.*);
	//@ ensures(*1. The method returns an integer representing the minimal length of a contiguous subarray whose sum is greater than or equal to the target value.*);
	//@ ensures(*2. If there is no such subarray, the method returns 0.*);
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j];
            if (sum >= target) {
                while (i <= j) {
                    if (sum - nums[i] >= target) {
                        sum = sum - nums[i];
                        i++;
                    } else {
                        break;
                    }
                }
                if (j - i + 1 < min) {
                    min = j - i + 1;
                }
            }
            j++;
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }
}