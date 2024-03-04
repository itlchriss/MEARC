package g0201_0300.s0209_minimum_size_subarray_sum;

// #Medium #Array #Binary_Search #Prefix_Sum #Sliding_Window #Algorithm_II_Day_5_Sliding_Window
// #Binary_Search_II_Day_1 #2022_06_28_Time_1_ms_(100.00%)_Space_50.1_MB_(11.60%)

public class Solution {
//@ ensures(*If there exists a contiguous subarray `[nums_l, nums_l+1, ..., nums_r-1, nums_r]` of the integer array parameter `nums` such that the sum of elements in this subarray is greater than or equal to the integer parameter `target`, the integer result is the minimal length of this subarray.*);
//@ ensures(*If there is no such contiguous subarray in the integer array parameter `nums` that satisfies the sum condition, the integer result is 0.*);
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