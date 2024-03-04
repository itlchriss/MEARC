package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The integer result is the last remaining number in the list `arr` after applying the elimination game algorithm.*);
//@ ensures(*The list `arr` must be initialized with all integers in the range `[1, n]` in strictly increasing order.*);
//@ ensures(*After each iteration of the elimination game algorithm, the list `arr` must be updated according to the specified rules.*);
//@ ensures(*The elimination game algorithm must alternate between removing numbers from left to right and right to left until only one number remains in the list `arr`.*);
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n / 2) + 1);
    }
}