package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((n >= 1) && (n <= 1000000000));
//@ ensures((n == 9) ==> (\result == 6));
//@ ensures((n == 1) ==> (\result == 1));
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 * lastRemaining(n / 2) + 1);
    }
}
