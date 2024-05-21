package g0001_0100.s0045_jump_game_ii;

// #Medium #Top_100_Liked_Questions #Array #Dynamic_Programming #Greedy
// #Algorithm_II_Day_13_Dynamic_Programming #Dynamic_Programming_I_Day_4
// #Big_O_Time_O(n)_Space_O(1) #2023_08_11_Time_2_ms_(49.02%)_Space_44.7_MB_(52.72%)

public class Solution {
//@ requires(*Given an array of non-negative integers param_nums, you are initially positioned at the first index of the array.*);
//@ requires(*Each element in the array represents your maximum jump length at that position.*);
//@ requires(*Your goal is to reach the last index in the minimum number of jumps.*);
//@ requires(*You can assume that you can always reach the last index.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [2,3,1,1,4]*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: The minimum number of jumps to reach the last index is 2.*);
//@ requires(*Jump 1 step from index 0 to 1, then 3 steps to the last index.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [2,3,0,1,4]*);
//@ requires(*Output: 2*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>4</sup></code>*);
//@ requires(*`0 <= nums[i] <= 1000`*);
    public int jump(int[] nums) {
        int length = 0;
        int maxLength = 0;
        int minJump = 0;
        //@ assume nums.length > 1;
        //@ decreases nums.length - 1 - i;
        //@ loop_invariant 0 <= i <= nums.length - 1;
        //@ maintaining length <= nums.length - i - 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            length--;
            maxLength--;
            maxLength = Math.max(maxLength, nums[i]);
            if (length <= 0) {
                length = maxLength;
                minJump++;
            }
            if (length >= nums.length - i - 1) {
                return minJump;
            }
        }
        return minJump;
    }
}