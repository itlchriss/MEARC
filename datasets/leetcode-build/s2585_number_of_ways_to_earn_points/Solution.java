package g2501_2600.s2585_number_of_ways_to_earn_points;

// #Hard #Array #Dynamic_Programming #2023_08_22_Time_56_ms_(76.40%)_Space_43.9_MB_(21.91%)

public class Solution {
    private static final int MOD = 1000000007;
    private Integer[][] memo;

    private int helper(int[][] types, int target, int typeIndex) {
        int n = types.length;
        if (typeIndex >= n) {
            return target == 0 ? 1 : 0;
        }
        if (memo[typeIndex][target] != null) {
            return memo[typeIndex][target];
        }
        int ways = 0;
        ways = (ways + helper(types, target, typeIndex + 1)) % MOD;
        int currQuestCount = types[typeIndex][0];
        int currQuestPoint = types[typeIndex][1];
        int pointsEarned;
        for (int quest = 1; quest <= currQuestCount; quest++) {
            pointsEarned = quest * currQuestPoint;
            if (pointsEarned > target) {
                break;
            }
            ways = (ways + helper(types, target - pointsEarned, typeIndex + 1)) % MOD;
        }
        memo[typeIndex][target] = ways;
        return memo[typeIndex][target];
    }
	//@ requires(*The input `target` is a positive integer.*);
	//@ requires(*The input `types` is a 2D integer array with dimensions `n x 2`, where `n` is a positive integer.*);
	//@ requires(*Each element in `types` is a 2-element array `[count_i, marks_i]`, where `count_i` and `marks_i` are positive integers.*);
	//@ requires(*The sum of all `count_i` values in `types` is greater than or equal to `target`.*);
	//@ ensures(*The output is an integer representing the number of ways to earn exactly `target` points in the exam.*);
	//@ ensures(*The output is non-negative.*);
	//@ ensures(*The output is less than or equal to `10^9 + 7`.*);

    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        memo = new Integer[n + 1][target + 1];
        return helper(types, target, 0);
    }
}