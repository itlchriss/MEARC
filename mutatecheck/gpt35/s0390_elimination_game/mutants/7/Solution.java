package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
//@ requires n >= 1;
//@ ensures (\forall int i; i >= 1 && i <= n; i % 2 != 0 ==> i in \old(arr));
//@ ensures \result >= 1 && \result <= n;
//@ ensures (\forall int i; i >= 1 && i <= n; i % 2 != 0 ==> i in arr);
//@ ensures (\forall int i; i >= 1 && i <= n; i % 2 == 0 ==> i not in arr);
//@ ensures (\forall int i; i >= 1 && i <= n; i % 2 == 0 ==> i not in \old(arr));
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n % 2 - lastRemaining(n % 2) + 1);
    }
}
