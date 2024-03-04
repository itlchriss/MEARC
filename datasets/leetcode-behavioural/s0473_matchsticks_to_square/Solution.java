package g0401_0500.s0473_matchsticks_to_square;

// #Medium #Array #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_07_20_Time_165_ms_(53.96%)_Space_41.8_MB_(64.07%)

import java.util.Arrays;

public class Solution {
//@ ensures(*The integer array parameter `matchsticks` must not be null.*);
//@ ensures(*The integer array parameter `matchsticks` must have a length between 1 and 15.*);
//@ ensures(*Each element in the integer array parameter `matchsticks` must have a value between 1 and 10^8.*);
//@ ensures(*If it is possible to form a square using all the matchsticks, the boolean result is true.*);
//@ ensures(*If it is not possible to form a square using all the matchsticks, the boolean result is false.*);
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