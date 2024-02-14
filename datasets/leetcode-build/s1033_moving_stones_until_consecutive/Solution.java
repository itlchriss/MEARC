package g1001_1100.s1033_moving_stones_until_consecutive;

// #Medium #Math #Brainteaser #2022_02_27_Time_1_ms_(86.36%)_Space_42.7_MB_(6.82%)

import java.util.Arrays;

public class Solution {
    private int minMoves(int x, int y, int z) {
        if (x + 1 == y && y + 1 == z) {
            return 0;
        }
        if (y - x <= 2 || z - y <= 2) {
            return 1;
        }
        return 2;
    }

    private int maxMoves(int x, int z) {
        return z - x - 2;
    }
	//@ requires(*The input integers `a`, `b`, and `c` must be between 1 and 100 (inclusive).*);
	//@ requires(*The input integers `a`, `b`, and `c` must have different values.*);
	//@ ensures(*The output array `answer` must have a length of 2.*);
	//@ ensures(*The first element of the output array `answer[0]` must be the minimum number of moves that can be played.*);
	//@ ensures(*The second element of the output array `answer[1]` must be the maximum number of moves that can be played.*);
	//@ ensures(*The output array `answer` must contain integers.*);

    public int[] numMovesStones(int a, int b, int c) {
        int[] t = {a, b, c};
        Arrays.sort(t);
        int min = minMoves(t[0], t[1], t[2]);
        int max = maxMoves(t[0], t[2]);
        return new int[] {min, max};
    }
}