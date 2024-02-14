package g2001_2100.s2029_stone_game_ix;

// #Medium #Array #Math #Greedy #Counting #Game_Theory
// #2022_06_21_Time_14_ms_(53.97%)_Space_126.8_MB_(25.40%)

public class Solution {
	//@ requires(*The input array `stones` is not null.*);
	//@ requires(*The length of the input array `stones` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `stones` is a positive integer.*);
	//@ ensures(*The method returns `true` if Alice wins the game.*);
	//@ ensures(*The method returns `false` if Bob wins the game.*);
    public boolean stoneGameIX(int[] stones) {
        int zero = 0;
        int one = 0;
        int two = 0;
        for (int i : stones) {
            if (i % 3 == 0) {
                zero++;
            } else if (i % 3 == 1) {
                one++;
            } else if (i % 3 == 2) {
                two++;
            }
        }
        if (one == 0 && two == 0) {
            return false;
        }
        int max = Math.max(one, two);
        int min = Math.min(one, two);
        if (zero % 2 == 0) {
            return min != 0;
        }
        if (zero % 2 == 1) {
            return max - 2 > min;
        }
        return false;
    }
}