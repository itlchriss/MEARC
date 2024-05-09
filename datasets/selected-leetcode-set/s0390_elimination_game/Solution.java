package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
//@ requires(*The integer parameter `n` is greater than or equal to 1 and is less than or equal to 10^9.*);
//@ requires(*The length of the list `arr` decreases by half in each iteration of the elimination game algorithm.*);
//@ ensures(*The integer result is the last number that remains in the list `arr` after applying the elimination game algorithm.*);
//@ ensures(*The last number that remains in the list `arr` is returned as the integer result.*);
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n / 2) + 1);
    }
}