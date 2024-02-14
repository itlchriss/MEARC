package g0001_0100.s0045_jump_game_ii;

// #Medium #Top_100_Liked_Questions #Array #Dynamic_Programming #Greedy
// #Algorithm_II_Day_13_Dynamic_Programming #Dynamic_Programming_I_Day_4
// #Big_O_Time_O(n)_Space_O(1) #2023_08_11_Time_2_ms_(49.02%)_Space_44.7_MB_(52.72%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - Each element in the input array `nums` is a non-negative integer.*);
	//@ requires(*The last index of the input array `nums` is reachable.*);
	//@ ensures(*The method returns an integer representing the minimum number of jumps required to reach the last index.*);
	//@ ensures(*The elements in the input array `nums` are not modified.*);
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