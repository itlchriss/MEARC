package g0201_0300.s0238_product_of_array_except_self;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Prefix_Sum
// #Data_Structure_II_Day_5_Array #Udemy_Arrays #Big_O_Time_O(n^2)_Space_O(n)
// #2022_07_04_Time_1_ms_(100.00%)_Space_50.8_MB_(85.60%)

public class Solution {
//@ requires(*The product of any prefix or suffix of param_nums is guaranteed to fit in a 32-bit integer.*);
//@ requires(*You must write an algorithm that runs in `O(n)` time and without using the division operation.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,3,4]*);
//@ requires(*Output: [24,12,8,6]*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [-1,1,0,-3,3]*);
//@ requires(*Output: [0,0,9,0,0]*);
//@ requires(*Constraints:*);
//@ requires(*<code>2 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*`-30 <= nums[i] <= 30`*);
//@ requires(*The product of any prefix or suffix of param_nums is guaranteed to fit in a 32-bit integer.*);
//@ requires(*Follow up: Can you solve the problem in `O(1) `extra space complexity? (*);
//@ requires(*The output array does not count as extra space for space complexity analysis.)*);
//@ ensures(*Given an integer array param_nums, the result is an array `answer` such that `answer[i]` is equal to the product of all the elements of param_nums except `nums[i]`.*);
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] ans = new int[nums.length];
        for (int num : nums) {
            product = product * num;
        }
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                ans[i] = product / nums[i];
            } else {
                int p = 1;
                //@ maintaining 0 <= j <= nums.length;
                for (int j = 0; j < nums.length; j++) {
                    if (j != i) {
                        p = p * nums[j];
                    }
                }
                ans[i] = p;
            }
        }
        return ans;
    }
}