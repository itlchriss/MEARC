package g0001_0100.s0016_3sum_closest;

// #Medium #Array #Sorting #Two_Pointers #Level_2_Day_14_Sliding_Window/Two_Pointer
// #2023_08_09_Time_3_ms_(99.88%)_Space_42.6_MB_(97.42%)

import java.util.Arrays;

public class Solution {
	//@ requires(*1. The input array `nums` must not be null.*);
	//@ requires(*2. The length of the input array `nums` must be at least 3.*);
	//@ requires(*3. The target value must be within the range of -10^4 to 10^4.*);
	//@ ensures(*1. The method should return an integer representing the sum of the three integers in `nums` that is closest to the target.*);
	//@ ensures(*2. The returned sum should be the closest possible sum to the target.*);
	//@ ensures(*3. The method should return a valid sum even if there are multiple valid solutions.*);
	//@ ensures(*4. The method should return the sum as 0 if there are no three integers in `nums` that can be summed to a value closest to the target.*);
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        Arrays.sort(nums);
        int n = nums.length;
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] + nums[n - 1] + nums[n - 2] < target) {
                sum = nums[i] + nums[n - 1] + nums[n - 2];
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] > target) {
                int temp = nums[i] + nums[i + 1] + nums[i + 2];
                return lessGap(sum, temp, target);
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int temp = nums[i] + nums[j] + nums[k];
                if (temp == target) {
                    return target;
                }
                if (temp < target) {
                    j++;
                } else {
                    k--;
                }
                sum = lessGap(sum, temp, target);
            }
        }
        return sum;
    }

    private int lessGap(int sum, int temp, int target) {
        return Math.abs(sum - target) < Math.abs(temp - target) ? sum : temp;
    }
}