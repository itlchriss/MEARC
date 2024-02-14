package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The list `arr` is sorted in strictly increasing order.*);
	//@ requires(*The list `arr` contains all integers in the range `[1, n]`.*);
	//@ ensures(*The output is a single integer.*);
	//@ ensures(*The output is the last number that remains in the list `arr` after applying the algorithm.*);
	//@ ensures(*The list `arr` is modified according to the algorithm described in the requirements.*);
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n / 2) + 1);
    }
}