package g0901_1000.s0908_smallest_range_i;

// #Easy #Array #Math #2022_03_28_Time_2_ms_(88.84%)_Space_41.9_MB_(99.76%)

public class Solution {
//@ requires(*You are given an integer array param_nums and an integer param_k.*);
//@ requires(*In one operation, you can choose any index `i` where `0 <= i < nums.length` and change `nums[i]` to `nums[i] + x` where `x` is an integer from the range `[-k, k]`.*);
//@ requires(*You can apply this operation at most once for each index `i`.*);
//@ requires(*The score of param_nums is the difference between the maximum and minimum elements in param_nums.*);
//@ requires(*Return the minimum score of param_nums after applying the mentioned operation at most once for each index in it.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1], k = 0*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: The score is max(nums) - min(nums) = 1 - 1 = 0.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [0,10], k = 2*);
//@ requires(*Output: 6*);
//@ requires(*Explanation: Change nums to be [2, 8].*);
//@ requires(*The score is max(nums) - min(nums) = 8 - 2 = 6.*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [1,3,6], k = 3*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: Change nums to be [4, 4, 4].*);
//@ requires(*The score is max(nums) - min(nums) = 4 - 4 = 0.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>4</sup></code>*);
//@ requires(*<code>0 <= nums[i] <= 10<sup>4</sup></code>*);
//@ requires(*<code>0 <= k <= 10<sup>4</sup></code>*);
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
        return (max - k) - (min + k);
    }
}