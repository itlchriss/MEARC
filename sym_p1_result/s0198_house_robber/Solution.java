package g0101_0200.s0198_house_robber;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Algorithm_I_Day_12_Dynamic_Programming #Dynamic_Programming_I_Day_3
// #Level_2_Day_12_Dynamic_Programming #Udemy_Dynamic_Programming #Big_O_Time_O(n)_Space_O(n)
// #2022_06_28_Time_0_ms_(100.00%)_Space_39.9_MB_(85.30%)

public class Solution {
//@ requires(*You are a professional robber planning to rob houses along a street.*);
//@ requires(*Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,3,1]*);
//@ requires(*Output: 4*);
//@ requires(*Explanation:*);
//@ requires(*Rob house 1 (money = 1) and then rob house 3 (money = 3).*);
//@ requires(*Total amount you can rob = 1 + 3 = 4.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [2,7,9,3,1]*);
//@ requires(*Output: 12*);
//@ requires(*Explanation:*);
//@ requires(*Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).*);
//@ requires(*Total amount you can rob = 2 + 9 + 1 = 12.*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= nums.length <= 100`*);
//@ requires(*`0 <= nums[i] <= 400`*);
//@ ensures(*Given an integer array param_nums representing the amount of money of each house, the result is the maximum amount of money you can rob tonight without alerting the police.*);
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