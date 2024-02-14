package g1401_1500.s1406_stone_game_iii;

// #Hard #Array #Dynamic_Programming #Math #Game_Theory
// #2022_03_26_Time_14_ms_(82.02%)_Space_117_MB_(71.71%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `stoneValue` is not null.*);
	//@ requires(*The length of the input array `stoneValue` is greater than or equal to 1.*);
	//@ requires(*The values in the input array `stoneValue` are integers.*);
	//@ requires(*The values in the input array `stoneValue` are within the range of -1000 to 1000.*);
	//@ ensures(*The output is a string.*);
	//@ ensures(*The output is either "Alice", "Bob", or "Tie".*);
	//@ ensures(*If the output is "Alice", it means Alice will win the game.*);
	//@ ensures(*If the output is "Bob", it means Bob will win the game.*);
	//@ ensures(*If the output is "Tie", it means the game will end with the same score for both players.*);
	//@ ensures(*The game continues until all the stones have been taken.*);
	//@ ensures(*The score of each player is the sum of the values of the stones taken.*);
	//@ ensures(*The score of each player is initially 0.*);
	//@ ensures(*Alice and Bob take turns, with Alice starting first.*);
	//@ ensures(*On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.*);
	//@ ensures(*Alice and Bob play optimally.*);
    public String stoneGameIII(int[] stoneValue) {
        int[] dp = new int[stoneValue.length + 1];
        Arrays.fill(dp, 0);
        int i = stoneValue.length - 1;
        while (i >= 0) {
            int ans = Integer.MIN_VALUE;
            ans = Math.max(ans, stoneValue[i] - dp[i + 1]);
            if (i + 1 < stoneValue.length) {
                ans = Math.max(ans, stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }
            if (i + 2 < stoneValue.length) {
                ans =
                        Math.max(
                                ans,
                                stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }
            dp[i] = ans;
            i--;
        }
        int value = dp[0];
        if (value > 0) {
            return "Alice";
        }
        return value == 0 ? "Tie" : "Bob";
    }
}