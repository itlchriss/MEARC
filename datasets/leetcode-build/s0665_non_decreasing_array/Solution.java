package g0601_0700.s0665_non_decreasing_array;

// #Medium #Array #2022_03_22_Time_1_ms_(86.75%)_Space_54.7_MB_(6.25%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - The length of the input array `nums` is less than or equal to 10^- The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^5 to 10^*);
	//@ ensures(*The method returns a boolean value indicating whether the input array `nums` can become non-decreasing by modifying at most one element.*);
	//@ ensures(*If the input array `nums` can become non-decreasing by modifying at most one element, the method returns true.*);
	//@ ensures(*If the input array `nums` cannot become non-decreasing by modifying at most one element, the method returns false.*);
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (i - 2 >= 0 && nums[i] < nums[i - 2]) {
                    nums[i] = nums[i - 1];
                }
                count++;
            }
            if (count >= 2) {
                return false;
            }
        }
        return true;
    }
}