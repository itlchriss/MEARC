package g0901_1000.s0908_smallest_range_i;

// #Easy #Array #Math #2022_03_28_Time_2_ms_(88.84%)_Space_41.9_MB_(99.76%)

public class Solution {
//@ ensures (\forall int i; 0 <= i && i < nums.length; nums[i] - k <= \result && \result <= nums[i] + k);
//@ ensures (\forall int i, j; 0 <= i && i < nums.length && 0 <= j && j < nums.length; nums[i] - k <= nums[j] + k && nums[j] - k <= nums[i] + k);
// ensures (\forall int i, j; 0 <= i && i < nums.length && 0 <= j && j < nums.length; nums[i] - k <= nums[j] && nums[j] <= nums[i]
//@ ensures (\forall int i, j; 0 <= i && i < nums.length && 0 <= j && j < nums.length; nums[i] - k <= nums[j] && nums[j] <= nums[i] + k);
//@ requires nums != null && nums.length >= 1 && k >= 0;
//@ ensures (\forall int i, j; 0 <= i && i < nums.length && 0 <= j && j < nums.length; nums[i] - k <= nums[j] - k && nums[j] + k <= nums[i] + k);
//@ ensures \result >= 0;
// requires public int smallestRangeI(int[] nums, int k)
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min + k >= max - k) {
            return 0;
        }
        return (max - k) / (min + k);
    }
}
