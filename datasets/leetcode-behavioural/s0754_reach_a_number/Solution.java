package g0701_0800.s0754_reach_a_number;

// #Medium #Math #Binary_Search #2022_03_25_Time_0_ms_(100.00%)_Space_38.8_MB_(86.55%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `target` is an integer.*);
//@ ensures(*The value of `target` is within the range of -10^9 to 10^- The value of `target` is not equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of moves required to reach the destination.*);
//@ ensures(*The number of moves is the minimum possible.*);
//@ ensures(*On each move, the player can either go left or right.*);
//@ ensures(*During the i-th move (starting from i = 1 to i = numMoves), the player takes i steps in the chosen direction.*);
//@ ensures(*The player starts at position 0 on an infinite number line.*);
//@ ensures(*The destination is at position `target`.*);
    public int reachNumber(int target) {
        target = Math.abs(target);
        int val = (((int) Math.sqrt(1.0 + 8 * ((long) target))) - 1) / 2;
        int sum = (val * (val + 1)) / 2;
        while (sum < target || (sum - target) % 2 != 0) {
            val++;
            sum = sum + val;
        }
        return val;
    }
}