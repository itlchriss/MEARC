package g0201_0300.s0238_product_of_array_except_self;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Prefix_Sum
// #Data_Structure_II_Day_5_Array #Udemy_Arrays #Big_O_Time_O(n^2)_Space_O(n)
// #2022_07_04_Time_1_ms_(100.00%)_Space_50.8_MB_(85.60%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 2.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 30 and is greater than or equal to -30.*);
//@ requires(*The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.*);
//@ requires(*The algorithm runs in O(n) time complexity.*);
//@ requires(*The algorithm does not use any extra space complexity.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Count of Positive Integer and Negative Integer*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an array `nums` sorted in **non-decreasing** order, return _the maximum between the number of positive integers and the number of negative integers_.*);
//@ requires(**);
//@ requires(**   In other words, if the number of positive integers in `nums` is `pos` and the number of negative integers is `neg`, then return the maximum of `pos` and `neg`.*);
//@ requires(**);
//@ requires(***Note** that `0` is neither positive nor negative.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[-2,-1,-1,0,1,2\]*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** There are 3 positive integers and 3 negative integers.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0\]*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no positive integers and no negative integers.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 1000`*);
//@ requires(**   `-1000 <= nums[i] <= 1000`*);
//@ requires(**   `nums` is sorted in **non-decreasing** order.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(log(n))` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumCount(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to -1000.*);
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2770\. Maximum Number of Jumps to Reach the Last Index*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `nums` of `n` integers and an integer `target`.*);
//@ requires(**);
//@ requires(*You are initially positioned at index `0`. In one step, you can jump to index `i` if `nums[i] == target` and `i!= 0`.*);
//@ requires(**);
//@ requires(*Return _the **maximum number of jumps** you can make to reach the last index_. If you cannot reach the last index, return `-1`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,3,5*);
//@ ensures(*The integer array result is of the same length as the integer array parameter `nums`.*);
//@ ensures(*The integer array result does not use the division operation.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4], the integer array result is equal to [24,12,8,6].*);
//@ ensures(*If the integer array parameter `nums` is equal to [-1,1,0,-3,3], the integer array result is equal to [0,0,9,0,0].*);
//@ ensures(*If the integer result is less than the length of the integer array parameter `nums`, all the values in the integer array parameter `nums` are not greater than 0 and all the values in the integer array parameter `nums` are not less than 0.*);
//@ ensures(*If the integer result is equal to the length of the integer array parameter `nums` and the first value of the integer array parameter `nums` is greater than 0, all the values in the integer array parameter `nums` are greater than 0.*);
//@ ensures(*If the integer result is equal to the length of the integer array parameter `nums` and the first value of the integer array parameter `nums` is less than 0, all the values in the integer array parameter `nums` are less than 0.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[-2,-1,-1,0,1,2\], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[0\], the integer result is equal to 0.*);
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