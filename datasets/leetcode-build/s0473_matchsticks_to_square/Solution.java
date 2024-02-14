package g0401_0500.s0473_matchsticks_to_square;

// #Medium #Array #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_07_20_Time_165_ms_(53.96%)_Space_41.8_MB_(64.07%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `matchsticks` is not null.*);
	//@ requires(*The length of the input array `matchsticks` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `matchsticks` is a positive integer.*);
	//@ requires(*The sum of all elements in the input array `matchsticks` is divisible by 4.*);
	//@ ensures(*The method returns `true` if it is possible to form a square using all the matchsticks, and `false` otherwise.*);
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) {
            return false;
        }
        int per = 0;
        for (int ele : matchsticks) {
            per = ele + per;
        }
        if (per % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        int side = per / 4;
        int[] sides = new int[] {side, side, side, side};
        return help(matchsticks, matchsticks.length - 1, sides);
    }

    private boolean help(int[] nums, int i, int[] side) {
        if (i == -1) {
            return side[0] == 0 && side[1] == 0 && side[2] == 0 && side[3] == 0;
        }
        for (int j = 0; j < 4; j++) {
            if (nums[i] > side[j]) {
                continue;
            }
            side[j] -= nums[i];
            if (help(nums, i - 1, side)) {
                return true;
            }
            side[j] += nums[i];
        }
        return false;
    }
}