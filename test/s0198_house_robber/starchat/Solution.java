package g0101_0200.s0198_house_robber;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Algorithm_I_Day_12_Dynamic_Programming #Dynamic_Programming_I_Day_3
// #Level_2_Day_12_Dynamic_Programming #Udemy_Dynamic_Programming #Big_O_Time_O(n)_Space_O(n)
// #2022_06_28_Time_0_ms_(100.00%)_Space_39.9_MB_(85.30%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 400 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Count of Positive Integer and Negative Integer*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer array `nums` sorted in **non-decreasing order**, return _the maximum count of positive integers and negative integers_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,1\]*);
//@ requires(**);
//@ requires(***Output:** 3*);
//@ requires(**);
//@ requires(***Explanation:** There are 3 positive integers (1, 2, 3) and 1 negative integer (-1).*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0,1\]*);
//@ requires(**);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 1000`*);
//@ requires(**   `-1000 <= nums[i] <= 1000`*);
//@ requires(**   `nums` is sorted in **non-decreasing order**.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(log n)` time complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(1)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n log n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n log n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n log n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n log n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n log n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n log n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(log n)` space complexity?*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the sum of all the values in the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1], the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,7,9,3,1], the integer result is equal to 12.*);
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] profit = new int[nums.length];
        profit[0] = nums[0];
        profit[1] = Math.max(nums[1], nums[0]);
        //@ assume nums.length >= 2;
        //@ maintaining 2 <= i <= nums.length;
        for (int i = 2; i < nums.length; i++) {
            profit[i] = Math.max(profit[i - 1], nums[i] + profit[i - 2]);
        }
        return profit[nums.length - 1];
    }
}