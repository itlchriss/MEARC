package g1801_1900.s1884_egg_drop_with_2_eggs_and_n_floors;

// #Medium #Dynamic_Programming #Math #2022_05_10_Time_0_ms_(100.00%)_Space_39_MB_(97.33%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of moves needed to determine the value of `f`.*);
//@ ensures(*The value of `f` is an integer between 0 and `n`.*);
//@ ensures(*The first egg is dropped from floor 1 and the second egg is dropped from floor 2.*);
//@ ensures(*If the first egg breaks, the value of `f` is 0.*);
//@ ensures(*If the second egg breaks but the first egg didn't, the value of `f` is 1.*);
//@ ensures(*If both eggs survive, the value of `f` is 2.*);
//@ ensures(*If the first egg does not break, it is dropped again at floor 22.*);
//@ ensures(*If the first egg does not break again, it is dropped from floors 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99, and 100.*);
//@ ensures(*Regardless of the outcome, it takes at most 14 drops to determine the value of `f`.*);
    public int twoEggDrop(int n) {
        // given x steps, the maximum floors I can test with two eggs
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // move is i, previous move is i - 1,
            // we put egg on floor i, if egg breaks, we can check i - 1 floors with i - 1 moves
            // if egg does not break, we can check dp[i-1] floors having two eggs to with i - 1
            // moves
            dp[i] = 1 + i - 1 + dp[i - 1];
            if (dp[i] >= n) {
                return i;
            }
        }
        return 0;
    }
}