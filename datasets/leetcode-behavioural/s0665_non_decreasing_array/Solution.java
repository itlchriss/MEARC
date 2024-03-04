package g0601_0700.s0665_non_decreasing_array;

// #Medium #Array #2022_03_22_Time_1_ms_(86.75%)_Space_54.7_MB_(6.25%)

public class Solution {
//@ ensures(*If the integer array parameter `nums` can be modified by changing at most one element to make it non-decreasing, the boolean result is true.*);
//@ ensures(*If the integer array parameter `nums` cannot be modified by changing at most one element to make it non-decreasing, the boolean result is false.*);
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