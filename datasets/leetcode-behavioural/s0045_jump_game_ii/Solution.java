package g0001_0100.s0045_jump_game_ii;

// #Medium #Top_100_Liked_Questions #Array #Dynamic_Programming #Greedy
// #Algorithm_II_Day_13_Dynamic_Programming #Dynamic_Programming_I_Day_4
// #Big_O_Time_O(n)_Space_O(1) #2023_08_11_Time_2_ms_(49.02%)_Space_44.7_MB_(52.72%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the minimum number of jumps required to reach the last index in the integer array parameter `nums`.*);
//@ ensures(*The minimum number of jumps is calculated based on the maximum jump length at each position in the integer array parameter `nums`.*);
//@ ensures(*The minimum number of jumps is calculated by jumping from the first index to subsequent indices until reaching the last index.*);
    public int jump(int[] nums) {
        int length = 0;
        int maxLength = 0;
        int minJump = 0;
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