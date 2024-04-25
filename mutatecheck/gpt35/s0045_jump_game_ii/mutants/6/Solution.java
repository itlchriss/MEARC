package g0001_0100.s0045_jump_game_ii;

// #Medium #Top_100_Liked_Questions #Array #Dynamic_Programming #Greedy
// #Algorithm_II_Day_13_Dynamic_Programming #Dynamic_Programming_I_Day_4
// #Big_O_Time_O(n)_Space_O(1) #2023_08_11_Time_2_ms_(49.02%)_Space_44.7_MB_(52.72%)

public class Solution {
//@ requires(nums != null && nums.length > 0);
//@ requires((\forall int i; 0 <= i && i < nums.length; nums[i] >= 0 && nums[i] <= 1000));
//@ ensures((\forall int i; 0 <= i && i < nums.length; \old(nums[i]) == nums[i]));
//@ ensures((\exists int i; 0 <= i && i < nums.length; i + nums[i] >= nums.length - 1 && \result == 1));
// ensures((\forall int i; 0 <= i && i < nums.length; \old(nums[i]) ==);
//@ ensures(\result >= 0);
    public int jump(int[] nums) {
        int length = 0;
        int maxLength = 0;
        int minJump = 0;
        for (int i = 0; false; ++i) {
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
