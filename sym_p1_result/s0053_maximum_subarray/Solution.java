package g0001_0100.s0053_maximum_subarray;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Divide_and_Conquer #Data_Structure_I_Day_1_Array #Dynamic_Programming_I_Day_5
// #Udemy_Famous_Algorithm #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_1_ms_(100.00%)_Space_57.7_MB_(90.58%)

public class Solution {
//@ requires(*A subarray is a contiguous part of an array.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [-2,1,-3,4,-1,2,1,-5,4]*);
//@ requires(*Output: 6*);
//@ requires(*Explanation: [4,-1,2,1] has the largest sum = 6.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1]*);
//@ requires(*Output: 1*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [5,4,-1,7,8]*);
//@ requires(*Output: 23*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code>*);
//@ requires(*Follow up: If you have figured out the `O(n)` solution, try coding another solution using the divide and conquer approach, which is more subtle.*);
//@ ensures(*Given an integer array param_nums, find the contiguous subarray (containing at least one number) which has the largest sum and the result is its sum.*);
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            // calculating sub-array sum
            sum += num;
            maxi = Math.max(sum, maxi);
            if (sum < 0) {
                // there is no point to carry a -ve subarray sum. hence setting to 0
                sum = 0;
            }
        }
        return maxi;
    }
}